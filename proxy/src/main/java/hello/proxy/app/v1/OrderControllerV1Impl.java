package hello.proxy.app.v1;

public class OrderControllerV1Impl implements OrderControllerV1 {

  private final OrderServiceV1 orderService;

  public OrderControllerV1Impl(OrderServiceV1 orderService) {
    this.orderService = orderService;
  }

  @Override
  public String request(String itemId) { // LogTrace 적용 대상
    orderService.orderItem(itemId);
    return "Just fetched " + itemId;
  }

  @Override
  public String nolog() { // LogTrace 적용 안하는 대상
    return "no log print";
  }
}
