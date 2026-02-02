package custom_logging_proxy;

import java.lang.reflect.*;

public class CustomLoggingProxy {
    
    public static void main(String[] args) {
        UserService realService = new UserServiceImpl();
        
        UserService proxyService = (UserService) Proxy.newProxyInstance(
            UserService.class.getClassLoader(),
            new Class[]{UserService.class},
            new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("Calling: " + method.getName());
                    Object result = method.invoke(realService, args);
                    System.out.println("Returned: " + result);
                    return result;
                }
            }
        );
        
        String user = proxyService.getUserById(1);
        System.out.println("Final result: " + user);
    }
}
