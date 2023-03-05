package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.Member;
import spring.MemberDao;

import java.util.List;

@Controller
public class MemberListController {

    private MemberDao memberDao;

    Logger logger = LoggerFactory.getLogger(MemberListController.class);

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @RequestMapping("/members")
    public String list(
            @ModelAttribute("cmd") ListCommand listCommand, Errors errors,
            Model model
    ) {
        logger.info("listCommand to : " + listCommand.getTo());
        logger.info("listCommand from : " + listCommand.getFrom());
        if (errors.hasErrors()) {
            return "member/memberList";
        }
        if (listCommand.getFrom() != null && listCommand.getTo() != null) {
            logger.info("들어옴");
            List<Member> members = memberDao.selectByRegDate(listCommand.getFrom(), listCommand.getTo());
            model.addAttribute("members", members);
        }
        return "member/memberList";
    }
}
