package com.tx.MVCDAO;

import com.tx.MVCbeans.Student;

public interface IStudentDao {
    Student selectStudentLogin(String username, String password);
    Integer insertStudent(Student student);
}