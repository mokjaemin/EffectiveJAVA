package Item9;

import java.io.*;

// Item9 : try-finally 보다는 try-with-resources를 사용하라.
public class main {
    public static void main(String[] args) throws IOException {

        // 자바 라이브러리에서는 close메서드를 통해 닫아줘야하는 메서드들이 많다.
        // InputStream, OutputStream, java.sql.Connection 등이 예이다.

        // 자원이 제대로 닫힘을 보장하자
        // - 클라이언트가 close 메서드를 놓치는 경우가 존재한다.
        // - 안전망으로 finalizer 메서드를 활용하지만 믿을만하지 못한다.

        // 전통적인 방법 try-finally
        String path = "/";
        BufferedReader br = new BufferedReader(new FileReader(path));
        try{
            String result = br.readLine();
        }
        finally {
            br.close();
        }

        // 자원이 두개이상인 경우
        String src = "/";
        String dst = "/";
        InputStream in = new FileInputStream(src);
        try{
            OutputStream out = new FileOutputStream(dst);
            try{
                byte[] buf = new byte[10];
                int n = 1;
                while((n = in.read(buf)) >= 0){
                    out.write(buf, 0, n);
                }
            }
            finally {
                out.close();
            }
        }
        finally {
            in.close();
        }

        // 두 코드의 문제점
        // - 자원이 많아질수록 코드가 지저분해진다.
        // - close 메서드를 제대로 구현한 경우가 드문 예시가 많다.
        // - try, finally는 모두 예외를 발생시키는데, 만약 try, finally 모두 예외를 발생시킬 문제가 발생된다면
        // 첫번째로 발생한 오류는 두번째 오류에 묻히게 되고 이는 디버깅을 어렵게 만든다.
        // - 예를들어, 예외발생시 try부분의 예외는 묻히고 close부분의 예외만 발생


        // 자바7부터는 try-with-sources로 이를 해결
        // - 이를 활용하기 위해서는 해당 자원이 AutoCloseable 인터페이스를 구현해야 함.
        // - 보통은 만들어진 클래스들은 Closable인터페이스가 AutoCloseable을 구현해놓았음.
        // (AutoCloseable 인터페이스는 오직 void를 반환하는 close 메서드만 정의되어 있다.)
        // - 닫아야하는 자원을 뜻하는 클래스를 작성한다면 AutoCloseable을 꼭 구현하자.
        // - 예외 발생시 try부분의 예외는 기록되고 close 부분에 예외는 숨겨짐.
        // - 자바 7에서는 Throwable에 추가된 getSuppressed메서드를 통해 출력 가능
        // - 이 후, Catch 절도 작성 가능

        // 사용하기 좋은 예시 : 파일처리, 네트워크 연결(socket 등), 데이터베이스 Connection Pool


        // 위의 두가지 예시를 수정
        // 아래 코드로 close 역할까지 수행가능

        // 1
        String path1 = "/";
        try(BufferedReader br1 = new BufferedReader(new FileReader(path))){
            String result = br1.readLine();
        }

        // 2
        String src1 = "/";
        String dst1 = "/";
        try(InputStream in1 = new FileInputStream(src);
            OutputStream out1 = new FileOutputStream(dst)){
                byte[] buf = new byte[10];
                int n = 1;
                while((n = in1.read(buf)) >= 0){
                    out1.write(buf, 0, n);
                }
        }
    }
}
