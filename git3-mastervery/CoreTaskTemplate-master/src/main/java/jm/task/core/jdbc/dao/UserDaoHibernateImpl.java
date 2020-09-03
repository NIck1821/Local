package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        sessionFactory = Util.getSessionFactory();
    }

    @Override
    public void createUsersTable() {
        Transaction tx1 = null;
        try (Session session = sessionFactory.openSession();) {
            tx1 = session.beginTransaction();
            String sql = "CREATE TABLE Users (Id INT PRIMARY KEY AUTO_INCREMENT, Name VARCHAR(20), LastName VARCHAR(20), Age TINYINT)";
            session.createSQLQuery(sql).executeUpdate();
            tx1.commit();
        } catch (HibernateException e) {
            tx1.rollback();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction tx1 = null;
        try (Session session = sessionFactory.openSession();) {
            tx1 = session.beginTransaction();
            String sql = "DROP TABLE Users";
            session.createSQLQuery(sql).executeUpdate();
            tx1.commit();
        } catch (HibernateException e) {
            tx1.rollback();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction tx1 = null;
        try (Session session = sessionFactory.openSession()) {
            tx1 = session.beginTransaction();
            session.save(new User(name, lastName, age));
            tx1.commit();
            System.out.println("пользователь " + name + "  " + lastName + "  " + age + " сохранен");
        } catch (HibernateException e) {
            tx1.rollback();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            User user = (User) session.load(User.class, id);
            session.delete(user);
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
        }
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) sessionFactory.openSession().createQuery("From User").list();
    }

    @Override
    public void cleanUsersTable() {
        Transaction tx1 = null;
        try (Session session = sessionFactory.openSession()) {
            tx1 = session.beginTransaction();
            String sql = "delete from Users";
            session.createSQLQuery(sql).executeUpdate();
            tx1.commit();
        } catch (HibernateException e) {
            tx1.rollback();
        }
    }
}
