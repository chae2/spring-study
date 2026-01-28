package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService(); // memberServiceImpl을 appConfig가 주입해줌.
//        MemberService memberService = new MemberServiceImpl();
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        Member chaeil = new Member(1L, "Chaeil", Grade.VIP);
        memberService.join(chaeil);

        Member findMember = memberService.findMember(1L);
        System.out.println("new Member = " + chaeil.getName());
        System.out.println("find Member = "+findMember.getName());
        System.out.println("equal = "+chaeil.getName().equals(findMember.getName()));

    }
}
