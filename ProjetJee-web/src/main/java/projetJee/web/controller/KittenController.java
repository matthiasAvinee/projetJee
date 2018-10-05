package projetJee.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class KittenController {

    @RequestMapping(value = "/home")
    public String getAllKitties() {

        return "home";
    }

    @RequestMapping(value = "/connexion")

    public String getListUserAutorised() {

        return "connexion";
    }

    @RequestMapping(value = "/ajouterPost")

    public String addPost() {

        return "ajoutPost";
    }

}
