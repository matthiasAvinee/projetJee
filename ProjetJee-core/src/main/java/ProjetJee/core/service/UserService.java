package ProjetJee.core.service;

import ProjetJee.core.entity.User;

import java.util.List;

public interface UserService {

    void saveUser (User user);

    List<User> findByPseudo(String pseudo);

    User findById (long id);

    User findByEmail (String email);

    public boolean checkPasseword(String email, String passewordAttempt);

}
