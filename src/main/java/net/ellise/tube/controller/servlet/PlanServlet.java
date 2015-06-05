package net.ellise.tube.controller.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Map;

public class PlanServlet extends HttpServlet {
    private static final String CODE = "<html><body><p>Parameters</p><table>%s</table><p>Attributes</p><table>%s</table></body></html>";
    private static final String LINE = "<tr><th>%s</th><td>%s</td></tr>";

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        StringBuilder parameters = new StringBuilder();
        for (Map.Entry<String,String[]> entry : req.getParameterMap().entrySet()) {
            parameters.append(String.format(LINE, entry.getKey(), entry.getValue()));
        }

        StringBuilder attributes = new StringBuilder();
        Enumeration<String> attrs = req.getAttributeNames();
        while (attrs.hasMoreElements()) {
            String name = attrs.nextElement();
            Object value = req.getAttribute(name);
            attributes.append(String.format(LINE, name, value.toString()));
        }

        Writer writer = resp.getWriter();
        writer.write(String.format(CODE, parameters.toString(), attributes.toString()));
        writer.flush();
    }
}
