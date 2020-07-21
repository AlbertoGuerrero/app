package web.life.forms.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import web.life.forms.model.Categoria;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    @Query("SELECT t FROM Categoria t WHERE t.nombre = :nombre")
    Categoria findCategoriaByNombre(@Param("nombre")String nombre);
}
