package by.tms.authCalculation.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");

        String message;

        if(user != null) {
            session.invalidate();
            message = "Вы успешно вышли из аккаунта";
        } else {
            message = "Вы не авторизованы, чтобы выйти из аккаунта";
        }

        req.setAttribute("message", message);
        getServletContext().getRequestDispatcher("/message.jsp").forward(req, resp);
    }
}
