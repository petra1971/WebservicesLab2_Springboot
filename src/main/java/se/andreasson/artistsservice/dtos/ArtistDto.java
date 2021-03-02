package se.andreasson.artistsservice.dtos;

public class ArtistDto {

    private long id;
    private String name;

    public ArtistDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getArtistId() {
        return id;
    }

    public void setArtistId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
