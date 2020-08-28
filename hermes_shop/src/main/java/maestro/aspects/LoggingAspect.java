package maestro.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("@annotation(maestro.annotations.Logging)")
    public void logging(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Method " + joinPoint.getSignature().getName()  + " was executed!");
        joinPoint.proceed();
        logger.info("Method " + joinPoint.getSignature().getName()  + " end!");
    }

}
