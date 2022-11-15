package az.orient.course.web;

import az.orient.course.dao.LoginDao;
import az.orient.course.dao.impl.LoginDaoImpl;
import az.orient.course.model.Login;
import az.orient.course.service.LoginService;
import az.orient.course.service.impl.LoginServiceImpl;
import sun.rmi.runtime.Log;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet",urlPatterns = "/ls")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginDao loginDao=new LoginDaoImpl();
        LoginService loginService=new LoginServiceImpl(loginDao);
        String address=null;
        try {
            String username=request.getParameter("username");
            String password=request.getParameter("pwd");
            if(username!=null && !username.isEmpty() && password!=null && !password.isEmpty())
            {
             Login login=loginService.login(username,password);
             if(login!=null)
             {
                 HttpSession session=request.getSession();
                 session.setAttribute("login",login);
                 address="index.jsp";
             }
             else {
                 request.setAttribute("invalid","Username or password is invalid!");
                 address="login.jsp";
             }
            }
            else {
                request.setAttribute("invalid","Username or password is empty!");
             address="login.jsp";
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        RequestDispatcher dispatcher=request.getRequestDispatcher(address);
        dispatcher.forward(request,response);
    }


}
