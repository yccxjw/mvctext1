package com.tx.MVCDAO;

import com.tx.MVCbeans.Student;
import com.tx.MVCutils.JdbcUtils;

import java.sql.*;


public class StudentDaoImpl implements IStudentDao {
    private Connection conn;
    private Statement st;
    private PreparedStatement ps;
    private ResultSet rs;


    @Override
    public Student selectStudentLogin(String username, String password) {
        Student student = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select * from student where username=? and password=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if(rs.next()) {
                student.setId(rs.getInt("id"));
                student.setUsername(rs.getString("username"));
                student.setName(rs.getString("name"));
                student.setPassword(rs.getString("password"));

            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            try {
                JdbcUtils.close(conn, ps, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return student;
    }


    @Override
    public Integer insertStudent(Student student) {
        Integer id = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "insert into student(username,password,name) values(?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, student.getUsername());
            ps.setString(2, student.getPassword());
            ps.setString(3, student.getName());

            ps.executeUpdate();

            //sql = "select @@identity newId";//获取一个id（数据库表里的id是自增的）
            sql = "select last_insert_id() newId";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                id = rs.getInt("newId");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                JdbcUtils.close(conn, ps, rs);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return id;


    }

}
