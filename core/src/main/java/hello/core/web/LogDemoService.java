package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {
  private final MyLogger logger; // 2. request scope에 proxy 옵션 추가
//  private final ObjectProvider<MyLogger> loggerProvider; // 1. ObjectProvider로 빈 사용 지연

  public void logic(String id) {
//    MyLogger logger = loggerProvider.getObject(); // 1. ObjectProvider로 빈 사용 지연
    logger.log("service id = " + id);
  }
}
