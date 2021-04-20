package by.tms.authCalculation.servlet;

import by.tms.authCalculation.entity.Operation;
import by.tms.authCalculation.entity.User;
import by.tms.authCalculation.servise.OperationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/history")
public class HistoryServlet extends HttpServlet {
    private OperationService operationService = new OperationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object user = req.getSession().getAttribute("user");
        String message;

        if(user != null) {
            List<Operation> allOperationUser = operationService.getAllOperationUser((User) user);

            String result = "";
            for(Operation operation : allOperationUser) {
                result += String.format("Пользователь: %s | Число 1: %f | Число 2: %f | Операция: %s<br>", operation.getUser().getName(), operation.getNum1(), operation.getNum2(), operation.getOperation().getNameUser());
            }
            message = result;
        } else {
            message = "Вы не авторизованы! Для того что бы воспользоваться калькулятором для начала авторизуйтесь.";
        }

        req.setAttribute("message", message);
        getServletContext().getRequestDispatcher("/history.jsp").forward(req, resp);
    }
}
