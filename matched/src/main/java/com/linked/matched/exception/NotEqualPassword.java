package com.linked.matched.exception;

public class NotEqualPassword extends MatchException{

    private static final String MESSAGE= "비밀번호가 일치하지 않습니다.";

    public NotEqualPassword() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
