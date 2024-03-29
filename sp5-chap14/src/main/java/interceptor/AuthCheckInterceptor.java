package interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if(session != null){
            Object authInfo = session.getAttribute("authInfo");
            if(authInfo != null){
                return false;
            }
        }
        response.sendRedirect(request.getContextPath() + "/login");
        return false;
    }
}
