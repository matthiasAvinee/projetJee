package ProjetJee.core.service;

import ProjetJee.core.entity.Cat;
import ProjetJee.core.entity.User;

import java.util.List;

public interface CatService {

    List<Cat> findByUser(User user);

    void saveCat(Cat cat);

    Cat findById(long id);

}
