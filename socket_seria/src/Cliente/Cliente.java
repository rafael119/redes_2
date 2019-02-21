package Cliente;

import Servidor.Usuario;
import java.io.*;
import java.net.*;

public class Cliente {

    public static void main(String[] args) {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        String host = "127.0.0.1";
        int port = 9999;
        try {
            Socket cl = new Socket(host, port);//si no pasa de esta linea no se realizo conexion
            System.out.println("Conexion establecida...");
            oos = new ObjectOutputStream(cl.getOutputStream());
            ois = new ObjectInputStream(cl.getInputStream());
            Usuario u = new Usuario("pepito", "perez", "hernandez", "1234", 20);
            System.out.println("Enviando objeto...");
            oos.writeObject(u);
            oos.flush();
            System.out.println("Preparado para recibir respuesta");
            Usuario u2 = (Usuario) ois.readObject();
            System.out.println("Objeto recivido.... Extrayendo info");
            System.out.println("nombre: " + u2.getNombre()
                    + "\nA paterno: " + u2.getApaterno()
                    + "\nA materno: " + u2.getAmaterno()
                    + "\nPassword:  " + u2.getPwd()
                    + "\nEdad: " + u2.getEdad());

        } catch (Exception e) {
            System.err.println(e);
        }

    }
}
