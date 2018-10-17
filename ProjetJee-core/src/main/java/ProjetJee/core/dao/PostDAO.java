package ProjetJee.core.dao;

import ProjetJee.core.entity.Cat;
import ProjetJee.core.entity.Post;
import ProjetJee.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostDAO extends JpaRepository<Post, Long> {

    List<Post> findByCat(Cat cat);

    List<Post> findByUser(User user);

    @Query(value = "INSERT INTO projet_jee.favourite (user_id, cat_id) VALUES (?1, ?2)", nativeQuery = true)
    void saveFavourite (long userId, long catId);

}
