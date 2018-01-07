/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbdd1718.practica04.rowMapper;

import bbdd1718.practica04.dtos.Estadio;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Andres
 */
public class mapperEstadio {
    public static Estadio mapperEstadio(ResultSet rs) throws SQLException{

        int id = rs.getInt("idEstadio");
        int capacidad = rs.getInt("capacidad");
        String direccion = rs.getString("direccion");
        String ciudad = rs.getString("ciudad");
        
        
     
        //Instanciamos con los datos leidos
        Estadio e = new Estadio(id,capacidad,direccion,ciudad);
        
        return e;
    }
}
