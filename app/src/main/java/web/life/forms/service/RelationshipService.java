package web.life.forms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.life.forms.jpa.PostRepository;
import web.life.forms.jpa.TagRepository;
import web.life.forms.model.Post;
import web.life.forms.model.Tag;

@Service
public class RelationshipService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private PostRepository postRepository;

    public void saveTag(Tag tag) {
        tagRepository.save(tag);
    }

    public void savePost(Post post) {
        postRepository.save(post);
    }

    public Tag findByTagName(String name) {
        return tagRepository.findTagName(name);
    }

    public Post findByPostTitle(String title) {
        return postRepository.findByPostTitle(title);
    }
}
