package web.life.forms.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.life.forms.jpa.CategoriaRepository;
import web.life.forms.model.Categoria;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService implements ICategoriaService{

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public Categoria findById(Integer id) {
        Categoria categoria = null;
        Optional<Categoria> optCategoria = categoriaRepository.findById(id);
        if (optCategoria.isPresent()) {
            categoria = optCategoria.get();
        } else {
            System.out.println("No se ha encontrado la categoría");
        }
        return categoria;
    }

    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria searchByName(String name) {
        System.out.println(categoriaRepository.findCategoriaByNombre(name));
        return categoriaRepository.findCategoriaByNombre(name);
    }

    @Override
    public void save(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    @Override
    public void deleteAll() {
        categoriaRepository.deleteAll();
    }

    @Override
    public void delete(Integer id) {
        categoriaRepository.deleteById(id);
    }
}
