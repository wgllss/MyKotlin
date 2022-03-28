package com.common.framework.utils.base64;

/**
 * @author：atar
 * @date: 2019/7/9
 * @description:
 */
public class EncoderException extends Exception {
    private static final long serialVersionUID = 1L;

    public EncoderException() {
    }

    public EncoderException(String message) {
        super(message);
    }

    public EncoderException(String message, Throwable cause) {
        super(message, cause);
    }

    public EncoderException(Throwable cause) {
        super(cause);
    }
}
