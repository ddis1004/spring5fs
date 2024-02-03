package spring;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(String msg){
        super(msg);
    }
}
