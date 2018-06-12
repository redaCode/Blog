package com.reda.nio;

import java.nio.*;

public class BufferTest {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.wrap(new byte[]{0,0,0,0,0,0,0,'a'});
        //byteBUffer result:0 0 0 0 0 0 0 97
        while (buffer.hasRemaining())
            System.out.print(buffer.get() + " ");
        //CharBuffer result:      a
        CharBuffer charBuffer = ((ByteBuffer)buffer.rewind()).asCharBuffer();
        while (charBuffer.hasRemaining())
            System.out.print(charBuffer.get() + " ");
        //FloatBuffer result: 0.0 1.36E-43
        FloatBuffer floatBuffer = ((ByteBuffer)buffer.rewind()).asFloatBuffer();
        while (floatBuffer.hasRemaining())
            System.out.print(floatBuffer.get() + " ");
        //IntBuffer result:0 97
        IntBuffer intBuffer = ((ByteBuffer)buffer.rewind()).asIntBuffer();
        while (intBuffer.hasRemaining())
            System.out.print(intBuffer.get() + " ");
        //ShortBuffer result:0 0 0 97
        ShortBuffer shortBuffer = ((ByteBuffer)buffer.rewind()).asShortBuffer();
        while (shortBuffer.hasRemaining())
            System.out.print(shortBuffer.get() + " ");
        //LongBuffer result:97
        LongBuffer longBuffer = ((ByteBuffer)buffer.rewind()).asLongBuffer();
        while (longBuffer.hasRemaining())
            System.out.print(longBuffer.get() + " ");
        //DoubleBuffer result：4.8E-322
        DoubleBuffer doubleBuffer = ((ByteBuffer)buffer.rewind()).asDoubleBuffer();
        while (doubleBuffer.hasRemaining())
            System.out.print(doubleBuffer.get() + " ");
    }
}
