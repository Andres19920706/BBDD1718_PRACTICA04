/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbdd1718.practica04.rowMapper;

import bbdd1718.practica04.dtos.Arbitro;
import bbdd1718.practica04.dtos.Personas;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Andres
 */
public class mapperArbitro {
    

    public static Arbitro mapperArbitro(ResultSet rs) throws SQLException{

        int id = rs.getInt("idArbitro");
        String nombre = rs.getString("nombre");
        String email = rs.getString("email");
        String tfl = rs.getString("tfl");
        String tipo = rs.getString("tipo");
        Double salario = rs.getDouble("salario");

        //Instanciamos con los datos leidos
        Personas p = new Personas(id,nombre,email,tfl);
        Arbitro a = new Arbitro(p,tipo,salario);
        
        return a;
    }
}
