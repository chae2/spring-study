package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
    //basePackages = "hello.core.member", // 시작 패키지부터 하위로 내려감
    excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) // 빈에서 뺄 것 지정해줌
    // 수동등록하는 AppConfig.java가 포함되면 충돌이 일어나기 때문에!
)
public class AutoAppConfig {
  @Bean(name = "memoryMemberRepository")
  MemberRepository memoryMemberRepository() {
    return new MemoryMemberRepository();
  }
}
