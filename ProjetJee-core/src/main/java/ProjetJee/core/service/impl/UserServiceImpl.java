package ProjetJee.core.service.impl;

import ProjetJee.core.dao.UserDAO;
import ProjetJee.core.entity.Post;
import ProjetJee.core.entity.User;
import ProjetJee.core.security.CryptageMdp;
import ProjetJee.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;



@Named
@Transactional
public class UserServiceImpl implements UserService {

    @Inject
    private UserDAO userDAO;

    public void saveUser(User user) {
        CryptageMdp cryptageMdp= new CryptageMdp();
        user.setPassword(cryptageMdp.genererMotDePasse(user.getPassword()));
        userDAO.save(user);
    }

    public List<User> findByPseudo(String pseudo) {
        return userDAO.findByPseudo(pseudo);
    }

    public User findById(long id) {
        return userDAO.findById(id);
    }

    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    public boolean checkPasseword(String email, String passewordAttempt)
    {
       CryptageMdp cryptageMdp= new CryptageMdp();
       try
       {
           User userToTry = this.findByEmail(email);
           return cryptageMdp.validerMotDePasse(passewordAttempt,userToTry.getPassword());
       }
       catch (NullPointerException e)
       {
           return false;
       }


    }

    public void addFavorite(Post post, User user) {
        user.getFavouritesPosts().add(post);
        userDAO.save(user);
    }

    public void deleteFavorite(Post post, User user) {
        List<Post> lst =user.getFavouritesPosts();
        for(int i=0;i<lst.size();i++)
        {
            if(lst.get(i).getId()==post.getId())
            {
                user.getFavouritesPosts().remove(i);
                break;
            }
        }

        userDAO.save(user);
    }

}
