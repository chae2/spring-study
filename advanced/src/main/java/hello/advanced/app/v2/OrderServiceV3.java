package hello.advanced.app.v2;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

    private final OrderRepositoryV3 orderRepository;
    private final LogTrace trace;

    public void orderItem(String itemId) {
        TraceStatus status = null; // try 안에 넣을 수 없음..
        try {
            status = trace.begin("OrderService.orderItem()");
            // 핵심 로직
            orderRepository.save(itemId);

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e; // 로그 추적기 요구사항 - 서비스에 영향을 줘선 안된다: 예외를 꼭 다시 던져줘야 함.
        }
    }
}
