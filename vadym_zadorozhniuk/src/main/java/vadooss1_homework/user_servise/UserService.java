package vadooss1_homework.user_servise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class UserService {
    private MySQLconnector mySQLconnector = new MySQLconnector();
    private Connection connection = mySQLconnector.getConnection();

    public User addUser(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user_service.users " +
                    "(status, name, password, age, sex, message_result) values (?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getAge());
            preparedStatement.setBoolean(5, user.isSex());
            preparedStatement.setString(6, "New user is created!");
            preparedStatement.executeUpdate();
            preparedStatement.close();
            PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT * FROM user_service.users ORDER BY id DESC LIMIT 1");
            ResultSet resultSet = preparedStatement1.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setStatus(resultSet.getInt("status"));
                user.setMessageResult(resultSet.getString("message_result"));
            }
            preparedStatement1.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public LinkedList<User> getAllUsers(){
        LinkedList<User> usersList = new LinkedList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user_service.users");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setStatus(resultSet.getInt("status"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setAge(resultSet.getInt("age"));
                user.setSex(resultSet.getBoolean("sex"));
                user.setMessageResult(resultSet.getString("message_result"));
                usersList.add(user);
            }
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;

    }


    public User getUser(int id) {
        User user = new User();
        user.setId(id);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user_service.users WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setStatus(resultSet.getInt("status"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setAge(resultSet.getInt("age"));
                user.setSex(resultSet.getBoolean("sex"));
                user.setMessageResult("User exists! Last message was: " + (resultSet.getString("message_result")));
                return user;
            }
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        user.setMessageResult("Such user is not exists!");
        return user;
    }


    public User updateUser(User user) {
        boolean userExists = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user_service.users WHERE id = ?");
            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userExists = true;
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (userExists) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user_service.users SET status = ?, name = ?, " +
                        "password = ?, age = ?, sex = ?, message_result = ?  WHERE id = ?");
                preparedStatement.setInt(1, 1);
                preparedStatement.setString(2, user.getName());
                preparedStatement.setString(3, user.getPassword());
                preparedStatement.setInt(4, user.getAge());
                preparedStatement.setBoolean(5, user.isSex());
                preparedStatement.setString(6, "User is updated!");
                preparedStatement.setInt(7, user.getId());
                preparedStatement.executeUpdate();
                preparedStatement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            user.setStatus(1);
            user.setMessageResult("User is updated!");
            return user;
        } else {
            user.setStatus(0);
            user.setMessageResult("Such user is not exists!");
            return user;
        }
    }


    public User deleteUser(int id) {
        User user = new User();
        user.setId(id);
        boolean userExists = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user_service.users WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setStatus(resultSet.getInt("status"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setAge(resultSet.getInt("age"));
                user.setSex(resultSet.getBoolean("sex"));
                userExists = true;
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (userExists) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user_service.users WHERE id = ?");
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            user.setMessageResult("User is deleted!");
            return user;
        } else {
            user.setStatus(0);
            user.setMessageResult("Such user is not exists!");
            return user;
        }
    }
}

