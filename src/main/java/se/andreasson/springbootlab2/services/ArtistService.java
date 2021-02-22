package se.andreasson.springbootlab2.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
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

    public void delete(Long id) {
        if(artistRepository.existsById(id))
            artistRepository.deleteById(id);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Id " + id + " not found.");
    }

    public ArtistDto replace(Long id, ArtistDto artistDto) {
        Optional<Artist> artist = artistRepository.findById(id);
        if(artist.isPresent()) {
            Artist updatedArtist = artist.get();
            updatedArtist.setName(artistDto.getName());
//            artistRepository.save(updatedArtist);
            return artistMapper.map(artistRepository.save(updatedArtist));
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id " + id + " not found.");
    }
}
