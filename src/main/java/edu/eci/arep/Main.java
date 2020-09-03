package edu.eci.arep;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ServidorHTTP servidorHTTP = new ServidorHTTP();
        servidorHTTP.ejecutar();
    }
    
}