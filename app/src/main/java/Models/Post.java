package Models;

import com.google.firebase.Timestamp;

public class Post {
    private String pid;
    private String uid;
    private String title;
    private String description;
    private com.google.firebase.Timestamp timestamp;
    private String image;
    private String category;
    private int cntPositive;
    private int cntNegative;
    private int cntNeutral;
    public Post() {
        super();
    }

    public Post(String pid, String uid, String title, String description, Timestamp timestamp, String image, String category, int cntPositive, int cntNegative, int cntNeutral) {
        this.pid = pid;
        this.uid = uid;
        this.title = title;
        this.description = description;
        this.timestamp = timestamp;
        this.image = image;
        this.category = category;
        this.cntPositive = cntPositive;
        this.cntNegative = cntNegative;
        this.cntNeutral = cntNeutral;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCntPositive() {
        return cntPositive;
    }

    public void setCntPositive(int cntPositive) {
        this.cntPositive = cntPositive;
    }

    public int getCntNegative() {
        return cntNegative;
    }

    public void setCntNegative(int cntNegative) {
        this.cntNegative = cntNegative;
    }

    public int getCntNeutral() {
        return cntNeutral;
    }

    public void setCntNeutral(int cntNeutral) {
        this.cntNeutral = cntNeutral;
    }
}