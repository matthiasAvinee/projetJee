package projetJee.web.controller;


import ProjetJee.core.entity.Post;
import ProjetJee.core.entity.User;
import ProjetJee.core.service.PostService;
import ProjetJee.core.service.UserService;
import javafx.geometry.Pos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

@Controller
public class KittenController {

    private static final Logger LOGGER = LoggerFactory.getLogger(KittenController.class);

    @Inject
    private UserService userService;
    @Inject
    private PostService postService;

    @RequestMapping(value = "/home")
    public String getAllKitties() {

        return "home";
    }

    @RequestMapping(value = "/")
    public String getListUserAutorised() {

        return "connexion";
    }

    @RequestMapping(value = "/ajouterPost", method = RequestMethod.GET)
    public String showAddPost(ModelMap model) {
        model.addAttribute("post", new Post());
        return "ajoutPost";
    }

    @RequestMapping(value = "/ajouterPost", method = RequestMethod.POST)
    public String addPost(@ModelAttribute("post")Post post) {
        postService.savePost(post);
        return "redirect:home";
    }

    @RequestMapping(value = "/creationCompte", method = RequestMethod.GET)
    public String showCreation(ModelMap model) {
        model.addAttribute("user", new User());
        return "creerUnCompte";
    }

    @RequestMapping(value = "/creationCompte", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user")User user) {
        userService.saveUser(user);
        return "redirect:home";
    }

}
