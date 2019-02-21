package Servidor;

import java.io.*;
import java.net.*;

public class SArchTCPB {

    public static void main(String[] args) {
        // TODO code application logic here
        //creamos el socket
        try {
            ServerSocket s = new ServerSocket(7000);
            //iniciamos el ciclo infinito
            String nombre;
            long tam;
            while (true) {
                //esperamos una conexion

                Socket cl = s.accept();
                System.out.println("Conexion establecida desde " + cl.getInetAddress() + ":" + cl.getPort());
                DataInputStream dis = new DataInputStream(cl.getInputStream());

                int numa = dis.readInt();
                System.out.println("Archivos recividos:" + numa);

                for (int conta = 0; conta < numa; conta++) {
                    nombre = dis.readUTF();
                    
                    System.out.print("Recibimos el archivo: " + nombre + "\n");
                    tam = dis.readLong();
                    File archivo = new File(nombre);
                    DataOutputStream dos = new DataOutputStream(new FileOutputStream(archivo));
                    byte[] b = new byte[1024];
                    long reccibidos = 0;
                    int n, porcentaje;
                    //seccion para recibir el archivo
                    while (reccibidos < tam) {
                        if ((reccibidos + b.length) > tam) {
                            n = dis.read(b, 0, ((int) (tam - reccibidos)));
                        } else {
                            n = dis.read(b);
                        }
                        dos.write(b, 0, n);
                        dos.flush();
                        reccibidos = reccibidos + n;
                        porcentaje = (int) (reccibidos * 100 / tam);
                        System.out.println("Recibido: " + porcentaje + "%\r");
                    }//while
                    System.out.println("\n\nArchivo recibido.\n");
                    dos.close();
                }
                dis.close();
                cl.close();
            }//for
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
