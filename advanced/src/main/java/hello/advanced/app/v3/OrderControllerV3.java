package hello.advanced.app.v3;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Controller + ResponseBody
@RequiredArgsConstructor
public class OrderControllerV3 {

    private final OrderServiceV3 orderService;
    private final LogTrace trace;

    @GetMapping("/v3/request")
    public String request(String itemId) {
        TraceStatus status = null; // try 안에 넣을 수 없음..
        try {
            status = trace.begin("OrderController.request()");
            // 핵심 로직
            orderService.orderItem(itemId);

            trace.end(status);
            return "Let's fetch you " + itemId; // Rest라서 웹에 그대로 문자가 뜨게 됨.}
        } catch (Exception e) {
            trace.exception(status, e);
            throw e; // 로그 추적기 요구사항 - 서비스에 영향을 줘선 안된다: 예외를 꼭 다시 던져줘야 함.
        }
    }
}
