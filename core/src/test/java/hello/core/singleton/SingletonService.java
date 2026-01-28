package hello.core.singleton;

public class SingletonService {
    private static final SingletonService i = new SingletonService();
    //자기 자신을 내부에 하나 스태틱으로 가지게 되면 딱 하나만. -> 자바 기본에 나오는 것.

    public static SingletonService getInstance(){
        return i;
    }

    private SingletonService(){

    }
    public void logic(){
        System.out.println("싱글톤 객체 호출");
    }
}
