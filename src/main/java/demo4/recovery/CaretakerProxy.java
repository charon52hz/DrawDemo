package demo4.recovery;

import java.lang.reflect.Proxy;
import java.util.Arrays;

public class CaretakerProxy {
    //被代理的对象
    private  Object target;

    public CaretakerProxy(Object target) {
        this.target = target;
    }

    public Object getProxy() {
        //获取被代理对象的类加载器
        ClassLoader classLoader =target.getClass().getClassLoader();
        //获取被代理对象实现的接口
        Class<?>[] interfaces = target.getClass().getInterfaces();
        /*
        参数说明
        invoke方法中就是要执行的扩展的功能
        该方法中参数的说明：
        proxy:传入的代理对象
        method：要调用的方法
        args：调用方法时传入的参数
         */
        return Proxy.newProxyInstance(classLoader, interfaces, (proxy1, method, args) -> {
            String methodName =method.getName();
            Object result=method.invoke(target,args);
            return result;
        });
    }
}
