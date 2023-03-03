package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import spring.DuplicateMemberException;
import spring.MemberRegisterService;
import spring.RegisterRequest;

import javax.validation.Valid;

@Controller
public class RegisterController {

    private MemberRegisterService memberRegisterService;

    public void setMemberRegisterService(MemberRegisterService memberRegisterService) {
        this.memberRegisterService = memberRegisterService;
    }

    @RequestMapping("/register/step1")
    public String handleStep1() {
        return "register/step1";
    }

    @PostMapping("/register/step2")
    public String handleStep2(
            @RequestParam(value = "agree", defaultValue = "false") Boolean agree,
            Model model
    ) {
        if (!agree) {
            return "register/step1";
        }
        model.addAttribute("registerRequest", new RegisterRequest());
        return "register/step2";
    }

    @GetMapping("/register/step2")
    public String handleStep2Get() {
        return "redirect:/register/step1"; // redirect: 로 리다이렉트
    }

    // ModelAttribute로 view에서 사용할 커맨드 객체의 이름을 지정해줄 수 있음
    @PostMapping("/register/step3")
    public String handleStep3(@Valid RegisterRequest regReq /* @Valid를 하면 메서드 실행 전에 검증함*/, Errors errors /* 무조건 요청 객체 바로 뒤에 Errors가 와야함! Errors를 대신해서 BindingResult 인터페이스를 사용해도 됨*/) {
//        new RegisterRequestValidator().validate(regReq, errors); // MvcConfig.java에서 글로벌 validator로 RegisterRequestValidator를 설정해주었기 떄문에 생성 필요없음
        if (errors.hasErrors()) {
            return "register/step2";
        }
        try {
            memberRegisterService.regist(regReq);
            return "register/step3";
        } catch (DuplicateMemberException exception) {
            errors.rejectValue("email","duplicate");
            return "register/step2";
        }
    }

//    컨트롤러 범위 검증
//    @InitBinder
//    protected void initBinder(WebDataBinder binder) {
//        binder.setValidator(new RegisterRequestValidator());
//    }
}
