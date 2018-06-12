package com.reda.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class FileLockTest {
    public static void main(String[] args) {
        File file = new File("/home/reda/Documents/test/3.txt");
        FileOutputStream fos = null;
        FileChannel channel = null;
        try {
            fos = new FileOutputStream(file);
            channel = fos.getChannel();
            FileLock lock = channel.tryLock();
            ByteBuffer buffer = ByteBuffer.allocate(16);
            //TODO 字节数已经超过buffer大小了，是怎么写进去的？
            if (lock != null) {
                buffer = ByteBuffer.wrap("qwerasdfzxcv1234masdgfasdgashhsah".getBytes("UTF-8"));
            }
            channel.write(buffer);
            lock.release();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                channel.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
