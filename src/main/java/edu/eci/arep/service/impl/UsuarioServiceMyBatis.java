package edu.eci.arep.service.impl;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import edu.eci.arep.config.AppConfig;
import edu.eci.arep.modelo.Usuario;
import edu.eci.arep.myBatis.UsuarioMapper;
import edu.eci.arep.service.UsuarioService;

@Service
public class UsuarioServiceMyBatis implements UsuarioService{

    UsuarioMapper usuarioMapper;

    public UsuarioServiceMyBatis (){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        this.usuarioMapper = context.getBean(UsuarioMapper.class);
    }

    public Usuario getUsuario(String documento){
        return usuarioMapper.getUsuario(documento);
    }

    public List<Usuario> getUsuarios(){
        return usuarioMapper.getUsuarios();
    }

}