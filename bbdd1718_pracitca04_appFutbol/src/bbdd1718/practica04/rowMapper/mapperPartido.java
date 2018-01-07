/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbdd1718.practica04.rowMapper;

import bbdd1718.practica04.dtos.Partido;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Andres
 */
public class mapperPartido {
  public static Partido mapperPartido(ResultSet rs) throws SQLException{
//idPartido		int  AUTO_INCREMENT PRIMARY KEY,
//	idArbitroA		int default null,
//    idArbitroB		int default null,
//    idEquipoA		int default null,
//    idEquipoB		int default null,
//    fecha			timestamp, #No puede tener por defecto null
//    golesA			int,
//    golseB			int,
//    ida				boolean NOT NULL,
//    idEstadio		int default null,

        int idPartido = rs.getInt("idPartido");
        int idArbitroA = rs.getInt("idArbitroA");
        int idArbitroB = rs.getInt("idArbitroB");
        int idEquipoA = rs.getInt("idEquipoA");
        int idEquipoB = rs.getInt("idEquipoB");
        String fecha = rs.getString("fecha");
        int golesA = rs.getInt("golesA");
        int golesB = rs.getInt("golesB");
        boolean ida = rs.getBoolean("ida");
        int idEstadio = rs.getInt("idEstadio");


        //Instanciamos con los datos leidos
        Partido p = new Partido(idPartido, golesA, golesB,  ida,
             fecha,  idEstadio,  idEquipoA,  idEquipoB, 
             idArbitroA,  idArbitroB);
        
        return p;
    }  
}
