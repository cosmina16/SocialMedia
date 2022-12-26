package lab.lab.repo;

import lab.lab.domain.User;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class RepoUserDB extends RepoDBAbstract<User>{

    public RepoUserDB(String url, String username, String password) {
        super(url, username, password);
    }

    @Override
    public void create(User e) {
        String sql = "INSERT INTO users (name, age, password) VALUES (?, ?, ?)";
        try(Connection connection = DriverManager.getConnection(url,username,password);
        PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setString(1, e.getName());
            ps.setInt(2, e.getAge());
            ps.setString(3,e.getPassword());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(int id, User e) {
        String sql ="UPDATE users SET name = ?, age = ?, password = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url,username,password);
             PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setString(1, e.getName());
            ps.setInt(2, e.getAge());
            ps.setString(3,e.getPassword());
            ps.setInt(4,id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Collection<User> getAll() {
        Set<User> users = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url,username,password);
             PreparedStatement ps = connection.prepareStatement("SELECT * from users");) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String pass = resultSet.getString("password");

                User utilizator = new User(name, age, pass);
                utilizator.setId(id);
                users.add(utilizator);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User get(int id) {
        String sql ="SELECT * FROM users WHERE id = ?";
        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement ps = connection.prepareStatement(sql);){
            ps.setInt(1,id);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();

            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            String pass = resultSet.getString("password");

            User utilizator = new User(name, age, pass);
            utilizator.setId(id);

            return utilizator;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sql ="DELETE FROM users WHERE id = ?";
        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
