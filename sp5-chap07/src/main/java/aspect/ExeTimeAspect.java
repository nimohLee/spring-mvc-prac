package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import java.util.Arrays;
// 여러 객체에 공통으로 적용되는 기능을 Aspect라고 한다.
// Aspect 애노테이션을 적용한 클래스는 Advice(여기서는 @Around)와 Pointcut을킴함께 제공한다.
@Aspect
@Order(1)
public class ExeTimeAspect {

    // 공통 기능을 적용할 대상을 설정한다.
    // @Pointcut 애노테이션의 값으로 사용할 수 있는 execution 명시자에 대해서는 뒤에서 살펴본다.
    // chap 패키지와 그 하위 패키지에 위치한 타입의 public 메서드를 Pointcut으로 설정한다는 정도만 이해하고 넘어가면 된다.
    // execution(수식어패턴? 리턴타입패턴 클래스이름패턴?메서드이름패턴(파라미터패턴))
    // '수식어패턴'은 생략 가능하며 public, protected 등이 온다. 스프링 AOP는 public 메서드에만 적용할 수 있기 때문에 사실상 public만 의미있다.
    // '리턴타입 패턴'은 리턴 타입을 명시한다. '클래스이름패턴'과 '메서드 이름패턴'은 클래스 이름 및 메서드 이름을 패턴으로 명시한다. '파라미터패턴'은 매칭될 파라미터에 대해서 명시한다.
    // 각 패턴은 '*'을 이용하여 모든 값을 표현할 수 있다. 또한, '..'(점 두 개)을 이용하여 0개 이상이라는 의미를 표현할 수 있다.
    @Pointcut("execution(public * chap07..*(..))") // public이고, 리턴타입 다 되고, chap07 패키지와 하위 패키지 모두 포함해서(..)메서드 이름(*) 아무거나, 메서드 파라미터 0개 이상인 메서드 호
    private void publicTarget() {
    }

    // Around Advice를 설정한다.
    // @Around 애노테이션의 값이 "publicTarget()"인데 이는 publicTarget() 메서드에 정의한 Pointcut에 공통 기능을 적용한다는 것을 의미한다.
    // publicTarget() 메서드는 chap07 패키지와 그 하위 패키지에 위치한 public 메서드를 Pointcut으로 설정하고 있으므로, chap07 패키지나 그 하위 패키ㅣ에 속한 빈 객체의 public 메서드에 @Around가 붙은 measure() 메서드를 적용한다.
    @Around("publicTarget()")
    // ProceedingJoinPoint 타입 파라미터는 프록시 대상 객체의 메서드를 호출할 때 사용한다. proceed() 메서드를 사용해서 실제 대상 객체의 메서드를 호출한다.
    public Object measure(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.nanoTime();

        try {
            // joinPoint 는 프록시객체, proceed()하면 실제 대상 객체
            Object result = joinPoint.proceed();
            return result;
        }finally {
            long finish = System.nanoTime();
            // 자바에서 메서드 이름과 파라미터 이름을 합쳐서 메서드 시그니처라고 한다. 메서드 이름이 다르거나 파라미터 타입, 개수가 다르면 시그니처가 다르다고 표현한다. 리턴 타입이나, 익셉션 타입은 시그니처에 포함되지 않는다.
            Signature sig = joinPoint.getSignature();
            System.out.printf("%s.%s(%s) 실행 시간 : %d ns\n",
                    joinPoint.getTarget().getClass().getSimpleName(),
                    sig.getName(), Arrays.toString(joinPoint.getArgs()),
                    (finish - start)
            );
        }
    }
}
