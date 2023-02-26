package spring;

import config.Appctx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainForCPS {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Appctx.class);
        ChangePasswordService cps = ctx.getBean("changePasswordService", ChangePasswordService.class);
        Logger logger = LoggerFactory.getLogger(MainForCPS.class);
        try {
            cps.changePassword("madvirus@madvirus.net", "1234", "1111");
            System.out.println("암호를 변경했습니다");
        } catch (MemberNotFoundException e) {
            System.out.println("회원 데이터가 존재하지 않습니다");
        } catch (WrongIdPasswordException e) {
            System.out.println("암호가 올바르지 않습니다");
        }

        ctx.close();

    }
}
