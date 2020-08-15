package web.life.forms.service;

import org.springframework.data.domain.Example;
import web.life.forms.model.Categoria;

import java.util.List;

public interface ICategoriaService {
    public Categoria findById(Integer id);
    public List<Categoria> findAll();
    public Categoria searchByName(String name);
    public List<Categoria> findByExample(Example<Categoria> example);
    public void save(Categoria categoria);
    public void deleteAll();
    public void delete(Integer id);
}
