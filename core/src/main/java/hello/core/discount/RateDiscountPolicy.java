package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
//@MainDiscountPolicy //@Qualifier("mainDiscountPolicy") // 직접 만든 어노테이션이 @Qualifier보다 낫다!
//@Primary // 기본값 (중복이면 기본적으로 얘)
public class RateDiscountPolicy implements DiscountPolicy { // 할인된 금액을 리턴.
  private int discountPercent = 10;

  @Override
  public int discount(Member member, int price) {
    if (member.getGrade() == Grade.VIP) {
      return price * discountPercent / 100;
    }
    {
      return 0;
    }
  }
}
