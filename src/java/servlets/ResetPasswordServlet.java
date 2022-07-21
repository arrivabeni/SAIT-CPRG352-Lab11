package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.AccountService;

public class ResetPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String jspName = "";

        if (uuid == null) {
            request.setAttribute("forgotMode", true);
            jspName = "reset.jsp";
        } else {
            request.setAttribute("resetMode", true);
            request.setAttribute("uuid", uuid);
            jspName = "resetNewPassword.jsp";
        }

        getServletContext().getRequestDispatcher("/WEB-INF/" + jspName).forward(request, response);
        return;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AccountService as = new AccountService();
        String action = request.getParameter("action");
        String message = "";
        String uuid = "";

        switch (action) {
            case "reset":
                uuid = request.getParameter("uuid");
                String password = request.getParameter("password");

                as.changePassword(uuid, password);
                message = "Your password has been successfully updated.";
                break;
            case "forgot":
                String email = request.getParameter("email");
                String path = getServletContext().getRealPath("/WEB-INF");
                String url = request.getRequestURL().toString();

                as.resetPassword(email, path, url);
                message = "If the email exists in our database, you will receive a message soon with the link to reset your password.";
                break;
            default:
                message = "Sorry! An unexpected error happened!";
                break;
        }

        request.setAttribute("message", message);
        getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
        return;
    }
}
