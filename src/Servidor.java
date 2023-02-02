import java.io.*;
import java.net.*;
public class Servidor {
    public static void main(String[] args) {
        try{
            DatagramSocket socket = new DatagramSocket(42000);
            System.out.println("Creación del arra de bytes");
            System.out.println("A la espera de recibir datagrama");
            String mensaje;
            File fichero = new File("mensajes.txt");
            FileWriter fw = new FileWriter("mensajes.txt");
            fichero.createNewFile();
            while(true) {
                byte[] buffer = new byte[64];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                mensaje = new String(packet.getData()).trim();
                if (mensaje.equals("FIN")) break;
                fw.write(mensaje.substring(9) + " ");
                System.out.println(mensaje.substring(9).trim() + " ");
            }
            fw.close();
        } catch (SocketException e) {
            System.err.println("Error en la creación del socket");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error en la recepción del paquete");
            e.printStackTrace();
        }
    }
}
