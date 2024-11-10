package servlet.auth;

import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = "/signin")
public class SignInServlet extends BaseAuthServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/signin.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = userService.findUser(login, password);

        if (user != null) {
            session.setAttribute("User", user);
            resp.sendRedirect("/account");
        } else {
            resp.sendError(403, "Invalid password and login");
        }
    }
}
