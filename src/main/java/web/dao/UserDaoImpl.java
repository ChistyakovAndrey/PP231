package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    LocalContainerEntityManagerFactoryBean entityManagerFactory;
    @Autowired
    public UserDaoImpl(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    private EntityManager getManager() {
        return entityManagerFactory.getObject().createEntityManager();
    }


    @Override
    public List<User> getAllUsers() {
        return getManager().createQuery("select u FROM User u").getResultList();
    }
    @Override
    public void addUser(User user) {
        entityManager.merge(user);
        entityManager.flush();
    }

    @Override
    public User getUserById(Integer id) {
        String query = "from User where id = " + id;
        List<User> list = entityManager.createQuery(query).getResultList();
        return list.get(0);
    }

    @Override
    public void deleteUserById(int id) {
        String query = "delete from User where id = " + id;
        entityManager.createQuery(query).executeUpdate();
    }
}
