package sample.customer.biz.service.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAdvice {

	private static final Logger logger = LoggerFactory.getLogger(LogAdvice.class);

    @Pointcut("@within(org.springframework.stereotype.Controller)")
    public void controller() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void requestMapping() {
    }

    @Pointcut("execution(* sample.customer.web.controller..*.*(..))")
    public void anyProjectMethodExecution() {
    }

    @Around("controller() && requestMapping() && anyProjectMethodExecution()")
    public Object log(ProceedingJoinPoint pjp) throws Throwable {
    	logger.info("before Args:{}", pjp.getArgs());
//        Object[] args = pjp.getArgs();
//        for (int i = 0; i < args.length; i++) {
//        	logger.info("BEFORE: checkBeforePointCut:{}",args[i]);
//        }

        // ここで対象のメソッドを呼び出します。
        String message = (String)pjp.proceed();

        Object[] objArray = pjp.getArgs();

        for (Object obj : objArray) {
            System.out.println("引数の値です。 : " + obj.toString());
        }


        Object retVal = pjp.proceed();
        logger.info("after");
        return retVal;
    }
}
