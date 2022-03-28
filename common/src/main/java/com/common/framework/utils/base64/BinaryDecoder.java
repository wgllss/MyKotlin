package com.common.framework.utils.base64;

/**
 * @author：atar
 * @date: 2019/7/9
 * @description:
 */
public interface BinaryDecoder {

    byte[] decode(byte[] var1) throws DecoderException;
}
