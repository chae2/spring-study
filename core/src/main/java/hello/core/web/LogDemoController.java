package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
  private final LogDemoService logDemoService;
  private final MyLogger logger; // 2. request scope에 proxy 옵션 추가
//  private final ObjectProvider<MyLogger> loggerProvider; // 1. ObjectProvider로 빈 사용 지연

  @RequestMapping("log-demo")
  @ResponseBody
  public String logDemo(HttpServletRequest request) throws InterruptedException {
    String requestUrl = request.getRequestURL().toString();

    System.out.println("myLogger = " + logger.getClass());

//    MyLogger logger = loggerProvider.getObject(); // 1. ObjectProvider로 빈 사용 지연
    logger.setRequestURL(requestUrl);

    logger.log("controller test");
    Thread.sleep(1000);
    logDemoService.logic("testId");
    return "OK";
  }
}
