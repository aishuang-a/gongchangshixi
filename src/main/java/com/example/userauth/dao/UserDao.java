package com.example.userauth.dao;

import com.example.userauth.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class UserDao {
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DriverManagerDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void save(User user) {
        String sql = "INSERT INTO users(username, password) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword());
    }

    public User findByUsernameAndPassword(String username, String password) {
        String sql = "SELECT username, password FROM users WHERE username=? AND password=?";
        return jdbcTemplate.query(sql, new Object[]{username, password}, rs -> {
            if (rs.next()) {
                return new User(rs.getString("username"), rs.getString("password"));
            }
            return null;
        });
    }
}
