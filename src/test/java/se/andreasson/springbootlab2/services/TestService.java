package se.andreasson.springbootlab2.services;

import se.andreasson.springbootlab2.dtos.ArtistDto;
import se.andreasson.springbootlab2.services.Service;
import java.util.List;
import java.util.Optional;

public class TestService implements Service {

    @Override
    public List<ArtistDto> getAllArtists() {
        return null;
    }

    @Override
    public Optional<ArtistDto> getOne(Long id) {
        if(id == 2) {
            return Optional.of(new ArtistDto(2, "Petra"));
        }
        return Optional.empty();
    }

    @Override
    public ArtistDto createArtist(ArtistDto artist) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ArtistDto replace(Long id, ArtistDto artistDto) {
        return null;
    }

    @Override
    public ArtistDto update(Long id, ArtistDto artistDto) {
        return null;
    }
}
