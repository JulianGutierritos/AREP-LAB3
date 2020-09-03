package edu.eci.arep.modelo;

public class Usuario {

    private String nombre; 
    private String apellido; 
    private String documento;
    private String telefono; 
    
    public Usuario(String nombre, String apellido, String documento, String telefono){
        this.nombre = nombre;
        this.apellido = apellido; 
        this.documento = documento; 
        this.telefono = telefono; 
    }

    public Usuario(){
    }

    public String getNombre(){
        return this.nombre;
    }

    public String getApellido(){
        return this.apellido;
    }

    public String getDocumento(){
        return this.documento;
    }

    public String getTelefono(){
        return this.telefono;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setTelefono(String telefono){
        this.telefono = telefono;
    }

    public void setApellido(String apellido){
        this.apellido = apellido;
    }
    
    public void setDocumento(String documento){
        this.documento = documento;
    }

    @Override
	public String toString() {
		return "\"Usuario\": {" + "\"Nombre\": \"" + this.nombre + "\" , \"Apellido\": \"" +  this.apellido + "\" , \"Documento\": \"" + this.documento + "\" , \"Telefono\": \"" + this.telefono +  "\" }";
	}

    
}