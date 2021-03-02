package se.andreasson.artistsservice.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.andreasson.artistsservice.dtos.ArtistDto;
import se.andreasson.artistsservice.entities.Artist;
import se.andreasson.artistsservice.mappers.ArtistMapper;
import se.andreasson.artistsservice.repositories.ArtistRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistService implements se.andreasson.artistsservice.services.Service {

    private final ArtistMapper artistMapper;
    private ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository, ArtistMapper artistMapper) {
        this.artistRepository = artistRepository;
        this.artistMapper = artistMapper;
    }

    @Override
    public List<ArtistDto> getAllArtists() {
        return artistMapper.map(artistRepository.findAll());
    }

    @Override
    public Optional<ArtistDto> getOne(Long id) {
        return artistMapper.map(artistRepository.findById(id));
    }

    //Map from ArtistDto to Artist
    @Override
    public ArtistDto createArtist(ArtistDto artist) {
        if(artist.getName().isEmpty())
            throw new RuntimeException();

        return artistMapper.map(artistRepository.save(artistMapper.map(artist)));
    }

    @Override
    public void delete(Long id) {
        if(artistRepository.existsById(id))
            artistRepository.deleteById(id);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Id " + id + " not found.");
    }

    @Override
    public ArtistDto replace(Long id, ArtistDto artistDto) {
        if(artistDto.getName().isEmpty())
            throw new RuntimeException("Name cannot be empty");

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
    @Override
    public ArtistDto update(Long id, ArtistDto artistDto) {
        Optional<Artist> artist = artistRepository.findById(id);
        if(artist.isPresent()) {
            Artist updatedArtist = artist.get();
            if(artistDto.getName() != null) {
                updatedArtist.setName(artistDto.getName());
            }
            return artistMapper.map(artistRepository.save(updatedArtist));
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id " + id + " not found.");








    }

}
