package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


// Component에 이름을 따로 정해주지 않으면 클래스 이름의 가장 첫 글자를 소문자로 변경한 뒤 빈 이름으로 사용
@Component
public class MemberRegisterService {


    @Autowired
    private MemberDao memberDao;

    public MemberRegisterService() {

    }

    public Long regist(RegisterRequest req) {
        Member member = memberDao.selectByEmail(req.getEmail());
        if (member != null) {
            throw new DuplicateMemberException("dup email " + req.getEmail());
        }
        Member newMember = new Member(
                req.getEmail(), req.getPassword(), req.getName(),
                LocalDateTime.now()
        );
        memberDao.insert(newMember);
        return newMember.getId();
    }
}
