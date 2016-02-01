package com.becomejavasenior.filters;

import com.becomejavasenior.ServiceException;
import com.becomejavasenior.User;
import com.becomejavasenior.UserService;
import com.becomejavasenior.config.ApplicationContextConfig;
import com.becomejavasenior.impl.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidRegistrationFilter implements Filter {

    private Pattern pattern;
    private Matcher matcher;

    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9_-]{3,15}$";
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*" +
            "@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PASSWORD_PATTERN = "([a-zA-Z0-9_-].{8,20})";
    private UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
        userService = context.getBean(UserService.class);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String servletUrl = request.getServletPath();
        System.out.println(servletUrl);
        if (!validUserName(username)) {
            if (servletUrl.equals("/registr")) {
                request.getRequestDispatcher("/pages/registration.jsp?errorUserName=1").forward(request, response);
            }
            if (servletUrl.equals("/user_add")) {
                request.getRequestDispatcher("/pages/userAdd.jsp?errorUserName=1").forward(request, response);
            }
            return;
        }
        if (!validEmail(email)) {
            if (servletUrl.equals("/registr")) {
                request.getRequestDispatcher("/pages/registration.jsp?errorEmail=1").forward(request, response);
            }
            if (servletUrl.equals("/user_add")) {
                request.getRequestDispatcher("/pages/userAdd.jsp?errorEmail=1").forward(request, response);
            }
            return;
        }
        if (!validPassword(password)) {
            if (servletUrl.equals("/registr")) {
                request.getRequestDispatcher("/pages/registration.jsp?errorPass=1").forward(request, response);
            }
            if (servletUrl.equals("/user_add")) {
                request.getRequestDispatcher("/pages/userAdd.jsp?errorPass=1").forward(request, response);
            }
            return;
        }

        try {
            if (validSameUser(email)) {
                if (servletUrl.equals("/registr")) {
                    request.getRequestDispatcher("/pages/registration.jsp?errorEmail=2").forward(request, response);
                }
                if (servletUrl.equals("/user_add")) {
                    request.getRequestDispatcher("/pages/userAdd.jsp?errorEmail=2").forward(request, response);
                }
                return;
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        if (validUserName(username) && validEmail(email) && validPassword(password) && !validUserName(email)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private boolean validSameUser(String email) throws ServiceException {
        List<User> users = userService.getAll();
        for (User u : users) {
            if (u.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }


    private boolean validUserName(String username) {
        pattern = Pattern.compile(USERNAME_PATTERN);
        matcher = pattern.matcher(username);
        return matcher.matches();
    }

    private boolean validEmail(String email) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean validPassword(String password) {
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    @Override
    public void destroy() {

    }
}
