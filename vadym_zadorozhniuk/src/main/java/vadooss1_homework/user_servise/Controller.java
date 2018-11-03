package vadooss1_homework.user_servise;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;

@WebServlet("/user")
public class Controller extends HttpServlet {
    final static Logger logger = Logger.getLogger(Controller.class);
    Gson json = new GsonBuilder().setPrettyPrinting().create();
    UserService userService = new UserService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            LinkedList<User> usersList = userService.getAllUsers();
            resp.getWriter().println(json.toJson(usersList));
        }catch (Exception e){
            logger.error(e);
            resp.getWriter().println("Server error");
            resp.setStatus(503);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = json.fromJson(req.getReader(), User.class);
        resp.getWriter().println(json.toJson(userService.addUser(user)));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = json.fromJson(req.getReader(), User.class);
        resp.getWriter().println(json.toJson(userService.updateUser(user)));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = json.fromJson(req.getReader(), User.class);
        resp.getWriter().println(json.toJson(userService.deleteUser(user.getId())));
    }
}
