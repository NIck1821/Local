package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    // реализуйте настройку соеденения с БД
    private static final String url = "jdbc:mysql://localhost/store2?serverTimezone=Europe/Moscow&useSSL=false";
    private static final String username = "root";
    private static final String password = "password";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Connection failed ...");
        }
        return connection;
    }

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration config = new Configuration()
                        .setProperty("hibernate.connector.driver_class", "com.mysql.cj.jdbc.Driver")
                        .setProperty("hibernate.connection.url", url)
                        .setProperty("hibernate.connection.username", username)
                        .setProperty("hibernate.connection.password", password)
                        .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect")
                        .setProperty("hibernate.hbm2dll.auto", "create-drop")
                        // .setProperty("hibernate.show_sql",                  "true")
                        .addAnnotatedClass(jm.task.core.jdbc.model.User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
                sessionFactory = config.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                System.out.println("failed : ");
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

}

