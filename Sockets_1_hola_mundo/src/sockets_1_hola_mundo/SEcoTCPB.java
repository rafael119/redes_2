/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets_1_hola_mundo;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SEcoTCPB {

    public static void main(String[] args) {
        //Los sockets siempre trabajan en un try-catch para las excepciones
        try {
            //Se crea el socket esperando a un cliente
            ServerSocket s = new ServerSocket(1234);
            System.out.println("Esperando cliente");
            //Iniciamos ciclo infinito
            for (;;) {
                //Bloqueo
                //En el momento que ya se conecto un socket se crea un Socket
                Socket cl = s.accept();
                System.out.println("Conexión establecida desde: " + cl.getInetAddress() + " : " + cl.getPort());
                String mensaje = "Hola mundo";
                //Nuestro buffer de salida lo ligamos a un OutputStreamWriter
                PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(cl.getOutputStream()));
                //Se envía el mensaje
                printWriter.print(mensaje);
                //Se limpia el flujo
                printWriter.flush();
                printWriter.close();
                cl.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/**
 * /
 * Se crea el ServerSocket Ciclo infinito Espera conexión Encía o recibe Cierra
 * la conexión /*
 */
