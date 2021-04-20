package by.tms.authCalculation.servlet;

import by.tms.authCalculation.entity.Operation;
import by.tms.authCalculation.entity.User;
import by.tms.authCalculation.exception.ParametersNotPassedException;
import by.tms.authCalculation.exception.UserNotFoundException;
import by.tms.authCalculation.servise.OperationService;
import by.tms.authCalculation.util.Validation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/operation")
public class OperationServlet extends HttpServlet {
    private OperationService operationService = new OperationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object user = req.getSession().getAttribute("user");
        if(user == null) {
            req.setAttribute("message", "Вы не авторизованы. Для работы с данной страницей необходима авторизация");
            getServletContext().getRequestDispatcher("/message.jsp").forward(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/operation.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object user = req.getSession().getAttribute("user");
        String message;

        if(user != null) {

            String num1 = req.getParameter("num1");
            String num2 = req.getParameter("num2");
            String method = req.getParameter("method");

            try {
                Operation operation = Validation.operation(num1, num2, method);
                operation.setUser((User) user);
                operationService.addOperation(operation);
                message = String.format("Результат: %f", operation.result());
            } catch (ParametersNotPassedException e) {
                message = "Не переданы обязательные параметры или указаны не верно";
            }

        } else {
            message = "Вы не авторизованы! Для того что бы воспользоваться калькулятором для начала авторизуйтесь.";
        }

        req.setAttribute("message", message);
        getServletContext().getRequestDispatcher("/operation.jsp").forward(req, resp);
    }
}
