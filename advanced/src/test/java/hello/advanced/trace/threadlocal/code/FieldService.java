package hello.advanced.trace.threadlocal.code;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class FieldService { // 그냥 스트링 받아서 저장하는 로직

  private String nameStore;

  public String logic(String name) {
    log.info("저장 name={} -> nameStore={}", name, nameStore);
    nameStore = name;
    sleep(1000); // 저장에 1초 걸림
    log.info("조회 nameStore={}", nameStore);
    return nameStore;
  }

  private void sleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
