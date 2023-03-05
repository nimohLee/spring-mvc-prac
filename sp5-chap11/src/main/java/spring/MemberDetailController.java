package spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MemberDetailController {

    private MemberDao memberDao;
    Logger log = LoggerFactory.getLogger(MemberDetailController.class);

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @GetMapping("/members/{id}")
    public String detail(@PathVariable("id") Long memId, Model model) {
        Member member = memberDao.selectById(memId);
        if (member == null) {
            throw new MemberNotFoundException();
        }
        log.info(String.valueOf(member.getRegisterDateTime()));
        model.addAttribute("member", member);
        return "member/memberDetail";
    }

    @ExceptionHandler(TypeMismatchException.class)
    public String handleTypeMismatchException() {
        return "member/invalidId";
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public String handleNotFoundException() {
        return "member/noMember";
    }
}
