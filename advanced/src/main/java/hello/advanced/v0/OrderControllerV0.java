package hello.advanced.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Controller + ResponseBody
@RequiredArgsConstructor
public class OrderControllerV0 {
  private final OrderServiceV0 orderService;

  @GetMapping("/v0/request")
  public String request(String itemId){
    orderService.orderItem(itemId);
    return "Let's fetch you "+itemId; // Rest라서 웹에 그대로 문자가 뜨게 됨.
  }
}
