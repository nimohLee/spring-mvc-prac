package config;

import aspect.CacheAspect;
import aspect.ExeTimeAspect;
import chap07.Calculator;
import chap07.RecCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AppCtxWithCache {

    // Bean을 등록한 순서에 따라 프록시 객체가 바뀜
    // CacheAspect가 먼저이기 때문에 CacheAspect의 프록시 대상 객체가 ExeTimeAspect가 됨. 반대의 경우 프록시 대상 객체도 반대를 가리킴
    @Bean
    public CacheAspect cacheAspect(){
        return new CacheAspect();
    }

    @Bean
    public ExeTimeAspect exeTimeAspect() {
        return new ExeTimeAspect();
    }

    @Bean
    public Calculator calculator() {
        return new RecCalculator();

    }
}
