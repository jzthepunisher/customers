package pe.interbank.customers.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import pe.interbank.customers.controller.web.dto.request.TransactionWebDto;
import pe.interbank.customers.util.api.exceptions.InvalidInputException;

@Aspect
@Component
public class SecurityAspect {

    @Before("execution(* pe.interbank.customers.controller.web.TransactionController.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            TransactionWebDto transactionWebDto = (TransactionWebDto)args[0];
            if(!isLimit(transactionWebDto)) {
                throw new InvalidInputException("Transaction amount exceeds the limit");
            }
        }
    }

    private Boolean isLimit(TransactionWebDto transactionWebDto) {
        return !(transactionWebDto.getAmount() > 1000.0);
    }

}
