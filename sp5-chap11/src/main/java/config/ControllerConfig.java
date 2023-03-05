package config;

import controller.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.*;

@Configuration
public class ControllerConfig {

    @Autowired
    private MemberRegisterService memberRegisterService;
    @Autowired
    private AuthService authService;

    @Autowired
    private ChangePasswordService changePasswordService;

    @Autowired
    private MemberDao memberDao;

    @Bean
    public MemberListController memberListController() {
        MemberListController memberListController = new MemberListController();
        memberListController.setMemberDao(memberDao);
        return memberListController;
    }
    @Bean
    public RegisterController registerController() {
        RegisterController controller = new RegisterController();
        controller.setMemberRegisterService(memberRegisterService);
        return controller;
    }

    @Bean
    public SurveyController surveyController() {
        return new SurveyController();
    }

    @Bean
    public LoginController loginController() {
        LoginController loginController = new LoginController();
        loginController.setAuthService(authService);
        return loginController;
    }

    @Bean
    public LogoutController logoutController() {
        return new LogoutController();
    }

    @Bean
    public ChangePwdController changePwdController() {
        ChangePwdController controller = new ChangePwdController();
        controller.setChangePasswordService(changePasswordService);
        return controller;
    }

    @Bean
    public MemberDetailController memberDetailController() {
        MemberDetailController controller = new MemberDetailController();
        controller.setMemberDao(memberDao);
        return controller;
    }

    @Bean
    public RestMemberController restApi() {
        RestMemberController cont = new RestMemberController();
        cont.setMemberDao(memberDao);
        cont.setRegisterService(memberRegisterService);
        return cont;
    }
}
