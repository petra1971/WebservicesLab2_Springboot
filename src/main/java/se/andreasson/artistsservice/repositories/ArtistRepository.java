package se.andreasson.artistsservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.andreasson.artistsservice.entities.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    public Artist findAllByName(String name);
}
