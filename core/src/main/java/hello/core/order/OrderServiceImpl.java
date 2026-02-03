package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
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
//@RequiredArgsConstructor // 생성자 주입 방법 쓸 필요 없음.
public class OrderServiceImpl implements OrderService {

  // 1. 생성자 주입 방법
  private final MemberRepository memberRepository;
  private final DiscountPolicy discountPolicy;

  //  @Autowired // 생성자 1개일 경우 생략 가능
  public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//    System.out.println("1. OrderServiceImpl.OrderServiceImpl");
    this.memberRepository = memberRepository;
    this.discountPolicy = discountPolicy;
  }

  // 2. 수정자 주입 방법
  /*private MemberRepository memberRepository;
  private DiscountPolicy discountPolicy;

  @Autowired
  public void setDiscountPolicy(DiscountPolicy discountPolicy) {
    System.out.println("discountPolicy = " + discountPolicy);
    this.discountPolicy = discountPolicy;
  }

  @Autowired
  public void setMemberRepository(MemberRepository memberRepository) {
    System.out.println("memberRepository = " + memberRepository);
    this.memberRepository = memberRepository;
  }*/

  @Override
  public Order createOrder(Long memberId, String itemName, int itemPrice) {
    Member member = memberRepository.findById(memberId);
    int amt = discountPolicy.discount(member, itemPrice); // member, price
    return new Order(memberId, itemName, itemPrice, amt);
  }

  // 테스트 용도
  public MemberRepository getMemberRepository() {
    return memberRepository;
  }

  // 4. 메서드 주입
  /*private MemberRepository memberRepository;
  private DiscountPolicy discountPolicy;

  public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
    System.out.println("1. OrderServiceImpl.OrderServiceImpl");
    this.memberRepository = memberRepository;
    this.discountPolicy = discountPolicy;
  }

  @Autowired
  public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
    this.memberRepository = memberRepository;
    this.discountPolicy = discountPolicy;
  }*/

}