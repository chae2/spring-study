package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.ContextV1;
import hello.advanced.trace.strategy.code.strategy.Strategy;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {

  /*@Test
  void strategyV0() {
    logic1();
    logic2();
  }

  private void logic1() {
    long startTime = System.currentTimeMillis();
    // 비즈니스 로직 실행
    log.info("비즈니스 로직1 시작");
    // 비즈니스 로직 종료
    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;
    log.info("resultTime={}", resultTime);
  }

  private void logic2() {
    long startTime = System.currentTimeMillis();
    // 비즈니스 로직 실행
    log.info("비즈니스 로직2 시작");
    // 비즈니스 로직 종료
    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;
    log.info("resultTime={}", resultTime);
  }*/

  /*
   * 전략 패턴 사용
   * */
  @Test
  void strategyV1() {
    StrategyLogic1 s1 = new StrategyLogic1();
    ContextV1 context1 = new ContextV1(s1);
    context1.execute();

    StrategyLogic2 s2 = new StrategyLogic2();
    ContextV1 context2 = new ContextV1(s2);
    context2.execute();
  }

  // 익명 내부 클래스 구현
  @Test
  void strategyV2() {
    Strategy strategy = new Strategy() {
      @Override
      public void call() {
        log.info("익명 내부 클래스 - 로직 1");
      }
    };
    ContextV1 contextV1 = new ContextV1(strategy);
    log.info("strategyL logic1 = {}", strategy.getClass());
    contextV1.execute();
  }

  @Test
  void strategyV3() {
    // 위의 익명내부 클래스를 인라인으로 합침
    ContextV1 contextV1 = new ContextV1(new Strategy() {
      @Override
      public void call() {
        log.info("익명 내부 클래스 - 로직 1");
      }
    });
    contextV1.execute();
  }

  // 람다 사용
  @Test
  void strategyV4() {
    ContextV1 contextV1 = new ContextV1(() -> log.info("람다로 비즈니스로직1 실행"));
    contextV1.execute();
  }
}
