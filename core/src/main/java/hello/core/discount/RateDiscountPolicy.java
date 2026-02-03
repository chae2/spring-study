package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
// @Qualifier("mainDiscountPolicy")
@Primary // 기본값 (중복이면 기본적으로 얘)
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
