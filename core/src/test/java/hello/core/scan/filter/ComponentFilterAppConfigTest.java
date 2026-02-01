package hello.core.scan.filter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComponentFilterAppConfigTest {
  @Test
  void filterScan() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
    BeanA beanA = ac.getBean("beanA", BeanA.class);
    assertNotNull(beanA);

    //BeanB beanB = ac.getBean("beanB", BeanB.class);
    //assertNotNull(beanB); // beanB는 아래처럼 ComponentScan에서 빠지게 되므로 당연히 테스트 불통.
    assertThrows(
        NoSuchBeanDefinitionException.class,
        () -> ac.getBean("beanB", BeanB.class)
    );

  }

  @Configuration
  @ComponentScan(
      includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
      excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)

  )
  static class ComponentFilterAppConfig {

  }
}
