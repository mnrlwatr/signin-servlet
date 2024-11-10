package servlet.auth;

import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;


@WebServlet(urlPatterns = "/signup")
public class RegistrationServlet extends BaseAuthServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("registration.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (userService.registerUser(User.builder().
                id(UUID.randomUUID()).
                login(login).
                password(password).build()))

        {
            resp.sendRedirect("/account");

        } else {
            resp.sendError(400,"User already exists, try to LogIn");
        }
    }
}
