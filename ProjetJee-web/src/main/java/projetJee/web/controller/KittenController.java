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
import javax.servlet.http.HttpServletResponse;
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
    @Inject
    private HttpServletResponse httpServletResponse;


    @RequestMapping(value = "/user/home", method = RequestMethod.GET)
    public String getAllKitties(ModelMap model, HttpServletRequest rq) {
        final List<Post> allPost = postService.findAll();
        String email = rq.getSession().getAttribute("userConnecte").toString();
        model.addAttribute("user", userService.findByEmail(email));
        model.addAttribute("posts", allPost);
        httpServletResponse.setHeader("Content-type","text/html;charset=UTF-8");
        return "home";
    }

    @RequestMapping(value = "/deconnexion", method = RequestMethod.GET)
    public String deconnexion(HttpServletRequest rq) {
        rq.getSession().removeAttribute("userConnecte");
        return "redirect:/connexion";
    }

    @RequestMapping(value = "/connexion", method = RequestMethod.GET)
    public String showConnexion(ModelMap model) {
        model.addAttribute("user", new User());
        httpServletResponse.setHeader("Content-type","text/html;charset=UTF-8");
        return "connexion";
    }

    @RequestMapping(value = "/connexion", method = RequestMethod.POST)
    public String connect(@ModelAttribute("user") User user, HttpServletRequest rq) {
        if (userService.checkPasseword(user.getEmail(), user.getPassword())) {
            LOGGER.info("connexion success");
            User userConnected = userService.findByEmail(user.getEmail());
            rq.getSession().setAttribute("userConnecte", userConnected.getEmail());
            return "redirect:/user/home";
        } else {
            LOGGER.info("connexion failed");
            return "redirect:connexion";
        }
    }

    @RequestMapping(value = "/")
    public String redirectConnexion () {
        return "redirect:connexion";
    }

    @RequestMapping(value = "/user/ajouterPost", method = RequestMethod.GET)
    public String showAddPost(ModelMap model) {
        model.addAttribute("post", new Post());
        httpServletResponse.setHeader("Content-type","text/html;charset=UTF-8");
        return "ajoutPost";
    }

    @RequestMapping(value = "/user/ajouterPost", method = RequestMethod.POST)
    public String addPost(@ModelAttribute("post") Post post) {
        postService.savePost(post);
        return "redirect:home";
    }

    @RequestMapping(value = "/creationCompte", method = RequestMethod.GET)
    public String showCreation(ModelMap model) {
        model.addAttribute("user", new User());
        httpServletResponse.setHeader("Content-type","text/html;charset=UTF-8");
        return "creerUnCompte";
    }

    @RequestMapping(value = "/creationCompte", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user, HttpServletRequest rq) {
        userService.saveUser(user);
        rq.getSession().setAttribute("userConnecte", user.getPseudo());
        return "redirect:/user/home";
    }

    @RequestMapping(value = "/user/{id}/choisirUnChat", method = RequestMethod.GET)
    public String chooseCat(ModelMap model, @PathVariable("id") long id) {
        final List<Cat> allUserCat = catService.findByUser(userService.findById(id));
        model.addAttribute("userCats", allUserCat);
        httpServletResponse.setHeader("Content-type","text/html;charset=UTF-8");
        return "chooseCat";
    }

    @RequestMapping(value = "/user/{id}/choisirUnChat", method = RequestMethod.POST)
    public String addCat(@ModelAttribute("newCat") Cat cat) {
        catService.saveCat(cat);
        long id = cat.getId();
        return "redirect:ajoutPost";
    }
}
