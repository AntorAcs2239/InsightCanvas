package Models;
public class Comments {
    private String pid;
    private String description;

    public Comments(String pid, String description) {
        this.pid = pid;
        this.description = description;
    }
    public Comments() {
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}