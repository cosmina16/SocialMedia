package lab.lab.repo;

import lab.lab.domain.Friendship;
import lab.lab.domain.StatusFriendship;
import lab.lab.domain.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class RepoFriendshipDB extends RepoDBAbstract<Friendship>{
    public RepoFriendshipDB(String url, String username, String password) {
        super(url, username, password);
    }

    @Override
    public void create(Friendship e) {
        String sql = "INSERT INTO friendships (id_user1, id_user2, data, status) VALUES (?, ?, ?, ?)";
        try(Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = connection.prepareStatement(sql);){
                ps.setInt(1, e.getIdUser1());
                ps.setInt(2, e.getIdUser2());
                ps.setDate(3, java.sql.Date.valueOf(e.getData()));
                ps.setString(4, e.getStatus().name());
                ps.executeUpdate();
        }
        catch (SQLException ex){
            throw  new RuntimeException(ex);
        }
    }

    @Override
    public void update(int id, Friendship e) {
        String sql ="UPDATE friendships SET id_user1 = ?, id_user2 = ?, data = ?, status = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url,username,password);
             PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setInt(1, e.getIdUser1());
            ps.setInt(2, e.getIdUser2());
            ps.setDate(3, java.sql.Date.valueOf(e.getData()));
            ps.setString(4, e.getStatus().name());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Collection<Friendship> getAll() {
        Set<Friendship> friendships = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url,username,password);
             PreparedStatement ps = connection.prepareStatement("SELECT * from friendships");) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int id_user1 = resultSet.getInt("id_user1");
                int id_user2 = resultSet.getInt("id_user2");
                LocalDate data = resultSet.getDate("data").toLocalDate();
                StatusFriendship status = StatusFriendship.valueOf(resultSet.getString("status"));

                Friendship f = new Friendship(id_user1, id_user2, data, status);
                f.setId(id);
                friendships.add(f);
            }
            return friendships;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Friendship get(int id) {
        String sql ="SELECT * FROM friendships WHERE id = ?";
        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement ps = connection.prepareStatement(sql);){
            ps.setInt(1,id);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();

            int id_user1 = resultSet.getInt("id_user1");
            int id_user2 = resultSet.getInt("id_user2");
            LocalDate data = resultSet.getDate("data").toLocalDate();
            StatusFriendship status = StatusFriendship.valueOf(resultSet.getString("status"));

            Friendship friendship = new Friendship(id_user1, id_user2, data, status);
            friendship.setId(id);

            return friendship;

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sql ="DELETE FROM friendship WHERE id = ?";
        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
