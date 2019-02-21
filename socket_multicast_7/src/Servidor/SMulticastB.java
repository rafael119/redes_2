/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.net.*;
import java.io.*;
/**
 *
 * @author rafa_
 */
public class SMulticastB {
    public static void main(String[] args) {
        InetAddress gpo=null;
        try{
            MulticastSocket s= new MulticastSocket(4876);
            s.setReuseAddress(true);
            s.setTimeToLive(1);
            String msj= "hola";
            byte[] b= msj.getBytes();
            gpo=InetAddress.getByName("228.1.1.1");
            s.joinGroup(gpo);
            for(;;){
                DatagramPacket p = new DatagramPacket(b,b.length,gpo ,9999);
                s.send(p);
                System.out.println("Mensaje"+msj+"con TTL="+s.getTimeToLive());
                try{
                    Thread.sleep(3000);
                }catch(InterruptedException ie){
                    ie.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
