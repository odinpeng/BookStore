package web.older;
/*web层*/
/*视图展现层：获取客户端请求，调用Service层处理业务，响应客户端*/
/*处理登录页面业务逻辑*/

import pojo.User;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        /*判断用户名对应的密码是否正确*/
        User login = userService.login(new User(null, username, password, null));
        if (login != null) {
            System.out.println("登录成功！");
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        } else {
            System.out.println("用户名或密码不正确，登录失败！");
            /*将错误信息保存到表request域中，保存用户名*/
            req.setAttribute("msg","用户名或密码不正确!");
            req.setAttribute("username",username);
            /*再次跳转到登录界面*/
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }
    }
}
