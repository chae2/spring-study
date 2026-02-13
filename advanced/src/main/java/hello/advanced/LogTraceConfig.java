package hello.advanced;

import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

  @Bean
  public LogTrace logTrace() {
//        return new FieldLogTrace(); // [필드 동기화] 버전 - 싱글톤으로 로그추적기 등록.
    return new ThreadLocalLogTrace(); // 동시성 문제 해결 [ThreadLocal] 버전.
  }
}
