package edu.eci.arep;

import org.junit.Test;
import java.io.IOException;
import java.util.List;

import junit.framework.Assert;
import edu.eci.arep.modelo.Usuario;
import edu.eci.arep.service.*;
import edu.eci.arep.service.impl.UsuarioServiceMyBatis;
/**
 * Clase de pruebas
 * @author Julian Gutierrez
 * @version 1.0
 */

public class ServidorHTTPTest{

    
    @Test
    public void obtenerUsuarios() throws IOException {
        UsuarioService usuarioService = new UsuarioServiceMyBatis();
        try{
            Usuario u = usuarioService.getUsuario("12345");
            Assert.assertEquals("Busqueda incorrecta", "12345", u.getDocumento());
            List<Usuario> us = usuarioService.getUsuarios();
            Assert.assertTrue("Busqueda incorrecta", us.size() > 0);
        }catch (Exception e){
            Assert.fail();
        }
    }

}