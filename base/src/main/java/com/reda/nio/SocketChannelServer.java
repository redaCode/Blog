package com.reda.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SocketChannelServer {
    public static void main(String[] args) {
        try {
            //Selector.open初始化一个选择器
            Selector selector = Selector.open();
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            //将服务器channel配置为非阻塞
            serverChannel.configureBlocking(false);
            ServerSocket serverSocket = serverChannel.socket();
            InetSocketAddress address = new InetSocketAddress(8100);
            //服务绑定
            serverSocket.bind(address);
            //注册到选择器，等待链接
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Server Running.");

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (true) {
                if (selector.select() > 0 ) {
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> itor = selectionKeys.iterator();
                    while (itor.hasNext()) {
                        SelectionKey key = itor.next();
                        if (key.isAcceptable()) {
                            ServerSocketChannel server = (ServerSocketChannel) key.channel();
                            //拿到链接到服务器的通道，写数据回去，并将该通道注册到Selector
                            SocketChannel client = server.accept();
                            client.configureBlocking(false);
                            System.out.println("Server accept.");

                            buffer.clear();
                            buffer.put("1234QAQ".getBytes());
                            buffer.flip();
                            client.write(buffer);
                            client.register(selector,SelectionKey.OP_READ);
                        }
                        else if (key.isReadable() && key.isValid()) {
                            SocketChannel client = (SocketChannel) key.channel();
                            buffer.clear();
                            int size = client.read(buffer);
                            if (size > 0) {
                                System.out.println("Server receive message: " + new String(buffer.array(),0,size));
                            }
                            client.register(selector,SelectionKey.OP_WRITE);
                        }
                        else if (key.isWritable() && key.isValid()) {
                            SocketChannel client  = (SocketChannel) key.channel();
                            buffer.clear();
                            buffer.put("Got Replay.tks.".getBytes());
                            buffer.flip();
                            client.write(buffer);
                            client.register(selector,SelectionKey.OP_READ);
                        }
                        itor.remove();
                    }
                    selectionKeys.clear();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
