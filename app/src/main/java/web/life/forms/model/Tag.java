package web.life.forms.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "Tag" )
public class Tag {

    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY )
    private int id;

    private String name;

    // Con FetchType.EAGER carga todos los elementos
    @ManyToMany( fetch = FetchType.EAGER,
            cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH } )
    @JoinTable( name = "Post_Tag", joinColumns = @JoinColumn( name = "tag_id" ),
            inverseJoinColumns = @JoinColumn( name = "post_id" ) )
    private List<Post> posts = new ArrayList<>();

    public Tag() {

    }

    public Tag(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "Tag [id=" + id + ", name=" + name + "]";
    }
}