package ProjetJee.core.service;

import ProjetJee.core.entity.Cat;
import ProjetJee.core.entity.Post;
import ProjetJee.core.entity.User;

import java.util.List;

public interface PostService {

    List<Post> findAll();

    List<Post> findByCat(Cat cat);

    List<Post> findByUser(User user);

    Post findById(Long id);

    void savePost(Post post);

    void saveFavourite(long userId, long catId);

    List<Post> findByUsersFans (User user);

    List<Post> findHomePost(User user);

}
