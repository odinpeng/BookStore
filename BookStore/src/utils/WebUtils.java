package utils;

import org.apache.commons.beanutils.BeanUtils;
import java.util.Map;

public class WebUtils {
    /**
     * 获取请求的参数，封装为Bean对象
     * 传入map到对应的JavaBean属性中
     *
     * @param value
     * @param bean  传入的参数为T类型，返回值的类型就为T，所以接收时，不需要类型转换
     */
    public static <T> T copyParameterToBean(Map value, T bean) {
        try {
            BeanUtils.populate(bean, value);
        } catch (Exception e) {
            /*e.printStackTrace();*/
        }
        return bean;
    }

    /**
     * 将获取的id改为int型
     *
     * @param id
     * @return 转换成功返回id，不成功返回defaultValue
     */
    public static int parseInt(String id, int defaultValue) {
        try {
            return Integer.parseInt(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }
}
