package hello.advanced.trace.template;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;

public abstract class AbstractTemplate<T> {

  private final LogTrace trace;

  public AbstractTemplate(LogTrace trace) {
    this.trace = trace;
  }

  // 반환 여부가 달라지므로 Generic 사용
  public T execute(String message) {
    TraceStatus status = null;
    try {
      status = trace.begin(message); // 이름은 어떻게 다르게 넣지. -> 매개변수
      T result = business();
      trace.end(status);
      return result; // 리턴값을 어떻게 할 것인가. -> 비즈니스 코드의 리턴값으로
    } catch (Exception e) {
      trace.exception(status, e);
      throw e;
    }
  }

  // 비즈니스 코드
  protected abstract T business();
}
