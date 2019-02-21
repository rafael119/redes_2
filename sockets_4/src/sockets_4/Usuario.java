package sockets_4;

import java.io.*;
import java.util.LinkedList;
import java.util.ListIterator;

public class Usuario {

    private String usuario;
    private String password;

    public Usuario() {
        System.out.println("Creando usuario");

    }

    Usuario(String u, String p) {
        System.out.println("Creando usuario (" + u + ", " + p + ")");
        usuario = u;
        password = p;
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        //explicitamente indicamos cuales atributos se van a enviar
        out.writeObject(usuario);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Usuario readExternal");
        usuario = (String) in.readObject();

    }

    public void muestraUsuario() {
        String cod = "Usuario " + usuario + " Password: ";
        if (password == null) {
            cod = cod + "No disponible";
        } else {
            cod = cod + password;
        }
        System.out.println(cod);

    }
}

class ListaUsuarios implements Serializable {

    private LinkedList lista = new LinkedList();
    int valor;

    ListaUsuarios(String[] usuarios, String[] passwords) {
        for (int i = 0; i < usuarios.length; i++) {
            lista.add(new Usuario(usuarios[i], passwords[i]));
        }
    }

    public void muestraUsuarios() {
        ListIterator li = lista.listIterator();
        Usuario u;
        while (li.hasNext()) {
            u = (Usuario) li.next();
            u.muestraUsuario();
        }
    }
}
