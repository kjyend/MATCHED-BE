package com.linked.matched.exception;

public class AlreadyReport extends MatchException{
    private static final String MESSAGE= "이미 신고를 했습니다.";

    public AlreadyReport() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
