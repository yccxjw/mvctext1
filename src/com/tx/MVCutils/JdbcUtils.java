package com.tx.MVCutils;

import java.sql.*;

/*一旦工具类发生异常，若使用try catch捕捉异常，工具类的调用者根本不知道哪错了，所以工具类里一般不用try catch*/
public class JdbcUtils {
    // 加载驱动
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 获取数据库连接Connection对象
    private static Connection conn;

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/text"; // jdbc总协议，mysql子协议，///数据库在本机且端口号是3306，test数据库
        String user = "root";
        String password = "15375866153";

        Connection conn = null;
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(url, user, password);
        }
        return conn;
    }

    // 关闭资源
    public static void close(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
        if (stmt != null && !stmt.isClosed()) {
            stmt.close();
        }
        if (rs != null && !rs.isClosed()) {
            rs.close();
        }
    }
}
