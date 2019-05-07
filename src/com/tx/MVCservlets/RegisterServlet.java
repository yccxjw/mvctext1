package com.tx.MVCservlets;

import com.tx.MVCbeans.Student;
import com.tx.MVCservice.IStudentService;
import com.tx.MVCservice.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //1.获取表单参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");


        //2.创建Student对象
        Student student = new Student(username,password,name);
        student.setPassword(password);

        //3.创建Service对象
        IStudentService service = new StudentServiceImpl();


        //4.调用Service对象的saveStudent()方法将对象写入DB
        Integer id = service.saveStudent(student);

        //5.写入失败，则跳转到注册页面，重新注册
        if(id == null) {
            response.sendRedirect(request.getContextPath()+"/register.jsp");
            return;
        }

        //6.写入成功,则跳转到登录页面
        response.sendRedirect(request.getContextPath()+"/login.jsp");
    }

}
