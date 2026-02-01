package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//public class OrderServiceImpl implements OrderService{
//
//    private DiscountPolicy dd = new FixDiscountPolicy();
//    private MemberServiceImpl mm = new MemberServiceImpl();
//
//    @Override
//    public Order createOrder(Long memberId, String itemName, int itemPrice) {
//        Member member = mm.findMember(memberId);
//        int amt = dd.discount(member, itemPrice); // member, price
//        return new Order(memberId, itemName, itemPrice, amt);
//    }
//}
@Component
public class OrderServiceImpl implements OrderService {
  private final MemberRepository mm;
  private DiscountPolicy dd;

  @Autowired
  public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
    this.mm = memberRepository;
    this.dd = discountPolicy;
  }

  @Override
  public Order createOrder(Long memberId, String itemName, int itemPrice) {
    Member member = mm.findById(memberId);
    int amt = dd.discount(member, itemPrice); // member, price
    return new Order(memberId, itemName, itemPrice, amt);
  }

  // 테스트 용도
  public MemberRepository getMemberRepository() {
    return mm;
  }
}