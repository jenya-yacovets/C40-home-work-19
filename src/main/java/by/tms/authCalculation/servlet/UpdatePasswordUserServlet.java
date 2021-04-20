package by.tms.authCalculation.servlet;

import by.tms.authCalculation.entity.User;
import by.tms.authCalculation.exception.FieldNotChangedException;
import by.tms.authCalculation.exception.InvalidPasswordException;
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

@WebServlet(urlPatterns = "/update-password")
public class UpdatePasswordUserServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object user = req.getSession().getAttribute("user");
        if(user == null) {
            req.setAttribute("message", "Вы не авторизованы. Для работы с данной страницей необходима авторизация");
            getServletContext().getRequestDispatcher("/message.jsp").forward(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/updatePassword.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");
        String message;

        if(user != null) {

            String password = req.getParameter("password");
            String newPassword = req.getParameter("newPassword");

            try {
                User user1 = userService.updatePassword((User) user, Validation.updatePassword(password, newPassword));
                // Изменяем данные в сеессии после редактирования данных пользователя
                session.setAttribute("user", user1);
                message = "Пароль успешно изменен";
            } catch (FieldNotChangedException e) {
                message = "Пароль введен старый";
            } catch (ParametersNotPassedException e) {
                message = "Параметры не переданы";
            } catch (InvalidPasswordException e) {
                message = "Старый пароль передан не верный";
            } catch (UserNotFoundException e) {
                message = "Пользователь не найден";
            }

        } else {
            message = "Вы не авторизованы! Для того что бы воспользоваться калькулятором для начала авторизуйтесь.";
        }
        req.setAttribute("message", message);
        getServletContext().getRequestDispatcher("/updatePassword.jsp").forward(req, resp);
    }
}
