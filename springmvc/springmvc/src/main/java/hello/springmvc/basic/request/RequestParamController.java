package hello.springmvc.basic.request;


import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
public class RequestParamController {

    /*
    반환 타입이 없으면서 이렇게 응답에 값을 직접 넣으면 , view 조회 x
     */
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }
    /**
     * @RequestParam 사용
     * -파라미터 이름으로 바인딩
     * @ResponseBody 추가
     * - view 조회를 무시하고, 바디에 직접 해당 내용 입력
     */

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestV2(@RequestParam("username") String memberName,
                            @RequestParam("age") int memberAge) {
        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    /**
     *
     * @param username
     * @param age
     * @return
     * @RequestParam 파라미터랑 변수 같으면 생략가능
     */
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestV3(@RequestParam String username,
                            @RequestParam int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }


    /**
     *
     string, int 단순 타입이면 requestParam도 생략가능
     */
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestV4(String username,
                             int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /*
    @ModelAttribute 하니 HelloData 객체가 생성되고 요청 파라미터 값들도 모두 들어갔다
     ModelAttribute -> 요청 파라미터이름으로 객체의 프로퍼티를 찾는다,
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username ={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";

    }

    /**
     * @ModelAttribute 생략 가능
     * argument resolver 로 지정해둔 타입 외 = @ModelAttribut로..
     * 스프링은 해당 생략시 다음과 같은 규칙을 적용한다.
     * String , int , Integer 같은 단순 타입 = @RequestParam
     * 나머지 = @ModelAttribute (argument resolver 로 지정
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2( HelloData helloData) {
        log.info("username ={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }


}
