package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.createUsersTable();
        userDaoHibernate.saveUser("a", "fsfdd", (byte) 45);
        userDaoHibernate.saveUser("b", "fsdf", (byte) 45);
        userDaoHibernate.saveUser("v", "fsda", (byte) 45);
        userDaoHibernate.saveUser("g", "fsda", (byte) 45);
        for(int i = 0; i< 4 ; i++){
            System.out.println(userDaoHibernate.getAllUsers().get(i).getName());
        }
        userDaoHibernate.cleanUsersTable();
        userDaoHibernate.dropUsersTable();
    }
}
