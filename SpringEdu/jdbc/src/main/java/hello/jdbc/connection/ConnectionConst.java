package hello.jdbc.connection;

// DB 연결에 대한 정보들을 담고 있기 때문에 객체로 생성되면 안된다. 따라서 추상 클래스로 생성한다.
public abstract class ConnectionConst {
    public static final String URL = "jdbc:h2:tcp://localhost/~/test";
    public static final String USERNAME = "sa";
    public static final String PASSWORD = "";
}
