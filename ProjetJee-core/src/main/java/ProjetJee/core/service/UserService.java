package ProjetJee.core.service;

import ProjetJee.core.entity.User;

import java.util.List;

public interface UserService {

    void saveUser (User user);

    List<User> findByPseudo(String pseudo);

}
