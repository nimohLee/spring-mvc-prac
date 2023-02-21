package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import spring.*;
// 각 클래스에서 Component 애노테이션을 붙여줬으므로 설정파일에서는 뺌
@Configuration
@ComponentScan(basePackages = {"spring"}) // spring 패키지와 그 하위에 속한 클래스들을 스캔 대상으로 설정
public class AppCtx {

    // 빈 이름이 동일한 경우 @Component를 해서 컴포넌트 스캔으로 자동으로 빈 등록 보다는 수동 빈 등록이 우선된다.
    // 빈 이름이 다른 경우 자동과 수동 모두 등록되고 사용할 때 Qualifier로 사용할 빈을 지정해줘야한다.
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
    public VersionPrinter versionPrinter() {
        VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(5);
        versionPrinter.setMinorVersion(0);
        return versionPrinter;
    }
}
