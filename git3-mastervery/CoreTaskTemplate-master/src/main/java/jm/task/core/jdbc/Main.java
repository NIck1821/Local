package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Nick", "Mikheenkov", (byte) 25);
        userService.saveUser("Alex", "Vologda", (byte) 30);
        userService.saveUser("Sasha", "Richard", (byte) 41);
        userService.saveUser("Kolya", "Rodionov", (byte) 56);
        for (int i = 0; i < 4; i++) {
            System.out.println(userService.getAllUsers().get(i).getLastName());
        }
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        System.out.println(userDaoHibernate.searchUserByName("Alex"));
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
