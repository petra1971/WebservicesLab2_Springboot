package se.andreasson.springbootlab2;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    public Artist findAllByName(String name);
}
