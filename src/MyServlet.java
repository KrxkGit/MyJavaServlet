import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Krxk")
public class MyServlet extends HttpServlet {
//    @Override
//    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
////        super.service(req, res);
//        PrintWriter out = res.getWriter();
//        out.println("<html>");
//        out.println(("<head>"));
//        out.println("</head>");
//        out.println(("<body>"));
//        out.println("<h3>My Servlet</h3>");
//        out.println("</body>");
//        out.println("</html>");
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        System.out.println("Request arrive!");
        String type = req.getParameter("type");  //获取参数

        resp.setHeader("content-type", "text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();

        System.out.println(type);
        if (type.equals("int")) {
            int num = Integer.parseInt(req.getParameter("value"));
            out.println("<html><body><h3>");
            out.println(num);
            out.println("</h3></body></html>");

        }
        else {
            out.println("<html><body><h3>类型： " + type +
                    "</body></html>");
        }
    }
}
