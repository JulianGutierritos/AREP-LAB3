package edu.eci.arep;

import java.net.*;
import java.util.Arrays;
import java.io.*;

/**
 * Servidor HTTP cuya funcion es recibir, leer y retornar mensajes con el protocolo HTTP o HTTPS 
 * @author Julián Gutiérrez
 * @version 1.0
 */
public class ServidorHTTP {

    private ServerSocket serverSocket;

    private int port; 

    private boolean correr;

    private final String[] multimedia = {"png", "jpg", "mp3"};

    /**
     * Constructor del servidor HTTP
     */
    public ServidorHTTP (){
        this.port = getPort(); 
        this.correr = true; 
    }

    /**
     * Constructor del servidor HTTP dado un puerto donde arrancar
     * @param port int por el cual va a correr el servidor
     */
    public ServidorHTTP (int port){
        this.port = port;
        this.correr = true;  
    }

    /**
     * Funcion que sirve para parar el servidor o reanudarlo 
     */
    public void setCorrer(){
        if (correr){
            correr = false; 
        }
        else{
            correr = true;
        }
    }

    /**
     * Funcion que: abre un socket de servidor y uno de cliente, lee los datos que llegan, llama a funciones para
     * responder a las peticiones y, finalmente, envia las respuestas al cliente.
     * @throws IOException si un recurso no es encontrado o leido de manera corecta
     */
    public void ejecutar() throws IOException {
        while (correr) {
            if (serverSocket == null){
                try {
                    serverSocket = new ServerSocket(port);
                } catch (IOException e) {
                    System.err.println("Could not listen on port: " + port);
                    System.exit(1);
                }
            }
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            OutputStream out = clientSocket.getOutputStream();
            PrintWriter out2 = new PrintWriter(out, true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String url = "http://";
            String host = "";
            String path ="";
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                //System.out.println(inputLine);
                if (inputLine.contains("GET")){
                    String[] div = inputLine.split(" ");
					path = div[1];
                }
                if (inputLine.contains("Host:")){
                    String[] div2 = inputLine.split(" ");
                    host = div2[1] ;
                }
                if (!in.ready()) {
                    break;
                }
            }
            if(path.equals("/")){
                path = "/index.html";
            }
            String extension = getExtension(path);
            String header = getHeader(extension);  
            url+= host + path;
            if(Arrays.asList(multimedia).contains(extension)){
                try{
                    byte[] resp =  SparkJ.getMultimedia(url);
                    out2.println(header);
                    out.write(resp);
                } catch (IOException e){
                    out2.println(SparkJ.notFound());
                }
            }
            else {
                try{
                    String resp = SparkJ.getFile(url);
                    out2.println(header + resp);
                } catch (IOException e){
                    out2.println(SparkJ.notFound());
                }
            }
            in.close();
            out.close();
            out2.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    /**
     * Funcion que dada una extension de un archivo, retorna el encabezado para esa extension
     * @param extension String del archivo 
     * @return String con el encabezado 
     */
    public String getHeader(String extension){
        String header = "";
        if (extension.equals("")){
            header = "HTTP/1.1 200 OK\r\n" + "Content-Type: application/json\r\n" + "\r\n";
        }
        else{
            if (extension.equals("html")){
                header = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html\r\n" + "\r\n";
            }
            else if (extension.equals("css")){
                header = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/css\r\n" + "\r\n";
            }
            else if (extension.equals("jpg")){
                header = "HTTP/1.1 200 OK\r\n" + "Content-Type: image/jpg\r\n" + "\r";
            }
            else if (extension.equals("png")){
                header = "HTTP/1.1 200 OK\r\n" + "Content-Type: image/png\r\n" + "\r";
            }
            else if (extension.equals("js")){
                header = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/javascript\r\n" + "\r\n";
            }
        }
        return header;
    }

    /**
     * Funcion que, dada una ruta, retorna la extension del archivo
     * @param path: String de la ruta del archivo
     * @return String con la extension del archivo 
     */
    private String getExtension(String path){
        String[] part = path.split("\\.");
        String extension = "";
        if (part.length > 1){
            extension = part[part.length - 1];
        }
        return extension;
    }

    /**
     * Funcion para seleccionar un puerto, ya sea uno seleccionado por defecto o seleccionado por el sistema
     * @return imt puerto obtenido
     */
    static int getPort() {
       if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 36000; 
    }
}
