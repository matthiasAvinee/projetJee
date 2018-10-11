package projetJee.web.controller;


import ProjetJee.core.entity.Cat;
import ProjetJee.core.entity.Post;
import ProjetJee.core.entity.User;
import ProjetJee.core.service.CatService;
import ProjetJee.core.service.PostService;
import ProjetJee.core.service.UserService;
import javafx.geometry.Pos;
import org.hibernate.annotations.Filter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class KittenController {

    private static final Logger LOGGER = LoggerFactory.getLogger(KittenController.class);

    @Inject
    private UserService userService;
    @Inject
    private PostService postService;
    @Inject
    private CatService catService;



    @RequestMapping(value = "/user/home",method = RequestMethod.GET)
    public String getAllKitties(ModelMap model) {
        final List<Post> allPost=postService.findAll();
        model.addAttribute("posts",allPost);
        return "home";
    }

    @RequestMapping(value = "/deconnexion",method = RequestMethod.GET)
    public String deconnexion(HttpServletRequest rq) {
        rq.getSession().removeAttribute("userConnecte");
        return "redirect:/";
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String showConnexion(ModelMap model) {
        model.addAttribute("user", new User());
        return "connexion";
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public String connect(@ModelAttribute("user")User user, HttpServletRequest rq) {
        User usertoTest= userService.findByEmail(user.getEmail());
        if(userService.checkPasseword(usertoTest.getEmail(),user.getPassword()))
        {
            rq.getSession().setAttribute("userConnecte",user.getPseudo());
        }

        return "redirect:/user/home";
    }

    @RequestMapping(value = "/user/ajouterPost", method = RequestMethod.GET)
    public String showAddPost(ModelMap model) {
        model.addAttribute("post", new Post());
        return "ajoutPost";
    }

    @RequestMapping(value = "/user/ajouterPost", method = RequestMethod.POST)
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
    public String addUser(@ModelAttribute("user")User user, HttpServletRequest rq) {
        userService.saveUser(user);
        rq.getSession().setAttribute("userConnecte",user.getPseudo());
        return "redirect:/user/home";
    }

    @RequestMapping(value="/user/{id}/choisirUnChat",method = RequestMethod.GET)
    public String chooseCat(ModelMap model, @PathVariable("id")long id) {
        final List<Cat> allUserCat=catService.findByUser(userService.findById(id));
        model.addAttribute("userCats",allUserCat);
        return "choisirUnChat";
    }

    @RequestMapping(value = "/user/{id}/choisirUnChat", method = RequestMethod.POST)
    public String addCat(@ModelAttribute("newCat") Cat cat) {
        catService.saveCat(cat);
        long id=cat.getId();
        return "redirect:ajoutPost";
    }
}
