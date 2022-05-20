package service;
/*Service实现*/
/*业务层：处理业务逻辑，调用持久层*/
import impl.UserDao;
import impl.UserDaoImpl;
import pojo.User;

public class UserServiceImpl implements UserService{
    private UserDao userDao=new UserDaoImpl();

    /**
     * 注册用户
     * @param user
     */
    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    /**
     * 登录
     * @param user
     * @return 返回toString: 表中有username和password,登录成功    返回null：表中没有username和password，登录失败
     */
    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    /**
     * 检查用户名是否可用
     * @param username
     * @return 返回true：用户名已存在    返回false：用户名不存在
     */
    @Override
    public boolean existsUsername(String username) {
        if (userDao.queryUserByUsername(username)==null){
            return false;
        }else{
            return true;
        }
    }
}
