package projetJee.web.controller;


import ProjetJee.core.entity.Post;
import ProjetJee.core.entity.User;
import ProjetJee.core.service.PostService;
import ProjetJee.core.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class KittenController {

    private UserService userService;
    private PostService postService;

    @RequestMapping(value = "/home")
    public String getAllKitties() {

        return "home";
    }

    @RequestMapping(value = "/")
    public String getListUserAutorised() {

        return "connexion";
    }

    @RequestMapping(value = "/ajouterPost", method = RequestMethod.POST)
    public String addPost(@ModelAttribute("post")Post post) {
        postService.savePost(post);
        return "ajoutPost";
    }

    @RequestMapping(value = "/creationCompte", method = RequestMethod.GET)
    public String showCreation() {

        return "creerUnCompte";
    }

    @RequestMapping(value = "/creationCompte", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user")User user) {
        userService.saveUser(user);
        return "creerUnCompte";
    }

}
