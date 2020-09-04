package edu.eci.arep;

import java.io.IOException;

/**
 * Clase principal cuya tarea es arrancar un servidor HTTP 
 * @author Julián Gutiérrez
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) throws IOException {
        ServidorHTTP servidorHTTP = new ServidorHTTP();
        servidorHTTP.ejecutar();
    }
    
}