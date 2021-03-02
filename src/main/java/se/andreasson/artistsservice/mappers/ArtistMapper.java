package se.andreasson.artistsservice.mappers;

import org.springframework.stereotype.Component;
import se.andreasson.artistsservice.dtos.ArtistDto;
import se.andreasson.artistsservice.entities.Artist;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ArtistMapper {

    public ArtistMapper() {
    }

    public ArtistDto map(Artist artist) {
        return new ArtistDto(artist.getId(), artist.getName());
    }

    public Artist map(ArtistDto artistDto) {
        return new Artist(artistDto.getArtistId(), artistDto.getName());
    }

    public Optional<ArtistDto> map(Optional<Artist> optionalArtist) {
        if (optionalArtist.isEmpty())
            return Optional.empty();
        return Optional.of(map(optionalArtist.get()));
    }

    public List<ArtistDto> map(List<Artist> all) {
        List<ArtistDto> artistDtoList = new ArrayList<ArtistDto>();
        for (var artist : all) {
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