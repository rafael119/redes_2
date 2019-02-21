
package cliente;

import java.net.*;
import java.io.*;
import javax.swing.JFileChooser;
public class CArchTCPB {
    public static void main(String [] args ){
         try{
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             System.out.println("Escriba la direccion del Servidor: ");
             String host = br.readLine();
             System.out.println("\n\n Escribe el puerto:");
             int pto = Integer.parseInt(br.readLine());
             Socket cl = new Socket(host,pto);
             JFileChooser jf= new FileChooser();
             int r = jf.showOpenDialog(null);
             if(r== JFileChooser.APPROVE_OPTION){
                 File f = jf.getSelectedFile();
                 String archivo =f.getAbsolutePath();
                 String nombre = f.getName();
                 long tam =f.length();
                 DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
                 DataInputStream dis = new DataInputStream(new FileInputStream(archivo));
                 dos.writeUTF(nombre);
                 dos.flush();
                 dos.writeLong(tam);
                 dos.flush();
                 byte[] b= new byte[1024];
                 long enviados=0;
                 int porcentaje,n;
                 while(enviados<tam){
                     n=dis.read(b);
                     dos.write(,0,n);
                     dos.flush();
                 }
                 
                 
             }
         }
    }
    
}
