package ProjetJee.core.service.impl;

import ProjetJee.core.dao.CatDAO;
import ProjetJee.core.entity.Cat;
import ProjetJee.core.entity.User;
import ProjetJee.core.service.CatService;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@Transactional
public class CatServiceImpl implements CatService {

    @Inject
    private CatDAO catDAO;

    public List<Cat> findByUser(User user) {
        return catDAO.findByUser(user);
    }

    public void saveCat(Cat cat) {
        catDAO.save(cat);
    }

    public Cat findById(long id) {
        return catDAO.findById(id);
    }

    public Boolean isUserCat(User user, Long idCat) {
        Boolean check=false;
        List<Cat> lstCat=findByUser(user);
        for (Cat cat: lstCat) {
            if(cat.getId()==idCat)
            {
                check=true;
                break;
            }
        }
        return check;
    }


}
