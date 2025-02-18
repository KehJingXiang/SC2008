import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Rfc865UdpClient {
    public static void main(String[] argv) throws SocketException, UnknownHostException {
    
    DatagramSocket socket = null;
	byte[] buffer = new byte[512];
    InetAddress address = InetAddress.getByName("10.96.189.98"); // Lab server
    //InetAddress address = InetAddress.getByName("10.96.184.228"); // PC IP address
    //InetAddress address = InetAddress.getByName("10.96.185.173"); // PC IP address
    //InetAddress address = InetAddress.getByName("10.96.177.226"); // PC IP address

    try {
        // 1. Open UDP socket
        socket = new DatagramSocket();
    } catch (SocketException e) {}
    
    try {
        // 2. Send UDP request to server
        String message = new String("Keh Jing Xiang, SCSE, 10.96.184.228");
        buffer = message.getBytes();
        DatagramPacket request = new DatagramPacket(buffer, buffer.length, address, 17); // QOTD port 17
        socket.send(request);
        
        // 3. Receive UDP Reply from Server
        buffer = new byte[65535];
        DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
        socket.receive(reply);
        String received = new String(reply.getData(), 0, reply.getLength());
        System.out.println("Quote of the Day");
        System.out.println(received);
        } catch (IOException e) {}
    }
}
