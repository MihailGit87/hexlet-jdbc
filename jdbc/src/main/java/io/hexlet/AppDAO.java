package io.hexlet;

import java.sql.DriverManager;
import java.sql.SQLException;

public class AppDAO {
    public static void main(String[] args) throws SQLException {
        try (var conn = DriverManager.getConnection("jdbc:h2:mem:hexlet_test")) {
            var sql = "CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(255), phone VARCHAR(255))";
            try ( var statement = conn.createStatement()) {
                statement.execute(sql);
            }
            var dao = new UserDao(conn);
            var user = new User("Miha", "8964...");
            user.getId();
            dao.save(user);
            System.out.println(user.getId());

            var user2 = dao.find(user.getId()).get();
            System.out.println(user.getId() == user2.getId());

            dao.delete(user.getId());
            System.out.println(user.getId());
        }
    }
}
