import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeeTableServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/yourdatabase";
    private static final String DB_USER = "yourusername";
    private static final String DB_PASSWORD = "yourpassword";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Set the content type of the response to HTML
        response.setContentType("text/html");
        
        // Get a PrintWriter object to write the HTML table string to the response
        PrintWriter out = response.getWriter();

        // Establish a connection to the MySQL database
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {

            // Create a Statement object and execute a SELECT query on the employee table
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employee");

            // Build the HTML table string
            StringBuilder htmlTable = new StringBuilder();
            htmlTable.append("<table>");
            htmlTable.append("<tr><th>Employee ID</th><th>Employee Name</th><th>Age</th><th>Salary</th></tr>");
            while (resultSet.next()) {
                String empId = resultSet.getString("empid");
                String empName = resultSet.getString("empname");
                int age = resultSet.getInt("age");
                int salary = resultSet.getInt("salary");
                htmlTable.append("<tr><td>").append(empId).append("</td><td>").append(empName).append("</td><td>").append(age).append("</td><td>").append(salary).append("</td></tr>");
            }
            htmlTable.append("</table>");

            // Write the HTML table string to the response
            out.println(htmlTable.toString());

        } catch (SQLException e) {
            e.printStackTrace();
            out.println("Error: " + e.getMessage());
        }
    }
}
