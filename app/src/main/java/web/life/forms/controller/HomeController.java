package web.life.forms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import web.life.forms.model.Categoria;
import web.life.forms.model.Post;
import web.life.forms.model.Tag;
import web.life.forms.service.ICategoriaService;
import web.life.forms.service.RelationshipService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private ICategoriaService categoriaService;

    @Autowired
    private RelationshipService relationshipService;

    @GetMapping("/")
    public String showHome(Model model) {
        model.addAttribute("message","Bienvenido a App!");
        model.addAttribute("date", new Date());

        return "home";
    }

    @GetMapping("/relationship")
    public String showRelationship(Model model) {
        // PRUEBAS

        // Borrar todos los registros creados
        categoriaService.deleteAll();

        // Insertar
        Categoria categoria1 = new Categoria();
        categoria1.setNombre("nombre1");
        categoria1.setDescripcion("descripcion1");

        Categoria categoria2 = new Categoria();
        categoria2.setNombre("nombre2");
        categoria2.setDescripcion("descripcion2");

        Categoria categoria3 = new Categoria();
        categoria3.setNombre("nombre3");
        categoria3.setDescripcion("descripcion3");

        // Guardar
        categoriaService.save(categoria1);
        categoriaService.save(categoria2);
        categoriaService.save(categoria3);

        // Leer
        // Se buscan en la base de datos
        Categoria c1 = categoriaService.searchByName("nombre1");
        Categoria c2 = categoriaService.searchByName("nombre2");

        // Actualizar
        c2.setNombre("NuevoNombre");
        c2.setDescripcion("NuevaDescripcion");
        categoriaService.save(c2);

        // Eliminar
        if (categoriaService.findById(c1.getId()) != null) {
            categoriaService.delete(c1.getId());
        }

        // Buscar por nombre
        Categoria catPorNombre = categoriaService.searchByName("NuevoNombre");
        if (catPorNombre != null) {
            System.out.println("Se encontró la categoría");
            System.out.println(catPorNombre);
        } else {
            System.out.println("No se encontró la categoría");
        }

        // Buscar todas
        List<Categoria> categorias = categoriaService.findAll();

        model.addAttribute("categorias", categorias);

        // MANY TO MANY
        Tag tag1 = new Tag("Etiqueta1");

        if (relationshipService.findByTagName(tag1.getName()) == null) {
            relationshipService.saveTag(tag1);
        }

        Post post1 = new Post("Post1");
        Post post2 = new Post("Post2");

        post1.addTag(tag1);
        post2.addTag(tag1);

        if (relationshipService.findByPostTitle("Post1") == null) {
            relationshipService.savePost(post1);
        }
        if (relationshipService.findByPostTitle("Post2") == null) {
            relationshipService.savePost(post2);
        }

        Tag tag2 = relationshipService.findByTagName("Etiqueta1");

        model.addAttribute("tag_1", tag2.getName());
        model.addAttribute("post_tag_1", tag2.getPosts());

        // Creamos los posts
        Post post3 = new Post("JPA with Hibernate");
        Post post4 = new Post("Native Hibernate");
        Post post5 = new Post("Post Solitario");

        // Guardamos los post en la base de datos
        if (relationshipService.findByPostTitle("JPA with Hibernate") == null) {
            relationshipService.savePost(post3);
        }
        if (relationshipService.findByPostTitle("Native Hibernate") == null) {
            relationshipService.savePost(post4);
        }
        if (relationshipService.findByPostTitle("Post Solitario") == null) {
            relationshipService.savePost(post5);
        }

        // Creamos las etiquetas
        Tag tag3 = new Tag("Java");
        Tag tag4 = new Tag("Hibernate");
        Tag tag5 = new Tag("Tag Solitario");
        Tag tag6 = new Tag("Tag Solitario2");

        // Añadimos las etiquetas a los posts
        post3.addTag(tag3);
        post3.addTag(tag4);
        post5.addTag(tag5);
        post5.addTag(tag6);

        // Guardamos las etiquetas en la base de datos
        if (relationshipService.findByTagName("Java") == null) {
            relationshipService.saveTag(tag3);
        }
        if (relationshipService.findByTagName("Hibernate") == null) {
            relationshipService.saveTag(tag4);
        }
        if (relationshipService.findByTagName("Tag Solitario") == null) {
            relationshipService.saveTag(tag5);
        }
        if (relationshipService.findByTagName("Tag Solitario2") == null) {
            relationshipService.saveTag(tag6);
        }
        return "relationship";
    }
}
