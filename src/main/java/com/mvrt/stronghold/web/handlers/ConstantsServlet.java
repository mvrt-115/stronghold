package com.mvrt.stronghold.web.handlers;

import com.mvrt.lib.ConstantsBase.Constant;
import com.mvrt.stronghold.Constants;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The servlet to show the constants viewing interface.
 *
 * @author Siddharth Gollapudi
 */
public class ConstantsServlet extends HttpServlet {

  /**
   * method to show the actual webpage.
   * @param response object to manipulate the webpage
   * @throws IOException in case everything fails
   */
  private void buildPage(HttpServletResponse response) throws IOException {
    Constants constants = new Constants();

    response.setContentType("text/html");
    response.setStatus(HttpServletResponse.SC_OK);//ensure the everything worked
    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<body>");
    out.println("<form method='post'>");
    out.println("<table cellspacing='5'>");
    Collection<Constant> cs = constants.getConstants();
    for (Constant c : cs) {
      out.println("<tr>");
      out.println("<td>(" + c.type + ")</td>");
      out.println("<td>" + c.name + "</td>");
      out.println(
          "<td><input type='text' name='" + c.name + "' id='" + c.name + "' value='" + c.value
              + "'></td>");
      out.println("</tr>");
    }
    out.println("</table>");
    out.println("<input type='submit' value='Save'>");
    out.println("<input type='reset' value='Reset'>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }

  /**
   * Create the webpage.
   * @param request what is sent
   * @param response what is received
   * @throws ServletException in case there's an error
   * @throws IOException in case there's an error
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    buildPage(response);
  }

  /**
   * Allow for the changing of constants.
   * @param request what is sent
   * @param response what is received
   * @throws ServletException in case there's an issue
   * @throws IOException in case there's an issue
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    Constants constants = new Constants();
    for (String key : request.getParameterMap().keySet()) {
      String value = request.getParameter(key);
      Constant c = constants.getConstant(key);
      if (c != null) {
        if (double.class.equals(c.type) || Double.class.equals(c.type)) {
          double v = Double.parseDouble(value);
          constants.setConstant(key, v);
        } else if (int.class.equals(c.type) || Integer.class.equals(c.type)) {
          int v = Integer.parseInt(value);
          constants.setConstant(key, v);
        } else if (String.class.equals(c.type)) {
          constants.setConstant(key, value);
        }
      }

    }
    constants.saveToFile();
    buildPage(response);
  }
}
