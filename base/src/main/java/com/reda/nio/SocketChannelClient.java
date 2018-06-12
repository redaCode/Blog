package com.reda.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SocketChannelClient {
    public static void main(String[] args) {
        try {
            SocketChannel channel = SocketChannel.open();
            channel.configureBlocking(false);
            Selector selector = Selector.open();
            //register OP_CONN to server
            channel.register(selector, SelectionKey.OP_CONNECT);

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (true) {
                if (selector.select() > 0) {
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> itor = selectionKeys.iterator();
                    while (itor.hasNext()) {
                        SelectionKey key = itor.next();
                        if (key.isConnectable()) {
                            channel.connect(new InetSocketAddress("127.0.0.1",8100));
                            SocketChannel client = (SocketChannel) key.channel();
                            if (client.isConnectionPending()) {
                                client.finishConnect();
                                buffer.clear();
                                buffer.put("Connected.".getBytes());
                                buffer.flip();
                                client.write(buffer);
                            }
                            client.register(selector,SelectionKey.OP_READ);
                        }
                        else if (key.isReadable()&& key.isValid() ) {
                            //拿到服务器的通道
                            SocketChannel client = (SocketChannel) key.channel();
                            buffer.clear();
                            int size = client.read(buffer);
                            if(size > 0) {
                                System.out.println(new String(buffer.array(),0,size));
                            }
                            client.register(selector,SelectionKey.OP_WRITE);
                        }
                        else if (key.isWritable() && key.isValid()) {
                            SocketChannel client = (SocketChannel) key.channel();
                            buffer.clear();
                            buffer.put("call again.".getBytes());
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
