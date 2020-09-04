package edu.eci.arep.service.impl;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import edu.eci.arep.config.AppConfig;
import edu.eci.arep.modelo.Usuario;
import edu.eci.arep.myBatis.UsuarioMapper;
import edu.eci.arep.service.UsuarioService;

/**
 * Clase que recupera datos de una base de datos
 * @author Julián Gutiérrez
 * @version 1.0
 */

@Service
public class UsuarioServiceMyBatis implements UsuarioService{

    UsuarioMapper usuarioMapper;

    /**
     * Constructor de la clase
     */
    public UsuarioServiceMyBatis (){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        this.usuarioMapper = context.getBean(UsuarioMapper.class);
        context.close();
    }

    /**
     * Busca un usuario dado su documento 
     * @param documento del usuario a buscar
     * @return Usuario: usuario con el documento dado
     */
    public Usuario getUsuario(String documento){
        return usuarioMapper.getUsuario(documento);
    }

    /**
     * Busca todos los usuarios de la base de datos
     * @return todos los usuarios registrados en la base de datos
     */
    public List<Usuario> getUsuarios(){
        return usuarioMapper.getUsuarios();
    }

}