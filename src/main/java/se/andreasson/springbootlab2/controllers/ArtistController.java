package se.andreasson.springbootlab2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.andreasson.springbootlab2.dtos.ArtistDto;
import se.andreasson.springbootlab2.services.ArtistService;
import se.andreasson.springbootlab2.services.Service;

import java.util.List;

@RestController
public class ArtistController {

    private final Service service;

    @Autowired
    public ArtistController(Service service) {
        this.service = service;
    }

    @GetMapping("/artists")
    public List<ArtistDto> all() {
        return service.getAllArtists();
    }

    @GetMapping("/artists/{id}")
    public ArtistDto one(@PathVariable Long id) {
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

