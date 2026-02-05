package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeTest {

  @Test
  public void prototypeBeanFind() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
    PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
    PrototypeBean bean2 = ac.getBean(PrototypeBean.class);

    System.out.println("prototypeBean 1: " + bean1);
    System.out.println("prototypeBean 2: " + bean2);

    Assertions.assertNotEquals(bean1, bean2);

    ac.close();

  }

  @Scope("prototype")
  static class PrototypeBean {
    @PostConstruct
    public void init() {
      System.out.println("PrototypeBean#init");
    }

    @PreDestroy
    public void destroy() {
      System.out.println("PrototypeBean#destroy");
    }
  }
}
