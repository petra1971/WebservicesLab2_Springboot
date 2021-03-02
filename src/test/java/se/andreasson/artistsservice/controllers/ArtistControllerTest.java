package se.andreasson.artistsservice.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;
import se.andreasson.artistsservice.configurations.TestConfiguration;
import se.andreasson.artistsservice.services.TestService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ArtistControllerTest {

    @Test
    void callingOneWithValidIdReturnOneArtist() {
        ArtistController artistController = new ArtistController(new TestConfiguration(), new TestService());
        var artist = artistController.one(2L);

        assertThat(artist.getArtistId()).isEqualTo(2);
        assertThat(artist.getName()).isEqualTo("Petra");
    }

    void callingOneWithInvalidIdThrowsNotFoundException() {
        ArtistController artistController = new ArtistController(new TestConfiguration(), new TestService());

        var artist = artistController.one(1L);
        assertThrows(ResponseStatusException.class, () -> artistController.one(1L));

    }

}