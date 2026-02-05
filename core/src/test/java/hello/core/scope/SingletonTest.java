package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonTest {

  @Test
  void singletonBeanFind() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);
    SingletonBean bean1 = ac.getBean(SingletonBean.class);
    SingletonBean bean2 = ac.getBean(SingletonBean.class);

    System.out.println("singletonBean 1: " + bean1);
    System.out.println("singletonBean 2: " + bean2);

    Assertions.assertEquals(bean1, bean2);

    ac.close();
  }

  @Scope("singleton")
  static class SingletonBean {
    @PostConstruct
    public void init() {
      System.out.println("SingletonBean#init");
    }

    @PreDestroy
    public void destroy() {
      System.out.println("SingletonBean#destroy");
    }
  }
}
