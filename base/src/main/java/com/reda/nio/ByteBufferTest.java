package com.reda.nio;



import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class ByteBufferTest {
    public static void main(String[] args) {
        try {
            FileInputStream is = new FileInputStream("/home/reda/Documents/test/1.txt");
            FileOutputStream fos = new FileOutputStream("/home/reda/Documents/test/1.txt");
            FileOutputStream os = new FileOutputStream("/home/reda/Documents/test/2.txt");
            FileChannel inChannel = is.getChannel();
            FileChannel fosChannel = fos.getChannel();
            FileChannel outChannel = os.getChannel();

            //特殊情况，直接在channel传输
//            inChannel.transferTo(0,inChannel.size(),outChannel);
//            outChannel.transferFrom(inChannel,0,inChannel.size());

            ByteBuffer buffer = ByteBuffer.allocate(16);
            //写入内容
            fosChannel.write(ByteBuffer.wrap("1234QAQ".getBytes("UTF-8")));
//            while(inChannel.read(buffer) != -1) {
//                buffer.flip();
//                outChannel.write(buffer);
//                //必须clear不然除了第一次，后面将读不进内容，重复写入第一次读到的内容
//                buffer.clear();
//            }

            buffer.clear();
            fosChannel.close();
            inChannel.read(buffer);
            buffer.flip();
            //将positon的位置标记在起始位置，将ByteBuffer中每个字节转为字符
            buffer.mark();
            while (buffer.hasRemaining()) {
                System.out.print((char)buffer.get());
            }
            System.out.println();
            //将positon的位置重置
            buffer.reset();
            CharBuffer charBuffer = buffer.asCharBuffer();
            //打印乱码,ByteBuffer直接转成CharBuffer不行
            System.out.println(charBuffer.toString());
            //将ByteBuffer解码成utf8后，则能重新正确打印
            CharBuffer charBuffer2 = Charset.forName("UTF-8").decode(buffer);
            System.out.println(charBuffer2.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
