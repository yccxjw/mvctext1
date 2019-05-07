package com.tx.MVCservice;

import com.tx.MVCbeans.Student;

public interface IStudentService {
    //对用户进行验证
    Student checkUser(String username, String password);

    //向DB中添加Student
    Integer saveStudent(Student student);
}
