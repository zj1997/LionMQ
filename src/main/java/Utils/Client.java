package Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import Common.Message;


/**
 *  NIO 客户端
 */
public class Client {
	SocketChannel socketChannel = null;
	volatile static int count;
	private String ip;
	private int port;
	public Client(String ip,int port) throws IOException {
		this.ip = ip;
		this.port = port;
	}

    private void init(String ip,int port) throws IOException {
    	//1.创建SocketChannel
		socketChannel=SocketChannel.open();
        //2.连接服务器
        socketChannel.connect(new InetSocketAddress(ip,port));
    }
    public String SyscSend(String msg) throws IOException{  
    		init(ip,port);
            //写数据
            ByteBuffer buffer=ByteBuffer.allocate(1024*1024);
            buffer.clear();
            buffer.put(msg.getBytes("ISO-8859-1"));
            buffer.flip();
            socketChannel.write(buffer);
            socketChannel.shutdownOutput();  
        
    	return receive();
    }
    public void Send(String msg) throws IOException{  
    		init(ip,port);
            //写数据
            ByteBuffer buffer=ByteBuffer.allocate(1024*1024);
            buffer.clear();
            buffer.put(msg.getBytes("ISO-8859-1"));
            buffer.flip();
            socketChannel.write(buffer);
            socketChannel.shutdownOutput();  
    }
    //发送对象
    public String SyscSend(Message msg) throws IOException{  	
    		init(ip,port);
    		String string = SerializeUtil.serialize(msg);
            //写数据
            ByteBuffer buffer=ByteBuffer.allocate(1024*1024);
            buffer.clear();
            buffer.put(string.getBytes("ISO-8859-1"));
            buffer.flip();
            socketChannel.write(buffer);
            socketChannel.shutdownOutput();  
    	return receive();
    }
    public void Send(Message msg) throws IOException{  	
    		init(ip,port);
    		String string = SerializeUtil.serialize(msg);
            //写数据
            ByteBuffer buffer=ByteBuffer.allocate(1024*1024);
            buffer.clear();
            buffer.put(string.getBytes("ISO-8859-1"));
            buffer.flip();
            socketChannel.write(buffer);
            socketChannel.shutdownOutput();  
    }
    public String receive() {
    	try {
//    	init(ip,port);
    	//读数据
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ByteBuffer buffer=ByteBuffer.allocate(1024);
        int len = 0;
        while (true) {
            buffer.clear();
            len = socketChannel.read(buffer);
            if (len == -1)
                break;
            buffer.flip();
            while (buffer.hasRemaining()) {
                bos.write(buffer.get());
            }
        }
        return new String(bos.toByteArray());
    	}catch(IOException e) {
    		System.out.println("Connection Refuse.");
    	}
		return null;
    }
}
