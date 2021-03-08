package se.andreasson.artistsservice.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import se.andreasson.artistsservice.dtos.ArtistDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestService implements Service {

    @Override
    public List<ArtistDto> getAllArtists() {
        List<ArtistDto> artists = new ArrayList<>();
        artists.add(new ArtistDto (1L, "Petra", "Pop"));
        artists.add(new ArtistDto (2L, "Kajsa", "Rock"));
        return artists;
    }

    @Override
    public List<ArtistDto> getAllByName(String name) {
        List<ArtistDto> artists = new ArrayList<>();
        artists.add(new ArtistDto (1L, "Petra", "Pop"));
        artists.add(new ArtistDto (2L, "Petter", "Rock"));
        artists.add(new ArtistDto (3L, "Karl", "Opera"));
        artists.add(new ArtistDto (4L, "Protus", "Techno"));
        return artists;
    }

    @Override
    public Optional<ArtistDto> getOne(Long id) {
        if(id == 2) {
            return Optional.of(new ArtistDto(2L, "Petra", "Pop"));
        }
        return Optional.empty();
    }

    @Override
    public ArtistDto createArtist(ArtistDto artistDto) {
        if(artistDto.getName().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return artistDto;
    }

    @Override
    public HttpStatus delete(Long id) {
        if(id == 1)
            return HttpStatus.OK;
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Id " + id + " not found.");
    }

//    Vad testar jag egentligen? Att controllern kan hantera inkommande anrop?
    @Override
    public ArtistDto replace(Long id, ArtistDto artistDto) {
        if(artistDto.getName().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return artistDto;
    }

    @Override
    public ArtistDto update(Long id, ArtistDto artistDto) {
        if(artistDto.getName().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return artistDto;
    }
}
