package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean
  public MemberService memberService() {
    System.out.println("call AppConfig.memberService()");
    return new MemberServiceImpl(memberRepository()); // AppConfig를 통해 들어감.
    // 객체 연결을 해주는 역할.
  }

  @Bean
  public MemberRepository memberRepository() {
    System.out.println("call AppConfig.memberRepository");
    return new MemoryMemberRepository();
  }

  @Bean
  public OrderService orderService() {
    System.out.println("call AppConfig.orderService()");
//    return new OrderServiceImpl(memberRepository(), discountPolicy());
    return null; // 의존관계 주입 중 [4. 필드 주입] 실습 시 이 설정 필요.
  }

  @Bean
  public DiscountPolicy discountPolicy() {
    return new FixDiscountPolicy();
//        return new RateDiscountPolicy();
  }
}
