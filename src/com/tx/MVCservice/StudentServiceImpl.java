package com.tx.MVCservice;

import com.tx.MVCDAO.IStudentDao;
import com.tx.MVCDAO.StudentDaoImpl;
import com.tx.MVCbeans.Student;

public class StudentServiceImpl implements IStudentService {
    private IStudentDao dao;


    public StudentServiceImpl() {
        dao = new StudentDaoImpl();
    }


    public Student checkUser(String username, String password) {

        return dao.selectStudentLogin(username,password);
    }


    @Override
    public Integer saveStudent(Student student) {
        return dao.insertStudent(student);
    }

}
