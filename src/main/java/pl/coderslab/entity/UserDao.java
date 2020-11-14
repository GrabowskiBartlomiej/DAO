package pl.coderslab.entity;

import java.sql.*;
import java.util.Arrays;

import org.mindrot.jbcrypt.BCrypt;


public class UserDao {
    public static final String createUserQuery = "INSERT INTO users(username, email, password) VALUES (?,?,?)";
    public static final String readUserOnId = "SELECT * FROM users WHERE id = ?;";
    public static final String updateUserOnId = "UPDATE users SET email = ?, username = ?, password = ? WHERE id = ?;";
    public static final String deleteUserOnId = "DELETE FROM users WHERE id = ?";

    //działa
    public User create(User user){
        try(Connection conn = DbUtil.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(createUserQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getUserName());
            stmt.setString(2,user.getEmail());
            stmt.setString(3,hashPassword(user.getPassword()));
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                user.setId(rs.getInt(1));
            }
            return user;
        }catch (SQLException e){
            System.err.println("Juz mamy taki mail w bazie");;
            return null;
        }
    }
//


    // działą
    public User read(int userId){
        try(Connection conn = DbUtil.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(readUserOnId);
            stmt.setInt(1,userId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                User user1 = new User(userId);
                System.out.print(rs.getString(2)+" ~~~~~~ ");
                user1.setEmail(rs.getString(2));
                System.out.print(rs.getString(3) + " ~~~~~~ ");
                user1.setUserName(rs.getString(3));
                System.out.print(rs.getString(4)+" ~~~~~~ ");
                user1.setPassword(rs.getString(4));
                return user1;
            }else return null;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
//

    // działa
    public void update(User user) {
        try(Connection conn = DbUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(updateUserOnId);
            stmt.setString(1,user.getEmail());
            stmt.setString(2,user.getUserName());
            stmt.setString(3,user.getPassword());
            stmt.setInt(4,user.getId());
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
//



    // działa
    public void delete(int userId){
        try(Connection conn = DbUtil.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(deleteUserOnId);
            stmt.setInt(1,userId);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    //
    public User[] findAll() {
        try(Connection conn = DbUtil.getConnection()){
            User[] users = new User[0];
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                User currentUser = new User();
                currentUser.setId(rs.getInt("id"));
                currentUser.setEmail(rs.getString("email"));
                currentUser.setUserName(rs.getString("username"));
                currentUser.setPassword(rs.getString("password"));
                users = addToArray(currentUser,users);
            }
            return users;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }


    private User[] addToArray(User u, User[] users){
        User[] tmpUsers = Arrays.copyOf(users,users.length+1);
        tmpUsers[users.length] = u;
        return tmpUsers;
    }

    //działą
    public static String hashPassword(String password){
        return BCrypt.hashpw(password, org.mindrot.jbcrypt.BCrypt.gensalt());
    }
//
}
