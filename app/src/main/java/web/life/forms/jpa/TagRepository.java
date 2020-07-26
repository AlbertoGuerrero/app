package web.life.forms.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import web.life.forms.model.Categoria;
import web.life.forms.model.Tag;

import javax.transaction.Transactional;
import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    @Query( "select t from Tag t join fetch t.posts where t.id = ?1" )
    public Tag obtenerTodosLosPostsDelTag(int id);

    @Query( value = "SELECT pt.post_id FROM Post_Tag pt JOIN Post p ON pt.post_id = p.id JOIN post_tag pt2 ON p.id = pt2.post_id WHERE pt2.tag_id = ?1 GROUP BY pt.post_id HAVING count(pt.tag_id) = 1",
            nativeQuery = true )
    public List<Integer> obtenerPostsAsignadosAUnSoloTag(int id);

    @Transactional
    @Modifying
    @Query( value = "DELETE FROM Post_Tag WHERE tag_id = ?1", nativeQuery=true)
    public void eliminarAsociacionesParaEsteTag( int id );

    @Transactional
    @Modifying
    @Query( "DELETE FROM Post p WHERE p.id IN (:listaIds)" )
    public void eliminarTodosLosPostsAsociadosUnicamenteAlTag(@Param("listaIds") List<Integer> listaIds);

    @Query("SELECT t FROM Tag t WHERE t.name = :name")
    public Tag findTagName(@Param("name")String name);
}
