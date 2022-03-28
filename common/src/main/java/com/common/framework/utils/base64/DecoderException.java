package com.common.framework.utils.base64;

/**
 * @author：atar
 * @date: 2019/7/9
 * @description:
 */
public class DecoderException extends Exception {
    private static final long serialVersionUID = 1L;

    public DecoderException() {
    }

    public DecoderException(String message) {
        super(message);
    }

    public DecoderException(String message, Throwable cause) {
        super(message, cause);
    }

    public DecoderException(Throwable cause) {
        super(cause);
    }
}
