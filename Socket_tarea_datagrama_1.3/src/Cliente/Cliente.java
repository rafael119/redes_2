package Cliente;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
/**
 *
 * @author rafa
 */
public class Cliente {

    public static void main(String[] args) {
        try {
            int pto = 2000;
            InetAddress dst = InetAddress.getByName("127.0.0.1");
            DatagramSocket cl = new DatagramSocket();
            System.out.println("Cliente iniciado... escriba un mensaje:");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            //Recibimos la cadena a enviar
            String cadena = br.readLine();
            //Obtenemos el tamaño de la cadena
            int tamCadena = cadena.length();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);
            dos.writeInt(tamCadena);
            dos.flush();
            dos.writeUTF(cadena);
            dos.flush();
            byte[] b = baos.toByteArray();
            //Enviamos la cadena y su tamaño
            DatagramPacket p = new DatagramPacket(b, b.length, dst, pto);
            cl.send(p);
            
            int divide = tamCadena / 20;
            byte[] data = new byte[100];
            DatagramPacket r = new DatagramPacket(data, data.length);
            System.out.println("Recibiendo datos... ");
            System.out.println("Imprimiendo la cadena recibida por el servidor:");
            String cadReconstruida = "";
            String cad1;
            
            for (int i = 0; i <= divide; i++) {
                cl.receive(r);
                cad1 = new String(r.getData(), 0, r.getLength());
                System.out.println("Pedaso ["+i+"]: " + cad1);
                cadReconstruida += cad1;
            }
            System.out.println("Cadena reconstruida: " + cadReconstruida);
            cl.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
