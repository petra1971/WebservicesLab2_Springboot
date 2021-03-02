package se.andreasson.artistsservice.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.andreasson.artistsservice.configurations.TestConfiguration;
import se.andreasson.artistsservice.dtos.ArtistDto;
import se.andreasson.artistsservice.services.Service;

import java.util.List;

@RestController
public class ArtistController {

    private final Service service;
    private TestConfiguration testConfiguration;
    Logger log = LoggerFactory.getLogger(ArtistController.class);

    @Autowired
    public ArtistController(TestConfiguration testConfiguration, Service service) {
        this.testConfiguration = testConfiguration;
        this.service = service;
    }

    @GetMapping("/message")
    public String message() {
        return testConfiguration.getFoo();
    }

    @GetMapping("/artists")
    public List<ArtistDto> all() {
        return service.getAllArtists();
    }

    @GetMapping("/artists/{id}")
    public ArtistDto one(@PathVariable Long id) {
        log.info("For your info, a request for artist with specific id has been received: " + id);
        return service.getOne(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id " + id + " not found."));

//        var result = artistRepository.findById(artistId);
//        if(result.isPresent())
//            return result.get();
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Id " + artistId + " not found.");
        }

    @PostMapping("/artists")
    @ResponseStatus(HttpStatus.CREATED)
    public ArtistDto create(@RequestBody ArtistDto artist) {
        return (service.createArtist(artist));
    }

    @DeleteMapping("/artists/{id}")
//    @ResponseStatus(HttpStatus.FOUND)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
    
    @PutMapping("artists/{id}")
    public ArtistDto replace(@PathVariable Long id,
                             @RequestBody ArtistDto artistDto) {
        return service.replace(id, artistDto);
    }

    @PatchMapping("/artists/{id}")
    public ArtistDto update(@PathVariable Long id,
                            @RequestBody ArtistDto artistDto) {

        return service.update(id, artistDto);
    }
}
