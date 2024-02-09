package Models;

public class Content {
    private String title;
    private String description;
    private String image;
    public Content() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Content(String title, String description, String image) {
        super();
        this.title = title;
        this.description = description;
        this.image = image;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
}