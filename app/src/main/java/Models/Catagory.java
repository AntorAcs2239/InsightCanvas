package Models;

import java.util.List;

public class Catagory {
    private int catId;

    private String catName;
    private int cntCat;
    private List<Post> posts;

    public Catagory() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Catagory(int catId, String catName, int cntCat, List<Post> posts) {
        super();
        this.catId = catId;
        this.catName = catName;
        this.cntCat = cntCat;
        this.posts = posts;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public int getCntCat() {
        return cntCat;
    }

    public void setCntCat(int cntCat) {
        this.cntCat = cntCat;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "Catagory [catId=" + catId + ", catName=" + catName + ", cntCat=" + cntCat + ", posts=" + posts + "]";
    }
}