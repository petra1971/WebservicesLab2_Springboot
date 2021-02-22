package se.andreasson.springbootlab2;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    private ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<ArtistDto> getAllArtists() {
        return map(artistRepository.findAll());
    }

    public Optional<ArtistDto> getOne(Long artistId) {
        return map(artistRepository.findById(artistId));
    }

    public ArtistDto createArtist(ArtistDto artist) {
        if(artist.getName().isEmpty())
            throw new RuntimeException();

        return map(artistRepository.save(map(artist)));
    }

    public ArtistDto map(Artist artist) {
        return new ArtistDto(artist.getId(), artist.getName());
    }

    public Artist map(ArtistDto artistDto) {
        return new Artist(artistDto.getArtistId(), artistDto.getName());
    }

    public Optional<ArtistDto> map(Optional <Artist> optionalArtist) {
       if(optionalArtist.isEmpty())
           return Optional.empty();
       return Optional.of(map(optionalArtist.get()));
    }

    public List<ArtistDto> map(List<Artist> all) {
        List<ArtistDto> artistDtoList = new ArrayList<>();
        for (var artist: all ) {
            artistDtoList.add(map(artist));
        }
        return artistDtoList;

//        return all
//                .stream()
//                .filter(artist -> artist.getArtistId() <5)
//                .map(this::map) // Equal to .map(artist -> map(artist))
//                .collect(Collectors.toList());

    }
}
