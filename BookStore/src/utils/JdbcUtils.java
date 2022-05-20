package utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<>();

    static {
        try {
            Properties properties = new Properties();
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(inputStream);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取数据库连接池中的连接
     *
     * @return 如果返回null, 说明获取连接失败<br />有值就是获取连接成功
     */
    public static Connection getConnection() {
        Connection conn = conns.get();
        if (conn == null) {
            try {
                conn = dataSource.getConnection(); //从数据库连接池中获取连接
                conns.set(conn); //保存到ThreadLocal对象中，供后面jdbc操作使用
                conn.setAutoCommit(false); //设置为手动管理事物
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * 提交事物，并关闭数据库连接
     */
    public static void commitAndClose() {
        Connection connection = conns.get();
        if (connection != null) { //若不为null，则说明之前使用过连接，操作数据库
            try {
                connection.commit(); //提交事物
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close(); //关闭连接
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        /*Tomcat服务器使用了线程池技术，所以必须执行删除操作*/
        conns.remove();
    }

    /**
     * 回滚事物，并关闭数据库连接
     */
    public static void rollbackAndClose() {
        Connection connection = conns.get();
        if (connection != null) { //若不为null，则说明之前使用过连接，操作数据库
            try {
                connection.rollback(); //回滚事物
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close(); //关闭连接
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        /*Tomcat服务器使用了线程池技术，所以必须执行删除操作*/
        conns.remove();
    }

   /*
    public static void close(Connection conn){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }*/
}
