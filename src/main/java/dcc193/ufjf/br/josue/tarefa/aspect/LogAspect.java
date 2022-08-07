package dcc193.ufjf.br.josue.tarefa.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
    @Around("execution(* *..*.*Controller.*(..))")
    Object startLog(ProceedingJoinPoint jp) throws Throwable {

        System.out.println("Método chamado: " + jp.getSignature());
        Object retorno;
        try {
            retorno = jp.proceed();
        } catch (Exception e) {
            System.out.println("Mpetodo interrompido: " + jp.getSignature());
            e.printStackTrace();
            throw e;
        } finally {
            System.out.println("Método conclúido " + jp.getSignature());
        }
        return retorno;
    }

}
