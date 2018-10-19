package ProjetJee.core.dao;

import ProjetJee.core.entity.Cat;
import ProjetJee.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatDAO extends JpaRepository<Cat, Long> {

    List<Cat> findByUser(User user);

    Cat findById(long id);

}
