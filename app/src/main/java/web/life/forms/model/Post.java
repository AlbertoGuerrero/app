package web.life.forms.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity()
@Table( name="Post" )
public class Post {

    @Id
    @GeneratedValue( strategy=GenerationType.IDENTITY )
    private int id;

    private String title;

    public Post() {

    }

    public Post( String title ) {
        this.title = title;
    }

    @ManyToMany( fetch = FetchType.LAZY,
            cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH } )
    @JoinTable( name = "post_tag", joinColumns = @JoinColumn( name = "post_id" ),
            inverseJoinColumns = @JoinColumn( name = "tag_id" ) )
    private List<Tag> tags;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }


    public void addTag(Tag tag) {
        if (tags == null) {
            tags = new ArrayList<>();
        }
        tags.add(tag);
    }

    public void removeTag(Tag tag) {

    }


    @Override
    public String toString() {
        return "Post [id=" + id + ", title=" + title + "]";
    }
}