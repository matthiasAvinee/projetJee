package ProjetJee.core.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date date;

    private String title;

    private String description;

    private String path;


    @ManyToOne
    private Cat cat;

    @ManyToOne
    private User user;

}
