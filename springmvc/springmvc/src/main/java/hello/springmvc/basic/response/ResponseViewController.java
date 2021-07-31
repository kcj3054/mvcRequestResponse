package hello.springmvc.basic.response;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ResponseBody가 없으면 respon/hello로 뷰 리졸버가 실행되어 뷰 찾고 렌더링
 * 있으면 바디에 직접 response/hello 입력된다
 */
@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello");

        return mav;
    }

    //컨트롤러의 논리이름과 view의 이름과 동일하고 void.로  반환 x 하면   논리이름이 뷰이름으로 반환된다

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello!!!");

        //view 논리이름
        return "response/hello";
    }


}
