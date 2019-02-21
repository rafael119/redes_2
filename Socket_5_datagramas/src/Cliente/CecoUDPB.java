/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.net.*;
import java.io.*;

/**
 *
 * @author rafa_
 */
public class CecoUDPB {

    public static void main(String[] args) {
        try {
            DatagramSocket cl = new DatagramSocket();
            System.out.println("Cliente eniciado , escriba un mensaje: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String mensaje = br.readLine();
            byte[] b = mensaje.getBytes();
            String dst = "127.0.0.1";
            int pto = 2000;
            DatagramPacket p = new DatagramPacket(b, b.length,InetAddress.getByName(dst),pto);
            cl.send(p);
            cl.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
