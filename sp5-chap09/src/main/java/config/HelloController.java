package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("/hello") //경로가 hello인경우
    public String hello(Model model,                //Controller의 처리 결과를 model을 통해 view에 전달
                        @RequestParam(value="name", required=false) String name){
        model.addAttribute("greeting", "안녕하세요, "+ name);
        return "hello";
    }
}
