package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {
  private final MemberRepository memberRepository; // 그냥 인터페이스만 가지면 nullpoint exception

  // 완전히 추상화에만 의존하게 됨.
  @Autowired
  public MemberServiceImpl(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  // 다형성에 의해 memberRepository에 override한 save가 호출될 것.
  @Override
  public void join(Member member) {
    memberRepository.save(member);
  }

  @Override
  public Member findMember(Long memberId) {
    return memberRepository.findById(memberId);
  } // 구현체 하나면 impl을 뒤에 관례상 쓴다.

  // 테스트 용도
  public MemberRepository getMemberRepository() {
    return memberRepository;
  }
}
