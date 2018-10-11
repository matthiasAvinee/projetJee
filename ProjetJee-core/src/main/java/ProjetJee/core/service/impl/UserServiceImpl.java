package ProjetJee.core.service.impl;

import ProjetJee.core.dao.UserDAO;
import ProjetJee.core.entity.User;
import ProjetJee.core.security.CryptageMdp;
import ProjetJee.core.service.UserService;
import org.springframework.transaction.annotation.Transactional;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

import static ProjetJee.core.security.CryptageMdp.validerMotDePasse;


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
    } //faire return User

    public User findById(long id) {
        return userDAO.findById(id);
    }

    public boolean checkPasseword(String nomUser, String passewordAttempt)
    {
       // CryptageMdp cryptageMdp= new CryptageMdp();
       // User userToTry = this.findByPseudo(nomUser);
       // return validerMotDePasse(passewordAttempt,userToTry.getPassword());
        return true;
    }
}
