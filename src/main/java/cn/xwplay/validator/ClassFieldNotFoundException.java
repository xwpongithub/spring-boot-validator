package cn.xwplay.validator;

import lombok.Getter;

@Getter
public class ClassFieldNotFoundException extends RuntimeException {

    private final int code = 400;

    public ClassFieldNotFoundException(String msg) {
        super(msg);
    }

}
