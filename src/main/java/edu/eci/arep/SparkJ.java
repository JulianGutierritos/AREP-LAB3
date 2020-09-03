package edu.eci.arep;

import java.util.List;
import edu.eci.arep.modelo.Usuario;
import edu.eci.arep.service.UsuarioService;
import edu.eci.arep.service.impl.UsuarioServiceMyBatis;
import java.io.*;
import java.net.*;

public class SparkJ{

    static UsuarioService usuarioService = new UsuarioServiceMyBatis();


    public static String getFile(String uri) throws IOException {
        URL url = new URL(uri);
        String path = url.getPath();
        String out = "";
        String primeraP = "";
        String [] part = url.getPath().split("\\/");
        if (part.length > 1){
            primeraP = part[1];
        }
        if (!(primeraP.equals("App"))){
            File archivo = null;
            FileReader fr = null;
            BufferedReader br = null;
            try {
                archivo = new File ("src/main/resources/static" + path);
                fr = new FileReader (archivo);
                br = new BufferedReader(fr);
                String linea;
                while((linea=br.readLine())!=null){
                    out+= linea;
                }
                fr.close();
            }
            catch(Exception e){
                throw new IOException();
            }              
        }
        else {
            if (part[2].equals("getUsuarios")){
                List<Usuario> usuarios = usuarioService.getUsuarios();
                String s = "";
                for (int i = 0; i < usuarios.size(); i++){
                    s += "{ ";
                    s += usuarios.get(i).toString();
                    s += " }";
                    if (i < usuarios.size() - 1){
                        s += ", ";
                    }
                }
                out = " { \"Lista\": [ " + s +" ] }";           
            }
            else if ((part[2].equals("getUsuario")) && (url.getQuery() != null)){
                String query[] = url.getQuery().split("&");
                String val[];
                String id = ""; 
                for (int i = 0; i < query.length; i++){
                    val = query[i].split("=");
                    if ((val.length == 2) && (val[0].equals("id"))){
                        id = val[1];
                    }
                }
                try {
                    String s = usuarioService.getUsuario(id).toString();
                    out = " { " + s +" }";  

                } catch (Exception e){
                    throw new IOException();
                }
            }            
            else{
                throw new IOException();
            }
        }
        return out;
    }

    public static byte[] getMultimedia(String uri) throws IOException {
        URL url = new URL(uri);
        File archivo = new File ("src/main/resources/static" + url.getPath());
        byte[] out = new byte[(int)archivo.length()];
        FileInputStream fs = new FileInputStream(archivo);
        fs.read(out);
        fs.close();
        return out;
    }

    public static String notFound(){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String out = "";
        try {
            archivo = new File ("src/main/resources/static/404.html");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);
            String linea;
            while((linea=br.readLine())!=null){
                out+= linea;
            }
            fr.close();
        }catch(Exception e){} 
        out = "HTTP/1.1 404 Not Found\r\n" + "Content-Type: text/html\r\n" + "\r\n" + out;
        return out;
    }
}