package com.linked.matched.exception;

public class InvalidRefreshToken extends MatchException{


    private static final String MESSAGE = "refreshToken이 유효하지 않습니다.";

    public InvalidRefreshToken() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 401;
    }
}
