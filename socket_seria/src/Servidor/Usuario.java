
package Servidor;

import java.io.Serializable;

public class Usuario implements Serializable{
    String nombre;
    String apaterno;
    String amaterno;
    transient String pwd;//este no se envia
    int edad;

    public Usuario(String nombre, String apaterno, String amaterno, String pwd, int edad) {
        this.nombre = nombre;
        this.apaterno = apaterno;
        this.amaterno = amaterno;
        this.pwd = pwd;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAmaterno() {
        return amaterno;
    }

    public String getApaterno() {
        return apaterno;
    }

    public int getEdad() {
        return edad;
    }

    public String getPwd() {
        return pwd;
    }
    
 
    
}
