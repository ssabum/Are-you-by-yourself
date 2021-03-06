package com.web.curation.sport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;

//@Component
public class SportRunner implements ApplicationRunner {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try(Connection connection = dataSource.getConnection()){
            System.out.println(connection.getMetaData().getURL());
//            Statement statement = connection.createStatement();
//            String sql ="INSERT INTO sport(sport_name) values('헬스')";
//            statement.executeUpdate(sql);
        }
        jdbcTemplate.execute("INSERT INTO sport(sport_name) values('조깅')");

    }
}
