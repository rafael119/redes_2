/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliete;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class CEcoTCPB {

    public static void main(String[] args) {
        try {
            //Recibimos una cadena de caracteres ligada al teclado
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            //Solicitamos la dirección de dirección
            System.out.printf("Escribe la dirección del Servidor: ");
            //Se lee como cadena de carácteres 
            //String host = Integer.parseInt(bufferedReader.readLine());
            String host = "127.0.0.1";
            //Pedimos el número de puerto como entero
            System.out.printf("\n\nEscriba el puerto: ");
            //int pto = Integer.parseInt(bufferedReader.readLine());
            int pto = 1234;
            bufferedReader.close();
            //Creamos el socket y nos conectamos
            Socket cl = new Socket(host, pto);
            //Buffer de envio y recibo
            BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(cl.getInputStream()));
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(cl.getOutputStream()));

            //Nos conectamos
//            String mensaje = bufferedReader1.readLine();
//            System.out.println("Recibimos un mensaje desde el servidor");
//            System.out.println("Mensaje: " + mensaje);
//            bufferedReader1.close();
            
            String res =  "Es un saludo de cliente";
            printWriter.print(res);
            printWriter.flush();
            //Cerramos flujo y socket
            printWriter.close();
            
            cl.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
