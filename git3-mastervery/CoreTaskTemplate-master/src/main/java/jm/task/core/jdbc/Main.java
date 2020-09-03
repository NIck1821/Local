package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.createUsersTable();
        userDaoHibernate.saveUser("a", "g", (byte) 25);
        userDaoHibernate.saveUser("b", "v", (byte) 30);
        userDaoHibernate.saveUser("v", "b", (byte) 41);
        userDaoHibernate.saveUser("g", "a", (byte) 56);
        for (int i = 0; i < 4; i++) {
            System.out.println(userDaoHibernate.getAllUsers().get(i).getName());
        }
        userDaoHibernate.cleanUsersTable();
        userDaoHibernate.dropUsersTable();
    }
}
