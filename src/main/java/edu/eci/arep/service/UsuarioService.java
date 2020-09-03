package edu.eci.arep.service;

import java.util.List;

import edu.eci.arep.modelo.Usuario;

public interface UsuarioService {

    public Usuario getUsuario(String documento);

    public List<Usuario> getUsuarios();





    
}