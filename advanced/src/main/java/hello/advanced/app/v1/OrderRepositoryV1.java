package hello.advanced.app.v1;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.helloTrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {

  private final HelloTraceV1 trace;

  public void save(String itemId) {

    TraceStatus status = null; // try 안에 넣을 수 없음..
    try {
      status = trace.begin("OrderRepository.save()");
      // 핵심 로직
      if (itemId.equals("ex")) {
        throw new IllegalStateException("예외 발생!");
      }
      sleep(1000);

      trace.end(status);
    } catch (Exception e) {
      trace.exception(status, e);
      throw e; // 로그 추적기 요구사항 - 서비스에 영향을 줘선 안된다: 예외를 꼭 다시 던져줘야 함.
    }
  }

  private void sleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
