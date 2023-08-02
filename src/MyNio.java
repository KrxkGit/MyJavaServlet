import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;


public class MyNio {
    public static void Test() {
        File file = new File("D:/1.txt");
        File file1 = new File("D:/2.txt");
        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel in = null;
        FileChannel out = null;
        try {
            fi = new FileInputStream(file);
            fo = new FileOutputStream(file1);
            in = fi.getChannel();
            out = fo.getChannel();
            in.transferTo(0, in.size(), out);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                fi.close();
                in.close();
                fo.close();
                out.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void Test2() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        ServerSocketChannel serverSocketChannel = serverSocket.getChannel();
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        selector.select(2000);
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
        while (keyIterator.hasNext()) {
            SelectionKey key = keyIterator.next();
            if (key.isAcceptable()) {
                // a connection was accepted by the ServerSocket.
            }
        }
    }
}
