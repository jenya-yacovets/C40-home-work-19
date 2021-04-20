package by.tms.authCalculation.servlet;

import by.tms.authCalculation.entity.User;
import by.tms.authCalculation.exception.ParametersNotPassedException;
import by.tms.authCalculation.exception.UserNotFoundException;
import by.tms.authCalculation.servise.UserService;
import by.tms.authCalculation.util.Validation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/authorization")
public class AuthorizationServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/authorization.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");

        String message;

        if(user == null) {
            String login = req.getParameter("login");
            String password = req.getParameter("password");

            try {
                User userAuth = userService.getUser(Validation.authorization(login, password));
                message = "Вы успешно аторизовались";
                req.getSession().setAttribute("user", userAuth);
            } catch (UserNotFoundException e) {
                message = "Логин или пароль введен не верно";
            } catch (ParametersNotPassedException e) {
                message = "Не переданы обязательные параметры";
            }
        } else {
            message = "Вы уже авторизованы. Выйдите из текуще аккаунта и авторизуйтесь в другой";
        }

        req.setAttribute("message", message);
        getServletContext().getRequestDispatcher("/authorization.jsp").forward(req, resp);
    }
}
