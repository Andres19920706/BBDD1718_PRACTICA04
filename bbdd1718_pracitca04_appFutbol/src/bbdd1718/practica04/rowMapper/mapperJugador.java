/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbdd1718.practica04.rowMapper;

import bbdd1718.practica04.dtos.Jugador;
import bbdd1718.practica04.dtos.Personas;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Andres
 */
public class mapperJugador {
    
    public static Jugador mapperJugador(ResultSet rs) throws SQLException{
        
        int id = rs.getInt("id");
        String nombre = rs.getString("nombre");
        String email = rs.getString("email");
        String tfl = rs.getString("tfl");
        Double salario = rs.getDouble("salario");
        int num = rs.getInt("num");
        String posicion = rs.getString("posicion");
        boolean titular = rs.getBoolean("titular");
        int idEquipo = rs.getInt("idEquipo");
        
        //Instanciamos con los datos leidos
        Personas pe = new Personas(id,nombre,email,tfl);
        Jugador ju = new Jugador(pe,salario,num,posicion,titular);
        ju.setIdEquipo(idEquipo);
        return ju;
    }
    
    
}
