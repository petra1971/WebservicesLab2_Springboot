package se.andreasson.artistsservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import se.andreasson.artistsservice.dtos.ArtistDto;
import se.andreasson.artistsservice.repositories.ArtistRepository;

import java.util.Arrays;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

//@TestPropertySource(properties = {"spring.datasource.url=jdbc:sqlserver://localhost;database=petraSpringLab2Test",
//        "spring.datasource.username=petra_test", "spring.datasource.password=test",
//        "spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver",
//        "spring.jpa.hibernate.ddl-auto=none"})
//@Sql(scripts = "classpath:schema.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ArtistsserviceApplicationTests {

    @LocalServerPort
    int port;
    final String URI_BASE = "http://localhost:" + port;

    @Autowired
    TestRestTemplate testClient;

    @Autowired
    ArtistRepository artistRepository;

//    Database is initialized automatically by using schema.sql and data.sql files, with create and insert scripts, under test/resources
//    Read here: https://www.baeldung.com/spring-boot-data-sql-and-schema-sql
//    Spring Boot makes it really easy to manage our database changes in an easy way.
//    When we run the project with schema.sql file and data.sql on the classpath, Spring will pick
//    them up and use it for creating a schema and populating the database.
//    Required setting: spring.jpa.hibernate.ddl-auto=none otherwise Hibernate will try to create the same

    @Test
    void contextLoads() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Accept", "application/xml");
//        testClient.exchange("localhost:8080/person", HttpMethod.GET, new HttpEntity<>(headers), PersonDto[].class);

        var result = testClient.getForEntity(URI_BASE + "/artists", ArtistDto[].class);
        System.out.println("get for entity is ran");;
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().length).isGreaterThan(0);

//      Predicate example: name -> name.startsWith("A") https://www.baeldung.com/java-predicate-chain

        Predicate<ArtistDto> predicate = artistDto -> artistDto.getName().equals("Petra");
        assertThat(result.getBody()).contains(new ArtistDto(1, "Petra", "Pop"));
        assertThat(Arrays.stream(result.getBody()).anyMatch(predicate));
    }


    @Test
    void postArtist() {
        ArtistDto artistDto = new ArtistDto(10L, "Olle", "Rock");
        var postResult = testClient.postForEntity(URI_BASE + "/artists", artistDto, ArtistDto.class);
        assertThat(postResult.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        System.out.println("Post status code: " + postResult.getStatusCode());
        var result = testClient.getForEntity(URI_BASE + "/artists", ArtistDto[].class);

        Predicate<ArtistDto> predicate1 = artistDto1 -> artistDto1.getName().equals("Olle");

        assertThat(Arrays.stream(result.getBody()).anyMatch(predicate1));

        ArtistDto[] artistDtos = result.getBody();
        for (ArtistDto artistDto1 : artistDtos) {
            System.out.println(artistDto1.toString());
            if (artistDto1.getName().equals("Olle"))
                System.out.println("artistDto name: " + artistDto1.getName());
        }
    }
    @Test
    void putArtist() {
        ArtistDto artistDto = new ArtistDto(4, "Kamilla", "Pop");
        testClient.put(URI_BASE + "/artists", artistDto);
        System.out.println(artistDto.toString());
        var result = testClient.getForEntity(URI_BASE + "/artists", ArtistDto[].class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);

        Predicate<ArtistDto> predicate1 = artist -> artist.getName().equals("Kalle");
        assertThat(Arrays.stream(result.getBody()).anyMatch(predicate1));

        ArtistDto[] artistDtos = result.getBody();
        for (ArtistDto artistDto1 : artistDtos) {
            System.out.println(artistDto1.toString());
            if (artistDto1.getName().equals("Olle"))
                System.out.println("artistDto name: " + artistDto1.getName());
        }
    }

    @Test
    void patchArtist() {
        ArtistDto artistDto = new ArtistDto(3L, "Östen", "Opera");
        var patchResult= testClient.patchForObject(URI_BASE + "/artists", artistDto, ArtistDto.class);
        var result = testClient.getForEntity(URI_BASE + "/artists", ArtistDto[].class);
        Predicate<ArtistDto> predicate1 = artistDto1 -> artistDto1.getName().equals("Östen");

        assertThat(Arrays.stream(result.getBody()).anyMatch(predicate1));
    }

    @Test
    void deleteArtist() {
        ArtistDto artistDto = new ArtistDto(1L, "Petra", "Pop");
        var result = testClient.getForEntity(URI_BASE+ "/artists/" + artistDto.getArtistId(), ArtistDto.class);
        assertThat(result.getBody().getName().contains("Petra"));
        testClient.delete(URI_BASE + "/artists/" + artistDto.getArtistId());
        result = testClient.getForEntity(URI_BASE+ "/artists/" + artistDto.getArtistId(), ArtistDto.class);
        assertThat(!result.getBody().getName().contains("Petra"));
    }
}
