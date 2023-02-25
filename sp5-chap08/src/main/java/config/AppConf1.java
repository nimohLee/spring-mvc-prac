package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.MemberDao;
import spring.MemberPrinter;

@Configuration
public class AppConf1 {
    @Bean // 메서드 이름을 빈 객체의 이름으로 사용함. { memberDao : new MemberDao()}
    public MemberDao memberDao() {
        return new MemberDao();
    }

    @Bean
    public MemberPrinter memberPrinter() {
        return new MemberPrinter();
    }
}
