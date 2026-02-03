package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
  MemberService memberService;
  OrderService orderService;

  @BeforeEach
  public void beforeEach() {
    AppConfig appConfig = new AppConfig();
    memberService = appConfig.memberService();
    orderService = appConfig.orderService();
  }

  @Test
  void createOrder() {
    Long memberId = 1L;
    Member chaeil = new Member(memberId, "Chaeil", Grade.VIP);
    memberService.join(chaeil);
    Order order = orderService.createOrder(memberId, "Dyson청소기", 100000);
    Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
  }

  @Test
  void fieldInjectionTest() {
    // 3. 필드 주입 테스트 - 생성자 지우고
//    OrderServiceImpl orderService = new OrderServiceImpl();
//
//    // 자바 Test 코드를 위해서는 setter가 필요해짐!
//    orderService.setMemberRepository(new MemoryMemberRepository());
//    orderService.setDiscountPolicy(new FixDiscountPolicy());
//
//    orderService.createOrder(1L, "itemA", 10000);
  }
}
