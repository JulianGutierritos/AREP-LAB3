package edu.eci.arep;

import java.net.*;
import java.util.Arrays;
import java.io.*;
 
public class ServidorHTTP {

    private ServerSocket serverSocket;

    private int port; 

    private boolean correr;

    private final String[] multimedia = {"png", "jpg", "mp3"};

    public ServidorHTTP (){
        this.port = getPort(); 
        this.correr = true; 
    }

    public ServidorHTTP (int port){
        this.port = port;
        this.correr = true;  
    }

    public void setCorrer(){
        if (correr){
            correr = false; 
        }
        else{
            correr = true;
        }
    }

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
                header = "HTTP/1.1 200 OK\r\n" + "Content-Type: image/jpg\r\n";
            }
            else if (extension.equals("png")){
                header = "HTTP/1.1 200 OK\r\n" + "Content-Type: image/png\r\n";
            }
            else if (extension.equals("js")){
                header = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/javascript\r\n" + "\r\n";
            }
        }
        return header;
    }

    private String getExtension(String path){
        String[] part = path.split("\\.");
        String extension = "";
        if (part.length > 1){
            extension = part[part.length - 1];
        }
        return extension;
    }

    static int getPort() {
       if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 36000; //returns default port if heroku-port isn't set (i.e. on localhost)
    }
}