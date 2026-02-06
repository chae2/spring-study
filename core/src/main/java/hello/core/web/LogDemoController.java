package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
  private final LogDemoService logDemoService;
  private final ObjectProvider<MyLogger> loggerProvider;

  @RequestMapping("log-demo")
  @ResponseBody
  public String logDemo(HttpServletRequest request) throws InterruptedException {
    String requestUrl = request.getRequestURL().toString();

    MyLogger logger = loggerProvider.getObject();
    logger.setRequestURL(requestUrl);

    logger.log("controller test");
    Thread.sleep(1000);
    logDemoService.logic("testId");
    return "OK";
  }
}
