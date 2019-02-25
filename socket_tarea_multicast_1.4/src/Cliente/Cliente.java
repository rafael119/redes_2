package Cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.rmi.UnknownHostException;
/**
 *
 * @author rafa
 */
public class Cliente {

    public static void main(String[] args) {
        InetAddress gpo = null;

        try {
            MulticastSocket cl = new MulticastSocket(9999);
            System.out.println("Cliente escuchando puerto " + cl.getLocalPort());
            cl.setReuseAddress(true);

            gpo = InetAddress.getByName("228.1.1.1");
            cl.joinGroup(gpo);
            System.out.println("Unido al grupo");
            System.out.println("Escribe un mensaje:");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            //Recibimos la cadena a enviar
            String cadena = br.readLine();
            
            byte[] b = cadena.getBytes();

            for (;;) {
                DatagramPacket p = new DatagramPacket(new byte[100], 100);
                cl.receive(p);
                String msj = new String(p.getData());
                System.out.println("Datagrama recibido : " + msj);
                System.out.println("Servidor descubierto: " + p.getAddress() + ":" + p.getPort());

                DatagramPacket q = new DatagramPacket(b, b.length, gpo, 9999);
                cl.send(q);

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}