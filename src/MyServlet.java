import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Documented;

@WebServlet("/Krxk")
public class MyServlet extends HttpServlet {
//    @Override
//    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        MyNio.Test();
        System.out.println("Request arrive!");
        HttpSession session = req.getSession();
        String type;
        if(session.isNew()) {
            type = req.getParameter("type");  //获取参数
            session.setAttribute("type", type);
        }
        else {
            type = (String) session.getAttribute("type");
        }

        resp.setHeader("content-type", "text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();

        System.out.println(type);
        int num;
        if (type.equals("int")) {
            num = 0;
            Cookie[] cookies = req.getCookies();
            if(cookies!=null) {
                for (Cookie cookie : cookies) {
                    if(cookie.getName().equals("Value")) {
                        num = Integer.parseInt(cookie.getValue());
                    }
                }
            }
            else {
                num = Integer.parseInt(req.getParameter("value"));
                Cookie cookie = new Cookie("Value", String.format("%d", num));
                resp.addCookie(cookie);
            }
            out.println("<html><body><h3>");
            out.println(num);
            out.println("</h3></body></html>");


        }
        else {
            out.println("<html><body><h3>类型： " + type +
                    "</body></html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}
