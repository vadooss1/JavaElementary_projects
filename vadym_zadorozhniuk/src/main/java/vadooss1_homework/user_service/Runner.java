package vadooss1_homework.user_service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Runner {
    public static void main(String[] args) {
        //MySQLconnector mySQLconnector = new MySQLconnector();
        //Connection connection = mySQLconnector.getConnection();
        //mySQLconnector.resetDB(connection);
        Gson json = new GsonBuilder().setPrettyPrinting().create();
        User user = new User();
        user.setId(2);
        user.setName("petya");
        user.setSex(true);
        user.setAge(20);
        user.setPassword("1111");

        UserService userService = new UserService();
        //System.out.println(json.toJson(userService.addUser(user)));
        System.out.println(json.toJson(userService.getAllUsers()));

    }
}
