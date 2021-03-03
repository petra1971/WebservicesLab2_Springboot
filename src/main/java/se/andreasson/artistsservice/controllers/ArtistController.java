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

//   Lägg även till möjlighet att söka med GET search=searchterm:
//    /artists/search?name=kula
//    När spring ser att det i urlen finns en parameter som heter name så
//    letar den efter parametrar till metoden som ska hantera anropet och
//    ser om någon av dom är annoterad med requestParam och heter samma som url parametern name
//     @GetMapping ("/persons/search") - den delen av urlen gör att metoden körs.
//    Men ska du komma åt själva urlparameterns värde så skapar du en parameter på metoden
//    som heter String name. name mappas mot name automatiskt

//    The @PathVariable annotation is used for data passed in the URI (e.g. RESTful web services)
//    while @RequestParam is used to extract the data found in query parameters.

    @GetMapping("/artists/search")
    public List<ArtistDto> allByName(@RequestParam String name) {
    log.info("A request for artists by name has been received: " + name);
    var result = service.getAllByName(name);
    if(!result.isEmpty())
        return result;
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Name " + name + " not found.");
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

