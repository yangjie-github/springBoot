package com.yangjie;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author yangjie
 * @date 2019/3/30 15:56
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcTests {

    @Autowired
    private DataSource dataSource;

    @Test
    public void test() throws SQLException {
        System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }
}
