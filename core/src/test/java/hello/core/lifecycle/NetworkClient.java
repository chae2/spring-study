package hello.core.lifecycle;

public class NetworkClient { // implements InitializingBean, DisposableBean {
  private String url;

  public NetworkClient() {
    System.out.println("생성자 호출, url = " + url);
    connect();
    call("초기화 연결 메시지");
  }

  public void setUrl(String url) {
    this.url = url;
  }

  // 서비스 시작 시 호출
  public void connect() {
    System.out.println("connect: " + url);
  }

  public void call(String message) {
    System.out.println("call: " + url + " message: " + message);
  }

  // 서비스 종료 시 호출
  public void disconnect() {
    System.out.println("close: " + url);
  }

  public void init() { // 의존관계 주입 후에 진행됨
    System.out.println("NetworkClient#init");
    connect();
    call("초기화 연결 메시지");
  }

  public void close() {
    System.out.println("NetworkClient#close");
    disconnect();
  }

  // 빈 생명주기 콜백 - 1. 인터페이스 방법
  /*@Override
  public void afterPropertiesSet() throws Exception { // 의존관계 주입 후에 진행됨
    System.out.println("NetworkClient#afterPropertiesSet");
    connect();
    call("초기화 연결 메시지");
  }

  @Override
  public void destroy() throws Exception {
    System.out.println("NetworkClient#destroy");
    disconnect();
  }*/
}
