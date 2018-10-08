package ProjetJee.core.dao;

import ProjetJee.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDAO extends JpaRepository<User, Long> {

    List<User> findByPseudo(String pseudo);

    User findById(Long id);

}
