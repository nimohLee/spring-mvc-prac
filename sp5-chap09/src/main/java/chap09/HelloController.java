package chap09;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model, // Model 파라미터는 컨트롤러의 처리 결과를 뷰에 전달할 때 사용
                        @RequestParam(value = "name", required = false) String name){
        model.addAttribute("greeting", "안녕하세요," + name);
        return "hello"; // 논리적인 view의 이름이며 실제 ViewResolver가 찾아서 처리한다.
    }
}
