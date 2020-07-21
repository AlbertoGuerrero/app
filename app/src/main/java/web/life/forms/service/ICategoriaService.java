package web.life.forms.service;

import web.life.forms.model.Categoria;

public interface ICategoriaService {
    public Categoria findById(Integer id);
    public Categoria searchByName(String name);
    public void save(Categoria categoria);
    public void delete(Integer id);
}
