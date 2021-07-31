package hello.springmvc.basic.request;


import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {

    //Jackson 라이브러리인 ObjectMapper 사용해서 객체로 변환 , json데이터 -> 객체
     private ObjectMapper objectMapper = new ObjectMapper();


     @PostMapping("/request-body-json-v1")
     public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
         ServletInputStream inputStream = request.getInputStream();
       String messageBody =  StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

       log.info("messageBody={}", messageBody);
         HelloData data = objectMapper.readValue(messageBody, HelloData.class);
         log.info("username={}, age={}", data.getUsername(), data.getAge());

         response.getWriter().write("ok");
     }


     //RequestBody를 이용해서 Http 메시지바디에있는 것을 꺼내고 messageBody에 저장한다
    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException{

         //문자로 갔다가 다시 json으로 갔다가 objectMapper를 이용해서 객체로 변환된다
         HelloData data = objectMapper.readValue(messageBody, HelloData.class);
         log.info("username={}, age={}", data.getUsername(), data.getAge());
         return "ok";
     }


     //@RequestBody에 우리가 직접만든 객체를 지정가능 .
    //RequestBody 생략하면 ModelAttribute가 적용된다
    @ResponseBody
     @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData data) {
         log.info("username={}", data.getUsername());
         return "ok";
     }


     //RequestBody요청 -> Json요청 -> 메시지 컨버터 -> 객체
    //ResponseBody 응답 -> 객체 -> 메시지 컨버터 -> json
     @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData data) {
         log.info("username ={}", data.getUsername());

         return data;
     }

}
