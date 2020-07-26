package web.life.forms.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import web.life.forms.model.Post;
import web.life.forms.model.Tag;

import javax.transaction.Transactional;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query( "select p from Post p join fetch p.tags where p.id = ?1" )
    public Post obtenerTodosLosTagsDelPost(int id);

    @Query( value = "SELECT pt.tag_id FROM Post_Tag pt JOIN Tag t ON pt.tag_id = t.id JOIN post_tag pt2 ON t.id = pt2.tag_id WHERE pt2.post_id = ?1 GROUP BY pt.tag_id HAVING count(pt.post_id) = 1",
            nativeQuery = true )
    public List<Integer> obtenerTagsAsignadosAUnSoloPost(int id);

    @Transactional
    @Modifying
    @Query( value = "DELETE FROM Post_Tag WHERE post_id = ?1", nativeQuery=true)
    public void eliminarAsociacionesParaEstePost(int id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Tag t WHERE t.id IN (:listaIds)")
    public void eliminarTodosLosTagsAsociadosUnicamenteAlPost(@Param("listaIds") List<Integer> listaIds);

    @Query("SELECT p FROM Post p WHERE p.title = :title")
    public Post findByPostTitle(@Param("title")String title);
}