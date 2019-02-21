/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente1;

//CMullticastB.java

import java.net.*;
import java.io.*;

public class CMulticastB{
	public static void main (String []args){
		InetAddress gpo= null;
		try{
			MulticastSocket cl = new MulticastSocket(9999);
			System.out.println("Cliente escuchando puerto"+cl.getLocalPort());
			cl.setReuseAddress(true);
			try{
				gpo=InetAddress.getByName("228.1.1.1");
			}catch(UnknownHostException u){
				System.err.println("Direccion erronea");
			}
			cl.joinGroup(gpo);
			System.out.println("Unido al grupo.");
			for(;;){
				DatagramPacket p = new DatagramPacket(new byte[10],10);
				cl.receive(p);
				String msj = new String(p.getData());
				System.out.println("datagrama recibido:"+msj);
				System.out.println("Servidor descubierto: "+p.getAddress()+":"+p.getPort());

			}
		}catch( Exception e){
			e.printStackTrace();
			
		}
	}
}