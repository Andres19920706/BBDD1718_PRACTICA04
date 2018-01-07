/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbdd1718.practica04.rowMapper;

import bbdd1718.practica04.dtos.Equipo;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Andres
 */
public class mapperEquipo {
    
    public static Equipo mapperEquipo(ResultSet rs) throws SQLException{

        int id = rs.getInt("idEquipo");
        String nombre = rs.getString("nombre");
        int posicion = rs.getInt("posicion");
        int idEstadio = rs.getInt("idEstadio");
     
        //Instanciamos con los datos leidos
        Equipo e = new Equipo(id,posicion,nombre,idEstadio);
        
        return e;
    }
}
