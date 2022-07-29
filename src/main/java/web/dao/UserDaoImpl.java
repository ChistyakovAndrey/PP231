package web.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    LocalContainerEntityManagerFactoryBean entityManagerFactory;

    private EntityManager getManager() {
        return entityManagerFactory.getObject().createEntityManager();
    }


    @Override
    public List<User> getAllUsers() {
        return getManager().createQuery("select u FROM User u").getResultList();
    }

    @Override
    public void addUser(User user) {
        entityManager = getManager();
        entityManager.merge(user);
        entityManager.getTransaction().begin();
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    @Override
    public User getUserById(Integer id) {
        entityManager = getManager();
        String query = "from User where id = " + id;
        List<User> list = entityManager.createQuery(query).getResultList();
        return list.get(0);
    }

    @Override
    public void deleteUserById(int id) {
        entityManager = getManager();
        String query = "delete from User where id = " + id;
        entityManager.getTransaction().begin();
        entityManager.createQuery(query).executeUpdate();
        entityManager.getTransaction().commit();
    }
}
