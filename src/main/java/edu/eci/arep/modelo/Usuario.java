package edu.eci.arep.modelo;

/**
 * Usuario de la base de datos
 */

public class Usuario {

    private String nombre; 
    private String apellido; 
    private String documento;
    private String telefono; 
    
    /**
     * Constructor del usuario 
     * @param nombre del usuario
     * @param apellido del usuario
     * @param documento del usuario
     * @param telefono del usuario
     */
    public Usuario(String nombre, String apellido, String documento, String telefono){
        this.nombre = nombre;
        this.apellido = apellido; 
        this.documento = documento; 
        this.telefono = telefono; 
    }

    /**
     * Constructor del usuario
     */
    public Usuario(){
    }

    /**
     * Retorna el nombre del usuario
     * @return String nombre del usuario
     */
    public String getNombre(){
        return this.nombre;
    }

    /**
     * Retorna el apellido del usuario
     * @return String apellido del usuario
     */
    public String getApellido(){
        return this.apellido;
    }

    /**
     * Retorna el documento del usuario
     * @return String documento del usuario
     */
    public String getDocumento(){
        return this.documento;
    }

    /**
     * Retorna el telefono del usuario
     * @return String telefono del usuario
     */
    public String getTelefono(){
        return this.telefono;
    }

    /**
     * Cambia el nombre del usuario
     * @param nombre: nuevo nombre del usuario
     */
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    /**
     * Cambia el telefono del usuario
     * @param telefono: nuevo telefono del usuario
     */
    public void setTelefono(String telefono){
        this.telefono = telefono;
    }

    /**
     * Cambia el apellido del usuario
     * @param apellido: nuevo apellido del usuario
     */
    public void setApellido(String apellido){
        this.apellido = apellido;
    }
    
    /**
     * Cambia el documento del usuario
     * @param documento: nuevo documento del usuario
     */
    public void setDocumento(String documento){
        this.documento = documento;
    }

    /**
     * Define como el usuario va a ser transformado en cadena 
     * @return String con la representacion del usuario como cadena
     */
    @Override
	public String toString() {
		return "\"Usuario\": {" + "\"Nombre\": \"" + this.nombre + "\" , \"Apellido\": \"" +  this.apellido + "\" , \"Documento\": \"" + this.documento + "\" , \"Telefono\": \"" + this.telefono +  "\" }";
	}

    
}