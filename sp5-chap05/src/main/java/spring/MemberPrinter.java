package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import java.time.format.DateTimeFormatter;

public class MemberPrinter {

    private DateTimeFormatter dateTimeFormatter;

    public MemberPrinter() {
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
    }

    public void print(Member member) {
        if (dateTimeFormatter == null) {
            System.out.printf(
                    "회원정보: 아이디 =%d, 이메일=%s, 이름=%s, 등록일=%tF\n",
                    member.getId(), member.getEmail(), member.getName(), member.getRegisterDateTime()
            );
        }else {
            System.out.printf(
                    "회원정보: 아이디 =%d, 이메일=%s, 이름=%s, 등록일=%s\n",
                    member.getId(), member.getEmail(), member.getName(), dateTimeFormatter.format(member.getRegisterDateTime()));
        }
    }

//    @Autowired(required = false) // 매칭되는 빈이 없어도 익셈션이 발생하지 않으며 자동 주입을 수행하지 않는다.
//    public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
//        this.dateTimeFormatter = dateTimeFormatter;
//    }

//    @Autowired // required false 안 쓰고 파라미터를 Optional로 받아서 null 처리할 수도 있음
//    public void setDateTimeFormatter(Optional<DateTimeFormatter> dateTimeFormatter) {
//        if (dateTimeFormatter.isPresent()) {
//            this.dateTimeFormatter = dateTimeFormatter.get();
//        } else {
//            this.dateTimeFormatter = null;
//        }
//    }

    @Autowired // @Nullable 붙일 수도 있음 -> 메서드 파라미터 필드 다 됨 => 다만, 자동 주입할 빈이 존재하지 않더라도 메서드가 호출됨, null이면 null을 주입함 <-> required false는 빈 없으면 호출안함 -
    public void setDateFormatter(@Nullable DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }
}
