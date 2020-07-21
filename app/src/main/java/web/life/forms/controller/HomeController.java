package web.life.forms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import web.life.forms.model.Categoria;
import web.life.forms.service.CategoriaService;
import web.life.forms.service.ICategoriaService;

@Controller
public class HomeController {

    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping("/")
    public String showHome() {
        // PRUEBAS
        /*
        // Insertar
        Categoria categoria = new Categoria();
        categoria.setNombre("nombre1");
        categoria.setDescripcion("descripcion1");

        Categoria categoria2 = new Categoria();
        categoria2.setNombre("nombre2");
        categoria2.setDescripcion("descripcion2");

        // Guardar
        categoriaService.save(categoria);
        categoriaService.save(categoria2);
         */

        // Leer
        Categoria cat = categoriaService.findById(2);
        System.out.println(cat);

        // Actualizar
        cat.setNombre("NuevoNombre");
        cat.setDescripcion("NuevaDescripcion");
        categoriaService.save(cat);
        Categoria catUpdated = categoriaService.findById(2);
        System.out.println(catUpdated);

        // Eliminar
        if (categoriaService.findById(1) != null) {
            categoriaService.delete(1);
        }

        // Buscar por nombre
        Categoria catPorNombre = categoriaService.searchByName("NuevoNombre");
        if (catPorNombre !=null) {
            System.out.println("Se encontró la categoría");
            System.out.println(catPorNombre);
        } else {
            System.out.println("No se encontró la categoría");
        }

        return "home";
    }
}
