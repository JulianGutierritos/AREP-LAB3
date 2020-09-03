package edu.eci.arep.myBatis;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import edu.eci.arep.modelo.Usuario;

@Repository
public interface UsuarioMapper {

    @Select("SELECT * FROM Usuario WHERE #{documento} = documento")
    Usuario getUsuario(String documento);

    @Select("SELECT * FROM Usuario")
    List<Usuario> getUsuarios();

    
}