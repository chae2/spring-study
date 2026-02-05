package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingletonWithPrototypeTest1 {
  @Test
  void prototypeFind() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
    PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
    bean1.addCount();
    assertEquals(1, bean1.getCount());

    PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
    bean2.addCount();
    assertEquals(2, bean2.getCount());
  }

  @Test
  void singletonClientUsePrototype() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean.class);

    ClientBean client = ac.getBean(ClientBean.class);
    int count1 = client.logic();
    assertEquals(count1, 1);

    ClientBean client2 = ac.getBean(ClientBean.class); // 하나만 생성될 것. 싱글턴이라.
    int count2 = client2.logic();
    assertEquals(count2, 1);
  }

  @Scope("singleton")
  static class ClientBean {

    @Autowired
    private Provider<PrototypeBean> prototypeBeanProvider; // 2. 자바 Provider 사용
//    private ObjectProvider<PrototypeBean> prototypeBeanProvider; // 1. 스프링 ObjectProvider 사용


    // 0. 초기 문제재현 코드
    /*private final PrototypeBean prototypeBean; // 생성시점에 주입
    @Autowired
    public ClientBean(PrototypeBean prototypeBean) {
      this.prototypeBean = prototypeBean;
    }*/

    public int logic() {
      // 2. 자바 Provider 사용
      PrototypeBean pt = prototypeBeanProvider.get();

      // 1. 스프링 ObjectProvider 사용
//      PrototypeBean pt = prototypeBeanProvider.getObject(); // DL; 스프링 컨테이너 내에 해당 빈을 찾아 반환함.
      pt.addCount();
      return pt.getCount();

      // 0. 초기 문제재현 코드
      /*prototypeBean.addCount();
      return prototypeBean.getCount();*/
    }
  }

  @Scope("prototype")
  static class PrototypeBean {
    private int count = 0;

    public void addCount() {
      count++;
    }

    public int getCount() {
      return count;
    }

    @PostConstruct
    public void init() {
      System.out.println("prototypeBean#init: " + this); // 나의 참조값을 볼 수 있음
    }

    @PreDestroy
    public void destroy() {
      System.out.println("prototypeBean#destroy: " + this);
    }

  }
}
