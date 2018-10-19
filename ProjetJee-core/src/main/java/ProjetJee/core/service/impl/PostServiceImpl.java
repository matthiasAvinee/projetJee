package ProjetJee.core.service.impl;

import ProjetJee.core.dao.PostDAO;
import ProjetJee.core.entity.Cat;
import ProjetJee.core.entity.Post;
import ProjetJee.core.entity.User;
import ProjetJee.core.service.PostService;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@Transactional
public class PostServiceImpl implements PostService {

    @Inject
    private PostDAO postDAO;

    public List<Post> findAll() {
        return postDAO.findAll();
    }

    public List<Post> findByCat(Cat cat) {
        return postDAO.findByCat(cat);
    }

    public List<Post> findByUser(User user) {
        return postDAO.findByUser(user);
    }

    public Post findById(Long id) {return postDAO.findById(id);}

    public void savePost(Post post) {  postDAO.save(post); }

    public void saveFavourite(long userId, long catId) {
        postDAO.saveFavourite(userId, catId);
    }

    public List<Post> findByUsersFans(User user) {
        return postDAO.findByUsersFans(user);
    }

    public List<Post> findHomePost(User user) {
        List<Post> listeTotale=this.findAll();
        List<Post> listFav=this.findByUsersFans(user);

        for(int i=0;i<listeTotale.size();i++)
        {
            for(int j=0;j<listFav.size();i++)
            {
                if(listeTotale.get(i).getId()==listFav.get(j).getId())
                {
                    listeTotale.remove(i);
                }
            }
        }
        return listeTotale;

    }

}
