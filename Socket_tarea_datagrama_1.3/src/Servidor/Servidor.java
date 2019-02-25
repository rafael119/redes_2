package Servidor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
/**
 *
 * @author rafa
 */

public class Servidor {

    public static void main(String[] args) {

        try {
            DatagramSocket s = new DatagramSocket(2000);
            System.out.println("Servidor iniciado");
            byte[] data = new byte[2000];
            for (;;) {
                //Creamos un paquete de datagrama para guardar lo recibido
                DatagramPacket p = new DatagramPacket(data, data.length);
                s.receive(p);
                System.out.println("Datagrama recibido:" + p.getAddress() + ": " + p.getPort());

                //-Recibimos los datos del cliente
                DataInputStream dis = new DataInputStream(new ByteArrayInputStream(p.getData()));
                int tamCadena = dis.readInt();
                String cadena = dis.readUTF();
                System.out.println("\nCadena recibida: " + cadena + " tamanio: " + tamCadena);
                
                //Reenviaremos la cadena reconstruida en paquetes de 20
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                DataOutputStream dos = new DataOutputStream(baos);
                int divide = tamCadena / 20;
                String cadenaPartida = "";
                
                for (int i = 0; i <= divide; i++) {
                    if (i == divide) {
                        cadenaPartida = cadena.substring((i * 20), (tamCadena));
                    } else {
                        cadenaPartida = cadena.substring((i * 20), ((i + 1) * 20));
                    }
                    //Enviamos la cadena
                    dos.writeUTF(cadenaPartida);
                    dos.flush();
                    byte[] b = cadenaPartida.getBytes();
                    DatagramPacket r = new DatagramPacket(b, b.length, p.getAddress(), p.getPort());
                    s.send(r);
                    System.out.print("Enviado: " + new String(r.getData())+"\n");
                }
                System.out.println("Cadena enviada");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

