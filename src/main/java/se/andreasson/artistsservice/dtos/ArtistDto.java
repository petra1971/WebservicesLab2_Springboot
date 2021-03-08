package se.andreasson.artistsservice.dtos;

public class ArtistDto {

    private long id;
    private String name;
    private String genre;


    public ArtistDto() {}

    public ArtistDto(long id, String name, String genre) {
        this.id = id;
        this.name = name;
        this.genre = genre;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "ArtistDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}

