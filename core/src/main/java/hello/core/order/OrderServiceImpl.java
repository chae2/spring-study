package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;

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
public class OrderServiceImpl implements OrderService{
    private final MemberRepository mm;
    private DiscountPolicy dd;

    public OrderServiceImpl(MemberRepository mm, DiscountPolicy dd) {
        this.mm = mm;
        this.dd = dd;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = mm.findById(memberId);
        int amt = dd.discount(member, itemPrice); // member, price
        return new Order(memberId, itemName, itemPrice, amt);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository(){
        return mm;
    }
}