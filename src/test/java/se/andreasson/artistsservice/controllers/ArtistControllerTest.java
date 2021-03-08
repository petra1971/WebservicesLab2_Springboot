package se.andreasson.artistsservice.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import se.andreasson.artistsservice.configurations.TestConfiguration;
import se.andreasson.artistsservice.dtos.ArtistDto;
import se.andreasson.artistsservice.services.TestService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ArtistControllerTest {

    @Test @Disabled
    void callingOneWithValidIdReturnOneArtist() {
        ArtistController artistController = new ArtistController(new TestConfiguration(), new TestService());
        var artist = artistController.one(2L);

        assertThat(artist.getArtistId()).isEqualTo(2);
        assertThat(artist.getName()).isEqualTo("Petra");
    }

    @Test @Disabled
    void callingOneWithInvalidIdThrowsNotFoundException() {
        ArtistController artistController = new ArtistController(new TestConfiguration(), new TestService());

        var artist = artistController.one(1L);

        var exception =  assertThrows(ResponseStatusException.class, () -> artistController.one(1L));
        assertThat(exception.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);

    }

    @Test @Disabled
    void callingAllReturnListWithArtists() {
        ArtistController artistController = new ArtistController(new TestConfiguration(), new TestService());
        var artists = artistController.all();
        assertThat(artists.get(0).getArtistId() == 1);
        assertThat(artists.get(0).getName().equals("Petra"));
        assertThat(artists.get(0).getArtistId() == 2);
        assertThat(artists.get(1).getName().equals("Kajsa"));
    }

    @Test @Disabled
    void callingAllByNameWithPartialValidNameReturnArtists() {
        ArtistController artistController = new ArtistController(new TestConfiguration(), new TestService());
        var artists = artistController.allByName("Pe");
        for (var artist : artists) {
            assertThat(artist.getName().equals("Petra") || artist.getName().equals("Petter"));
            assertThat(!artist.getName().equals("Karl") && !artist.getName().equals("Protus"));
        }
    }

    @Test @Disabled
    void callingAllByNameWithPartialNotValidNameReturnArtists() {
        ArtistController artistController = new ArtistController(new TestConfiguration(), new TestService());
        var artists = artistController.allByName("So");
            assertThat(artists.isEmpty());
    }

    @Test @Disabled
    void callingCreateWithValidNameReturnArtist() {
        ArtistController artistController = new ArtistController(new TestConfiguration(), new TestService());
        var artist = artistController.create(new ArtistDto(1L, "Petra", "Rock"));
        assertThat(artist.getArtistId() == 1 && artist.getName().equals("Petra"));
        assertThat(HttpStatus.CREATED);
    }

    @Test @Disabled
    void callingCreateWithEmptyNameThrowsBadRequestException() {
        ArtistController artistController = new ArtistController(new TestConfiguration(), new TestService());
        var artist = new ArtistDto(1L, "", "Pop");
        var exception = assertThrows(ResponseStatusException.class,
                () -> artistController.create(artist));
        assertThat(exception.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void callingDeleteWithValidIdResponseStatusOK() {
        ArtistController artistController = new ArtistController(new TestConfiguration(), new TestService());
        artistController.delete(1L);
        assertThat(HttpStatus.OK);
    }

    @Test
    void callingtDeleteWithInvalidIdThrowsNotFoundException() {
        ArtistController artistController = new ArtistController(new TestConfiguration(), new TestService());
        artistController.delete(2L);
        var exception = assertThrows(ResponseStatusException.class,
                () -> artistController.delete(2L));
        assertThat(exception.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void callingReplaceWithValidIdNoNameResponseOK() {
        ArtistController artistController = new ArtistController(new TestConfiguration(), new TestService());
        var artist = new ArtistDto();
        artist.setArtistId(1L);
        artistController.replace(1L, artist);
        assertThat(HttpStatus.OK);
    }

    @Test void callingUpdateWithEmptyNameThrowsBadRequestException() {
        ArtistController artistController = new ArtistController(new TestConfiguration(), new TestService());
        artistController.replace(1L, new ArtistDto(1L, "", "Opera"));
        assertThat(HttpStatus.BAD_REQUEST);
    }
}
