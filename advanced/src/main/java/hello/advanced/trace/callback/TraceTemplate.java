package hello.advanced.trace.callback;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;

public class TraceTemplate {

  private final LogTrace trace;

  public TraceTemplate(LogTrace trace) {
    this.trace = trace;
  }

  public <T> T execute(String message, TraceCallback<T> callback) {
    TraceStatus status = null;
    try {
      status = trace.begin(message); // 이름은 어떻게 다르게 넣지. -> 매개변수
      T result = callback.call();
      trace.end(status);
      return result; // 리턴값을 어떻게 할 것인가. -> 비즈니스 코드의 리턴값으로
    } catch (Exception e) {
      trace.exception(status, e);
      throw e;
    }
  }

}
