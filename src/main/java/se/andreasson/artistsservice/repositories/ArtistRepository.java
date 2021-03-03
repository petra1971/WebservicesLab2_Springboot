package se.andreasson.artistsservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.andreasson.artistsservice.entities.Artist;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    public List<Artist> findAllByName(String name);

    public List<Artist> findAllByNameContains(String name);
}
