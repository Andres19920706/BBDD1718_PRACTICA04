/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbdd1718.practica04.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Andres
 */
public class Equipo implements Serializable  {
    //Atributos
    private int idEquipo; //Identificador numérico del equipo
    private int posicion; //Posicon en la que esta la liga
    private String nameEquipo; //Nombre del equipo
    private int idEstadio; //id del estadio del equipo
    private ArrayList<Jugador> ljuga; //Lista de jugadores
    
    //Constructores
    //Constructor por defecto
    public Equipo(){
        this.idEquipo = 0;
        this.posicion = 0;
        this.nameEquipo = "";
        this.idEstadio = 0;
        this.ljuga = new ArrayList<>();
        this.ljuga = null;
    }
    //Constructor con parametros
    public Equipo(int idEquipo, int posicion, String nameEquipo, int idEstadio, ArrayList<Jugador> ljuga){
        this.idEquipo = idEquipo;
        this.posicion = posicion;
        this.nameEquipo = nameEquipo;
        this.ljuga = new ArrayList<>();
        this.ljuga = ljuga;      
        this.idEstadio = idEstadio;
    }
    
    public Equipo(int idEquipo, int posicion, String nameEquipo, int idEstadio){
        this.idEquipo = idEquipo;
        this.posicion = posicion;
        this.nameEquipo = nameEquipo;
        this.ljuga = new ArrayList<>();
        this.ljuga = null;      
        this.idEstadio = idEstadio;
    }

    //Métodos
    //Métodos de acceso Getters & Setters
    //Métodos Setters
        public void setIdEquipo(int idEquipo) {    
        this.idEquipo = idEquipo;
    }
    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
    public void setNameEquipo(String nameEquipo) {
        this.nameEquipo = nameEquipo;
    }
    public void setIdEstadio(int idEstadio) {
        this.idEstadio = idEstadio;
    }
    public void setLjuga(ArrayList<Jugador> ljuga) {    
        this.ljuga = new ArrayList<>();
        this.ljuga = ljuga;
    }
    //Métodos Getters
    public int getIdEquipo() {
        return idEquipo;
    }
    public int getPosicion() {
        return posicion;
    }
    public String getNameEquipo() {
        return nameEquipo;
    }
    public int getIdEstadio() {
        return idEstadio;
    }
    public ArrayList<Jugador> getLjuga() {
        return ljuga;
    }
    
    //Métodos para dar de alta y de baja a jugadores.
    public boolean altaJugador (Jugador j){
        boolean pivo = true;
        try{
            this.ljuga.add(j);
        }catch(Exception io){
            JOptionPane.showMessageDialog(null,"Error, al insertar el jugador.");
            pivo=false;
        }
        
        return pivo;
   
    }
    public boolean bajaJugador (int id){
        
        boolean pivo = true;
        try{
            this.ljuga.remove(id); //Eliminaomos por el id del jugador.
        }catch(Exception io){
            JOptionPane.showMessageDialog(null,"Error, al eliminar el jugador.");
            pivo=false;
        }
        
        return pivo;
    }    
    
}
