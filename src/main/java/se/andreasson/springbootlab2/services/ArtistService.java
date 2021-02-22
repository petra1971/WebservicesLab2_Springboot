package se.andreasson.springbootlab2.services;

import org.springframework.stereotype.Service;
import se.andreasson.springbootlab2.dtos.ArtistDto;
import se.andreasson.springbootlab2.entities.Artist;
import se.andreasson.springbootlab2.mappers.ArtistMapper;
import se.andreasson.springbootlab2.repositories.ArtistRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    private final ArtistMapper artistMapper;
    private ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository, ArtistMapper artistMapper) {
        this.artistRepository = artistRepository;
        this.artistMapper = artistMapper;
    }

    public List<ArtistDto> getAllArtists() {
        return artistMapper.map(artistRepository.findAll());
    }

    public Optional<ArtistDto> getOne(Long artistId) {
        return artistMapper.map(artistRepository.findById(artistId));
    }

    //Map from ArtistDto to Artist
    public ArtistDto createArtist(ArtistDto artist) {
        if(artist.getName().isEmpty())
            throw new RuntimeException();

        return artistMapper.map(artistRepository.save(artistMapper.map(artist)));
    }
}
