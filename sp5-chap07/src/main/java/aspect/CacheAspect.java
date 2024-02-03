package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Order(2)
public class CacheAspect {
    private Map<Long, Object> cache = new HashMap<>();

    @Pointcut("execution(public * chap07..*(long))")
    public void cacheTarget(){
    }

    @Around("cacheTarget()")
    public Object execute(ProceedingJoinPoint jointPoint) throws Throwable{
        Long num = (Long) jointPoint.getArgs()[0];
        if(cache.containsKey(num)){
            System.out.printf("CacheAspect: Cache에서 구함 [%d]\n", num);
            return cache.get(num);
        }
        Object result = jointPoint.proceed();
        cache.put(num, result);
        System.out.printf("CacheAspect: Cache에 추가 [%d]\n", num);
        return result;
    }
}
