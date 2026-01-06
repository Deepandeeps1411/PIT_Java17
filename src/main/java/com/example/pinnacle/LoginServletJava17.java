package com.example.pinnacle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServletJava17 extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String userId = request.getParameter("userId");
        String ctc    = request.getParameter("ctc");

        // EXACT match, case-sensitive: only this pair is allowed
        if ("Deepan".equals(userId) && "Deepan".equals(ctc)) {
            // successful login -> redirect to home page
            response.sendRedirect(request.getContextPath() + "/home.html");
            return;
        }

        // invalid credentials -> do NOT redirect; return "Invalid User"
        response.setContentType("text/html;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        try (PrintWriter out = response.getWriter()) {
            out.println("<!doctype html><html><head><meta charset='utf-8'><title>Invalid</title>");
            out.println("<style>body{font-family:Arial;margin:30px} .err{color:#c00}</style>");
            out.println("</head><body>");
            out.println("<h2 class='err'>Invalid Username or Password</h2>");
            out.println("<p><a href='" + request.getContextPath() + "/login.html'>Back to Login</a></p>");
            out.println("</body></html>");
        }
    }

    // allow GET to show a simple message or redirect to login page
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/login.html");
    }
}
