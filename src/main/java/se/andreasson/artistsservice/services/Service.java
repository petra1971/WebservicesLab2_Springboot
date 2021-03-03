package se.andreasson.artistsservice.services;

import se.andreasson.artistsservice.dtos.ArtistDto;

import java.util.List;
import java.util.Optional;

public interface Service {
    List<ArtistDto> getAllArtists();

    Optional<ArtistDto> getOne(Long artistId);

    List<ArtistDto> getAllByName(String name);

    //Map from ArtistDto to Artist
    ArtistDto createArtist(ArtistDto artist);

    void delete(Long id);

    ArtistDto replace(Long id, ArtistDto artistDto);

    ArtistDto update(Long id, ArtistDto artistDto);
}
