package ProjetJee.core.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @ManyToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cat")
    private List<Post> posts;

    @ManyToMany(mappedBy = "cat")
    private List<User> usersFans;

    public Cat() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<User> getUsersFans() {
        return usersFans;
    }

    public void setUsersFans(List<User> usersFans) {
        this.usersFans = usersFans;
    }
}
