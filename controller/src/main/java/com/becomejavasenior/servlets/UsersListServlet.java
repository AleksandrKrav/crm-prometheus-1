package com.becomejavasenior.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * This servlet class demonstrates how to access a JNDI DataSource that
 * represents a JDBC connection.
 * @author www.codejava.net
 */
@WebServlet("/listUsers")
public class UsersListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Resource(name = "jdbc/UsersDB")
    private DataSource dataSource;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        try {
            Connection conn = dataSource.getConnection();

            Statement statement = conn.createStatement();
            String sql = "select name, email from users";
            ResultSet rs = statement.executeQuery(sql);

            int count = 1;
            while (rs.next()) {
                writer.println(String.format("User #%d: %-15s %s", count++,
                        rs.getString("name"), rs.getString("email")));

            }
        }  catch (SQLException ex) {
            System.err.println(ex);
        }
    }

}