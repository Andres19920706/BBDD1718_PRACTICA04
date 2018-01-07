/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbdd1718.practica04.dtos;



/**
 *
 * @author Andres
 */
public class Partido{
    
    //Atributos
    private int idPartido;
    private int golesA;
    private int golesB;
    private Boolean ida;  
    private String fecha;
    private int idEstadio;
    private int idEquipoA,idEquipoB;
    private int idArbitroA, idArbitroB;
    
    
    //Constructor
    //Constructor por defecto.
    public Partido(){
    
    }
    //Constructor con parámetros.
    public Partido(int idPartido,int golesA,int golesB, boolean ida,
            String fecha, int idEstadio, int idEquipoA, int idEquipoB, 
            int idArbitroA, int idArbitroB){
        this.idPartido = idPartido;
        this.golesA = golesA;
        this.golesB = golesB;
        this.ida = ida;
        this.fecha = fecha;
        this.idEstadio = idEstadio;
        this.fecha = fecha;
        this.idEquipoA = idEquipoA;
        this.idEquipoB = idEquipoB;
        this.idArbitroA = idArbitroA;
        this.idArbitroB = idArbitroB;
        
    }
    
    //Métodos
    //Métodos de acceso Getters & Setters

    public int getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }

    public int getGolesA() {
        return golesA;
    }

    public void setGolesA(int golesA) {
        this.golesA = golesA;
    }

    public int getGolesB() {
        return golesB;
    }

    public void setGolesB(int golesB) {
        this.golesB = golesB;
    }

    public Boolean getIda() {
        return ida;
    }

    public void setIda(Boolean ida) {
        this.ida = ida;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdEstadio() {
        return idEstadio;
    }

    public void setIdEstadio(int idEstadio) {
        this.idEstadio = idEstadio;
    }

    public int getIdEquipoA() {
        return idEquipoA;
    }

    public void setIdEquipoA(int idEquipoA) {
        this.idEquipoA = idEquipoA;
    }

    public int getIdEquipoB() {
        return idEquipoB;
    }

    public void setIdEquipoB(int idEquipoB) {
        this.idEquipoB = idEquipoB;
    }

    public int getIdArbitroA() {
        return idArbitroA;
    }

    public void setIdArbitroA(int idArbitroA) {
        this.idArbitroA = idArbitroA;
    }

    public int getIdArbitroB() {
        return idArbitroB;
    }

    public void setIdArbitroB(int idArbitroB) {
        this.idArbitroB = idArbitroB;
    }

    @Override
    public String toString() {
        return "Partido{" + "idPartido=" + idPartido + ", golesA=" + golesA + ", golesB=" + golesB + ", ida=" + ida + ", fecha=" + fecha + ", idEstadio=" + idEstadio + ", idEquipoA=" + idEquipoA + ", idEquipoB=" + idEquipoB + ", idArbitroA=" + idArbitroA + ", idArbitroB=" + idArbitroB + '}';
    }

    
}
