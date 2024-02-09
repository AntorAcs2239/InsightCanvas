package Models;

import java.util.List;

public class User {
    private int uid;
    private String name;
    private String email;
    private String password;
    private String phone;

    private String job;

    private String image;
    private List<Post> posts;

    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

    public User(int uid, String name, String email, String password, String phone, String job, String image,
                List<Post> posts) {
        super();
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.job = job;
        this.image = image;
        this.posts = posts;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "User [uid=" + uid + ", name=" + name + ", email=" + email + ", password=" + password + ", phone="
                + phone + ", job=" + job + ", image=" + image + ", posts=" + posts + "]";
    }
}