package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        sessionFactory = Util.getSessionFactory();
    }

    @Override
    public void createUsersTable() {
        Transaction tx1 = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            tx1 = session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS Users (Id INT PRIMARY KEY AUTO_INCREMENT, Name VARCHAR(20), LastName VARCHAR(20), Age INTEGER )";
            session.createSQLQuery(sql).executeUpdate();
            tx1.commit();
        } catch (HibernateException e) {
            tx1.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction tx1 = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            tx1 = session.beginTransaction();
            String sql = "DROP TABLE Users";
            session.createSQLQuery(sql).executeUpdate();
            tx1.commit();
        } catch (Exception e) {
            tx1.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction tx1 = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            tx1 = session.beginTransaction();
            session.save(new User(name, lastName, age));
            tx1.commit();
            System.out.println("пользователь " + name + "  " + lastName + "  " + age + " сохранен");
        } catch (Exception e) {
            tx1.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction tx = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            User user = session.load(User.class, id);
            session.delete(user);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) sessionFactory.openSession().createQuery("From User").list();
    }

    @Override
    public void cleanUsersTable() {
        Transaction tx1 = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            tx1 = session.beginTransaction();
            String sql = "delete from Users";
            session.createSQLQuery(sql).executeUpdate();
            tx1.commit();
        } catch (Exception e) {
            tx1.rollback();
        } finally {
            session.close();
        }
    }

    public String searchUserByName(String name) {
        String returnName = null;
        Transaction tx = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM User where name = :paramName").setParameter("paramName", name);
            List<User> users = query.list();
            returnName = users.get(0).getLastName();
            tx.commit();
        } catch (Exception e) {
            returnName = "Пользователь по имени не найден";
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
        return returnName;
    }
}
