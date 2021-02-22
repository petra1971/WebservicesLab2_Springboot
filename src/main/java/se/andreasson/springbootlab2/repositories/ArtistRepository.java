package se.andreasson.springbootlab2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.andreasson.springbootlab2.entities.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    public Artist findAllByName(String name);
}
