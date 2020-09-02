package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try {
            Session session = Util.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            String sql = "CREATE TABLE Users (Id INT PRIMARY KEY AUTO_INCREMENT, Name VARCHAR(20), LastName VARCHAR(20), Age TINYINT)";
            session.createSQLQuery(sql).executeUpdate();
            tx1.commit();
            session.close();
        } catch (Exception e) {

        }
    }

    @Override
    public void dropUsersTable() {
        try {
            Session session = Util.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            String sql = "DROP TABLE Users";
            session.createSQLQuery(sql).executeUpdate();
            tx1.commit();
            session.close();
        } catch (Exception e) {

        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(new User(name, lastName, age));
        tx1.commit();
        session.close();
        System.out.println("пользователь " + name + "  "+ lastName +"  "+ age + " сохранен");
    }

    @Override
    public void removeUserById(long id) {
            Session session = Util.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.delete(session.load(User.class, id));
            tx1.commit();
            session.close();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = (List<User>) Util.getSessionFactory().openSession().createQuery("From User").list();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        String sql = "delete from Users";
        session.createSQLQuery(sql).executeUpdate();
        tx1.commit();
        session.close();
    }
}
