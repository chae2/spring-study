package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.template.Callback;
import hello.advanced.trace.strategy.code.template.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateCallbackTest {

  /*
   * 템플릿 콜백 패턴 - 익명내부클래스
   * */
  @Test
  void callbackV1() {
    TimeLogTemplate template = new TimeLogTemplate();
    template.execute(new Callback() {
      @Override
      public void call() {
        log.info("템플릿콜백의 v1 로직");
      }
    });
  }

  /*
   * 템플릿 콜백 패턴 - 람다
   * */
  @Test
  void callbackV2() {
    TimeLogTemplate template = new TimeLogTemplate();
    template.execute(() -> log.info("템플릿콜백의 v2 람다 로직"));
  }
}
