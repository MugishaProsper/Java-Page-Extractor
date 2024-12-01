package models;

public class Link {
    private int id;       // Unique identifier (used for database representation)
    private String url;   // The URL of the link

    // Constructor without ID (used when inserting new links into the database)
    public Link(String url) {
        this.url = url;
    }

    // Constructor with ID (used when retrieving links from the database)
    public Link(int id, String url) {
        this.id = id;
        this.url = url;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                ", url='" + url + '\'' +
                '}';
    }
}
