package ProjetJee.core.service.impl;

import ProjetJee.core.dao.UserDAO;
import ProjetJee.core.entity.User;
import ProjetJee.core.service.UserService;
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
        userDAO.save(user);
    }

    public List<User> findByPseudo(String pseudo) {
        return userDAO.findByPseudo(pseudo);
    }
}
