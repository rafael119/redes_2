package Cliente;

import java.io.*;
import java.net.*;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author esierray1500
 */
public class CArchTCPB {

    public static void main(String[] arg) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Escribe la direccion del servidor: ");
            String host = br.readLine();
            System.out.printf("\n\nEscriba el puerto: ");
            int pto = Integer.parseInt(br.readLine());
            //creamos el socket y nos conectamos
            Socket cl = new Socket(host, pto);
            JFileChooser jf = new JFileChooser();
            jf.setMultiSelectionEnabled(true);
            int r = jf.showOpenDialog(null);
            if (r == JFileChooser.APPROVE_OPTION) {
                File[] f = jf.getSelectedFiles();
                DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
                dos.writeInt(f.length);
                for (int op = 0; op < f.length; op++) {
                    String archivo = f[op].getAbsolutePath();
                    String nombre = f[op].getName();
                    long tam = f[op].length();
                    DataInputStream dis = new DataInputStream(new FileInputStream(archivo));
                    dos.writeUTF(nombre);
                    dos.flush();
                    dos.writeLong(tam);
                    dos.flush();
                    byte []b = new byte[1024];
                    long enviados = 0;
                    int porcentaje, n;
                    //secccion para el envio del archivo
                    while (enviados < tam) {
                        n = dis.read(b);
                        dos.write(b, 0, n);
                        dos.flush();
                        enviados = enviados + n;
                        porcentaje = (int) (enviados * 100 / tam);
                        System.out.println("Enviado: " + porcentaje + ("%\r"));
                    }//while
                    System.out.println("\n\nArchivo enviado.");

                    dis.close();
                }
                dos.close();
                cl.close();
            }//if
        }//try
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
