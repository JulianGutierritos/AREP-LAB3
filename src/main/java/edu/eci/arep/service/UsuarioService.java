package edu.eci.arep.service;

import java.util.List;

import edu.eci.arep.modelo.Usuario;

/**
 * Interfaz que define los metodos necesarios para recuperar los usuarios de algun almacenamiento externo 
 * @author Julián Gutiérrez
 * @version 1.0
 */

public interface UsuarioService {

    /**
     * Busca un usuario dado su documento 
     * @param documento del usuario a buscar
     * @return Usuario: usuario con el documento dado
     */
    public Usuario getUsuario(String documento);

    /**
     * Busca todos los usuarios de la base de datos
     * @return todos los usuarios registrados en la base de datos
     */
    public List<Usuario> getUsuarios();





    
}