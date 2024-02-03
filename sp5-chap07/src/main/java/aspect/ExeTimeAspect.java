package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import java.util.Arrays;

@Aspect
@Order(1)
public class ExeTimeAspect {

    @Pointcut("execution(public * chap07..*(..))") //execution(수식어패턴? 리턴타입패턴 클래스이름패턴?메서드이름패턴(파라미터패턴))
    private void publicTarget(){

    }

    @Around("publicTarget()")
    public Object measure(ProceedingJoinPoint jointPoint) throws Throwable{
        long start = System.nanoTime();
        try{
            Object result = jointPoint.proceed();
            return result;
        }finally {
            long finish = System.nanoTime();
            Signature sig = jointPoint.getSignature();
            System.out.printf("%s.%s(%s) 실행시간: %d ns\n",
                    jointPoint.getTarget().getClass().getSimpleName(),
                    sig.getName(), Arrays.toString(jointPoint.getArgs()),
                    (finish - start));
        }
    }
}
