package hello.springmvc.basic.request;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@RequestMapping("/request-param-required")
@ResponseBody
public class RequestParamRequired {

    public String requestParamRequired(@RequestParam(required = true) String username,
                                       @RequestParam(required = false) Integer age) {

      log.info("username={}, age={}", username, age);
      return "ok";
    }
}
