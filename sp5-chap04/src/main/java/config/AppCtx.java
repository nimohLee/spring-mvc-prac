package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.*;

@Configuration
public class AppCtx {

    @Bean
    public MemberDao memberDao() {
        return new MemberDao();
    }


    // memberPrinter가 있으면 memberPrinter를 가져옴. ( 다른 클래스에서 주입할 때 적은 이름이기 때문 )
//    @Bean
//    public MemberPrinter memberPrinter() {
//        return new MemberPrinter();
//    }

    @Bean
    @Qualifier("printer")
    public MemberPrinter memberPrinter1() {
        return new MemberPrinter();
    }

    // 주입한 이름과 다르지만 타입이 동일한 경우 자동으로 주입해줌. 하지만 동일 타입이 여러 개 있으면 에러 발생 Qualifier 하더라도 메서드 이름이 안 먹히는 건 아님.
    @Bean
    @Qualifier("summaryPrinter")
    public MemberSummanryPrinter memberPrinter2() {
        return new MemberSummanryPrinter();
    }

    @Bean
    public MemberRegisterService memberRegisterService() {
        return new MemberRegisterService();
    }

    @Bean
    public ChangePasswordService changePasswordService() {
        return new ChangePasswordService();
    }

    @Bean
    public MemberListPrinter memberListPrinter() {
        return new MemberListPrinter();
    }

    // 의존성을 직접 주입해줘도 MemberInfoPrinter 클래스에 있는 Autowired가 자동주입해줌.
    // 수동과 자동을 혼합해서 사용하면 NullPointerException이 발생할 수 있으므로 웬만하면 자동으로 통일하자
    @Bean
    public MemberInfoPrinter memberInfoPrinter() {
        MemberInfoPrinter memberInfoPrinter = new MemberInfoPrinter();
        memberInfoPrinter.setMemberPrinter(memberPrinter2());
        return memberInfoPrinter;
    }
    @Bean
    public VersionPrinter versionPrinter() {
        VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(5);
        versionPrinter.setMinorVersion(0);
        return versionPrinter;
    }
}
