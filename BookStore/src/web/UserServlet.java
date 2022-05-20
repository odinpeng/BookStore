package web;
/*User模块，处理用户的登录与注册*/
import pojo.User;
import service.UserService;
import service.UserServiceImpl;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();
    /**
     * 客户端登录功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        /*判断用户名对应的密码是否正确*/
        User login = userService.login(new User(null, username, password, null));
        if (login != null) {
            System.out.println("登录成功！");
            /*用户登录后，将user对象保存到Session域中*/
            req.getSession().setAttribute("user",login);
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

    /**
     * 注销，（销毁session对象）
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*立即销毁session对象*/
        req.getSession().invalidate();
        /*重定向到主页*/
        resp.sendRedirect(req.getContextPath());
    }

    /**
     * 客户端注册功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*获取请求参数*/
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code"); //验证码

        /*获取Session域中验证码*/
        String sessionCode =(String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        /*立刻删除Session域中的验证码*/
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        /*传入客户端请求的参数*/
        User user = WebUtils.copyParameterToBean(req.getParameterMap(),new User());

        /*判断验证码是否正确(忽略大小写)*/
        boolean b = sessionCode.equalsIgnoreCase(code);
        if (b) {
            /*此时验证码输入正确*/
            /*检查用户名是否可用*/
            boolean b1 = userService.existsUsername(username);
            if (b1) {
                /*b1==true:用户名已存在, 不可用，跳回注册页面 regist.jsp*/
                System.out.println("用户名已存在！");
                /*将错误信息保存到request域中，保存用户名和邮箱*/
                req.setAttribute("msg","用户名已存在！");

                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                /*b1==false: 用户名不存在，可用, 将用户信息保存到数据库*/
                System.out.println("注册成功！");
                userService.registerUser(new User(null, username, password, email));
                /*跳转到注册成功的页面: /pages/user/regist_success.jsp*/
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }

        }else {
            /*此时验证码输入错误，跳回注册页面 regist.jsp*/
            System.out.println("验证码错误！");
            /*将错误信息保存到request域中，保存用户名和邮箱*/
            req.setAttribute("msg","验证码错误！");
            req.setAttribute("username",username);
            req.setAttribute("email",email);

            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

    /**
     * 使用AJAX判断用户名是否可用
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        *//*获取username*//*
        String username = req.getParameter("username");
        *//*调用userService.existUsername方法判断用户名是否可用*//*
        boolean existsUsername = userService.existsUsername(username);
        *//*将返回的结果封装为Map对象*//*
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername",existsUsername);
        *//*获取Gson对象*//*
        Gson gson = new Gson();
        *//*将resultMap转化为Json字符串*//*
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }*/
}
