package Servidor;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import jdk.management.resource.internal.inst.DatagramDispatcherRMHooks;

public class SEcoUDPB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //Creamos un socket de datagrama con el puerto 2000
            DatagramSocket ds = new DatagramSocket(2000);
            System.out.println("SERVIDOR INICIADO");
            for (;;) {
                DatagramPacket dp = new DatagramPacket(new byte[2000], 2000);
                ds.receive(dp);//Bloqueo
                System.out.println("Datagrama recibido desde: " + dp.getAddress() + ":" + dp.getPort());
                String msj = new String(dp.getData(), 0, dp.getLength());
                System.out.println("Con el mensaje: " + msj);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
