package se.andreasson.springbootlab2;

public class ArtistDto {

    private long artistId;
    private String name;

    public ArtistDto(long artistId, String name) {
        this.artistId = artistId;
        this.name = name;
    }

    public long getArtistId() {
        return artistId;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
