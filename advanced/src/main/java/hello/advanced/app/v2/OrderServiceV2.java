package hello.advanced.app.v2;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.helloTrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

  private final OrderRepositoryV2 orderRepository;
  private final HelloTraceV2 trace;

  public void orderItem(TraceId traceId, String itemId) {
    TraceStatus status = null; // try 안에 넣을 수 없음..
    try {
      status = trace.beginSync(traceId, "OrderService.orderItem()");
      // 핵심 로직
      orderRepository.save(status.getTraceId(), itemId);

      trace.end(status);
    } catch (Exception e) {
      trace.exception(status, e);
      throw e; // 로그 추적기 요구사항 - 서비스에 영향을 줘선 안된다: 예외를 꼭 다시 던져줘야 함.
    }
  }
}
