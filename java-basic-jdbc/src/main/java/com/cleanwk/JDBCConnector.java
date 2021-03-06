package com.cleanwk;

import lombok.Data;

import java.sql.*;

/**
 * @author cleanwk
 * @date 2021/12/8
 */
@Data
public class JDBCConnector {
    // JDBC连接的URL, 不同数据库有不同的格式:
    private final String JDBC_URL = "jdbc:mysql://txcloud.com:3306/sample?useSSL=false&characterEncoding=utf8";
    private final String JDBC_USER = "root";
    private final String JDBC_PASSWORD = "root";

    void unsafeQuery(){
        try(Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)){
            try(Statement statement = connection.createStatement()){
                try (ResultSet rs = statement.executeQuery("SELECT id, grade, name, gender FROM students WHERE gender=1")) {
                    while (rs.next()) {
                        long id = rs.getLong(1); // 注意：索引从1开始
                        long grade = rs.getLong(2);
                        String name = rs.getString(3);
                        int gender = rs.getInt(4);

                    }
                 }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    void safeStatement(){
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO `sample`.`runoob_tbl` (`id`, `runoob_title`, `runoob_author`, `submission_date`) VALUES (25, 'canal教程', 'zhangnijia.com', '2099-01-01')\n")) {
                ps.setObject(1, "M"); // 注意：索引从1开始
                ps.setObject(2, 3);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        long id = rs.getLong("id");
                        long grade = rs.getLong("grade");
                        String name = rs.getString("name");
                        String gender = rs.getString("gender");
                    }
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
