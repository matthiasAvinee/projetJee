package ProjetJee.core.dao;

import ProjetJee.core.entity.Cat;
import ProjetJee.core.entity.Post;
import ProjetJee.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostDAO extends JpaRepository<Post, Long> {

    List<Post> findByCat(Cat cat);

    List<Post> findByUser(User user);

}
