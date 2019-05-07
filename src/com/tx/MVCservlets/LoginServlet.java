package com.tx.MVCservlets;

import com.tx.MVCbeans.Student;
import com.tx.MVCservice.IStudentService;
import com.tx.MVCservice.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException {
        //1.接收请求
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //获取session
        HttpSession session = request.getSession();

        /* "".equals(num.trim())表示用户输入了空串儿，但是是从表单登录的
         * num == null为的是防止用户从地址栏访问 */
        if(username == null || "".equals(username.trim())) {
            //request.getRequestDispatcher("/login.jsp").forward(request,response);//请求转发时，若用户恶意刷新会占用服务端的资源
            session.setAttribute("message", "学号输入有误！");
            response.sendRedirect(request.getContextPath()+"/login.jsp");//重定向 ， 防止恶意刷新
            return;
        }
        if(password == null || "".equals(password.trim())) {
            session.setAttribute("message", "密码输入有误！");
            response.sendRedirect(request.getContextPath()+"/login.jsp");//重定向 ， 防止恶意刷新
            return;
        }

        //2.创建Service对象
        IStudentService service = new StudentServiceImpl();//注意接口和实现类的命名方式


        //3.调用Service对象的checkStudent()方法对用户进行验证
        Student student = service.checkUser(username,password);

        //4.验证通过，则跳转到系统主页index.jsp
        response.sendRedirect(request.getContextPath()+"/index.jsp");//重定向 ， 防止恶意刷新


        //5.验证未通过，则跳转到登录页面，让用户再次登录，此时需要给用户一些提示信息
        if(student == null) {
            session.setAttribute("message", "用户名或密码输入有误！");
            response.sendRedirect(request.getContextPath()+"/login.jsp");//重定向 ， 防止恶意刷新
            return;
        }

    }

}
