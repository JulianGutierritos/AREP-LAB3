package edu.eci.arep.myBatis;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import edu.eci.arep.modelo.Usuario;

/**
 * Interfaz que permite el acceso a datos escritos en la base de datos y transformarlos en objetos
 */

@Repository
public interface UsuarioMapper {

    /**
     * Busca un usurio en la base de datos y lo retorna como objeto
     * @param documento: numero de documento del usuario 
     * @return Usuario: el usuario solicitado
     */

    @Select("SELECT * FROM Usuario WHERE #{documento} = documento")
    Usuario getUsuario(String documento);

    /**
     * Busca los usuarios en la base de datos y los retorna como objetos
     * @return lista de usuarios en la base de datos
     */
    @Select("SELECT * FROM Usuario")
    List<Usuario> getUsuarios();

    
}