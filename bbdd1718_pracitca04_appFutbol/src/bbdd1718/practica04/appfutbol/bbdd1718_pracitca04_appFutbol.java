/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbdd1718.practica04.appfutbol;

import bbdd1718.practica04.conexionBD.ConexionBD;
import bbdd1718.practica04.metodos.AppFutbol;

import java.sql.ResultSet;
import java.sql.SQLException;



/**
 *
 * @author Andres
 */
public class bbdd1718_pracitca04_appFutbol {

    /**
     * @param args the command line arguments
     */
    ConexionBD conn;
    public static void main(String[] args) throws SQLException{
        //Mostramos la ventana principal del programa
        //Cargamos los datos
        AppFutbol aplicacion = new AppFutbol();
        int option = 0;
        while(option != 15){
            option = aplicacion.menu();
            
            switch(option){
                case 1:
                    //Dar de Alta a equipo
                    aplicacion.altaEquipo();
                    break;
                case 2:
                    //Dar de baja a equipo
                    aplicacion.bajaEquipo();
                    break;
                case 3:
                    //Dar de alta a jugador
                    aplicacion.altaJugador();
                    break;
                case 4:
                    //Dar de baja a jugador
                    aplicacion.bajaJugador();
                    break;
                case 5:
                    //Dar de alta a arbitro;
                    aplicacion.altaArbitro();
                    break;
                case 6:
                    //Dar de baja arbitro;
                    aplicacion.bajaArbitro();
                    break;
                case 7:
                    //Dar de alta a Estadio
                    aplicacion.altaEstadio();
                    break;
                case 8:
                    //Dar de alta Partido
                    aplicacion.altaPartido();
                    break;
                case 9:
                    //Dar de baja Partido
                    aplicacion.bajaPartido();
                    break;
                case 10:
                    //Listar los datos en el sistema
                    aplicacion.mostrarDatos();
                    break;
                case 11:
                    //Partido dados una fecha
                    aplicacion.partidoFecha();
                    break;
                case 12:
                    //Partidos realizado por un equipo
                    aplicacion.partidoEquipo();
                    break;
                case 13:
                    //Jugadores segun posicion
                    aplicacion.jugadoresPose();
                    break;
                case 14:
                    //Jugadores de un equipo
                    aplicacion.JugadoresEquipo();
                    break;
                case 15:
                    //Salir
                    System.out.println("Adios. ");
                    break;
                default:
                    System.out.println("Opcion no conocida");
                    break;
                    
            }
            
            //Pausa
            if(option != 15){
                System.out.print("\nPress Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
            }
        
        }
    }
    
}
