package bbdd1718.practica04.metodos;

import bbdd1718.practica04.conexionBD.ConexionBD;
import bbdd1718.practica04.dtos.Arbitro;
import bbdd1718.practica04.dtos.Equipo;
import bbdd1718.practica04.dtos.Estadio;
import bbdd1718.practica04.dtos.Jugador;
import bbdd1718.practica04.dtos.Partido;
import bbdd1718.practica04.dtos.Personas;
import static bbdd1718.practica04.rowMapper.mapperArbitro.mapperArbitro;
import static bbdd1718.practica04.rowMapper.mapperEquipo.mapperEquipo;
import bbdd1718.practica04.rowMapper.mapperEstadio;
import static bbdd1718.practica04.rowMapper.mapperEstadio.mapperEstadio;
import static bbdd1718.practica04.rowMapper.mapperJugador.mapperJugador;
import static bbdd1718.practica04.rowMapper.mapperPartido.mapperPartido;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andres
 */
public class AppFutbol {
    public pedirDatos pedir = new pedirDatos();
    public String cadena;
    //
    public AppFutbol(){};
    
    /**
     * menu
     * Método para mostrar el menu por pantalla y devolver una opcion
     * @return option:int
     */
    public int menu(){
        int option = 0;
        Scanner reader = new Scanner(System.in);
        while(option==0){
            System.out.print("01: Alta Equipo.\n" +
                "02: Baja Equipo.\n" +
                "03: Alta Jugador.\n" +
                "04: Baja Jugador.\n" +
                "05: Alta Arbitro.\n" +
                "06: Baja Arbitro.\n" +
                "07: Alta Estadio.\n" +
                "08: Alta Partido.\n" +
                "09: Baja Partido.\n" +
                "10: Mostrar datos del Sistema.\n" +
                "11: Listar informacion de los Partidos dada una fecha.\n" +
                "12: Listar los Partidos hechos por un Equipo.\n" +
                "13: Listar los Jugadores que hay en una posicion.\n" +
                "14: Listar los Jugadores de un Equipo y sus Posiciones.\n" +
                "15: Salir. \n"+
                "Selecciona una opcion: ");
            
            String aux = reader.next();
            try{
                option = Integer.parseInt(aux);
            }catch(Exception e){
                System.out.println("Error "+e);
                option = 0;
            }
        }
        return option;
    }
    
    //
    public void altaEquipo() throws SQLException{
        Equipo e = pedir.datosEquipo();
        boolean insertar = true; //Por defecto si se agrega
        
        //Comprobamos el id del equipo
        if(e.getIdEquipo() == 0){//Es el pordefecto (autoincremetno)
            //Comprobamos el id del estadio
            if(e.getIdEstadio()==0){//Por defecto, no conoce el estadio
                cadena = "INSERT INTO EQUIPO VALUES("+null+","+
                        e.getPosicion()+",'"+e.getNameEquipo()+"',"+null+");"; 
                System.out.println("Cadena: "+cadena);
                ConexionBD.getInstancia().ejecutar(cadena);
                insertar = true;
            }else{
                //Cononoce el id del estadio, comprobamos si existe.
                cadena = "SELECT * FROM ESTADIO WHERE idEstadio= "+e.getIdEstadio()+";";
                System.out.println("Cadena: "+cadena);
                ResultSet rs = ConexionBD.getInstancia().ejecutarConsulta(cadena);
                if(rs.absolute(1)){//Existe el estadio
                    cadena = "INSERT INTO EQUIPO VALUES("+null+","+
                        e.getPosicion()+",'"+e.getNameEquipo()+"',"+e.getIdEstadio()+");"; 
                    
                }else{//No existe el estadio
                    System.out.println("No existe el estadio, se pone a null.");
                    cadena = "INSERT INTO EQUIPO VALUES("+null+","+
                        e.getPosicion()+",'"+e.getNameEquipo()+"',"+null+");"; 
                    
                }
                //Agregamos el equipos
                System.out.println("Cadena: "+cadena);
                ConexionBD.getInstancia().ejecutar(cadena);
                insertar = true;
            }
        }else{//El id es !=0
            //Comprobamos si ya esta ocupado el id del equipo
            cadena = "SELECT * FROM EQUIPO WHERE idEquipo= "+e.getIdEquipo()+";";
            System.out.println("Cadena: "+cadena);
            ResultSet rs = ConexionBD.getInstancia().ejecutarConsulta(cadena);
            if(rs.absolute(1)){//Existe el estadio
                //Hay un equipo con ese identificador
                Scanner reader = new Scanner(System.in);
                String aux;
                do{
                    System.out.print("id ocupado, ¿reemplazar¿? (y/n)");
                    aux = reader.next();
                    if(aux.equalsIgnoreCase("y")){
                        //Elimamos y lo añadimos

                        cadena = "DELETE FROM EQUIPO WHERE idEquipo ="+e.getIdEquipo()+";";
                        System.out.println("Cadena: "+cadena);
                        ConexionBD.getInstancia().ejecutar(cadena);

                        //Comprobamos si existe el estadio
                        if(e.getIdEstadio()==0){//Por defecto, no conoce el estadio
                            cadena = "INSERT INTO EQUIPO VALUES("+e.getIdEquipo()+","+
                                    e.getPosicion()+",'"+e.getNameEquipo()+"',"+null+");"; 
                            System.out.println("Cadena: "+cadena);
                            ConexionBD.getInstancia().ejecutar(cadena);
                            insertar = true;
                        }else{
                            //Cononoce el id del estadio, comprobamos si existe.
                            cadena = "SELECT * FROM ESTADIO WHERE idEstadio= "+e.getIdEstadio()+";";
                            System.out.println("Cadena: "+cadena);
                            rs = ConexionBD.getInstancia().ejecutarConsulta(cadena);
                            if(rs.absolute(1)){//Existe el estadio
                                cadena = "INSERT INTO EQUIPO VALUES("+e.getIdEquipo()+","+
                                    e.getPosicion()+",'"+e.getNameEquipo()+"',"+e.getIdEstadio()+");"; 

                            }else{//No existe el estadio
                                System.out.println("No existe el estadio, se pone a null.");
                                cadena = "INSERT INTO EQUIPO VALUES("+e.getIdEquipo()+","+
                                    e.getPosicion()+",'"+e.getNameEquipo()+"',"+null+");"; 

                            }
                            //Agregamos el equipos
                            System.out.println("Cadena: "+cadena);
                            ConexionBD.getInstancia().ejecutar(cadena);
                            insertar = true;
                        }
                        //

                    }else{
                        insertar = false;
                        if(!aux.equalsIgnoreCase("n")){//no escribio n
                            aux="x";
                        }
                    }
                }while(aux.equalsIgnoreCase("x"));
                //


            }else{//id disponible
                //Comprobamos el id del estadio
                if(e.getIdEstadio()==0){//Por defecto, no conoce el estadio
                    cadena = "INSERT INTO EQUIPO VALUES("+null+","+
                            e.getPosicion()+",'"+e.getNameEquipo()+"',"+null+");"; 
                    System.out.println("Cadena: "+cadena);
                    ConexionBD.getInstancia().ejecutar(cadena);
                    insertar = true;
                }else{
                    //Cononoce el id del estadio, comprobamos si existe.
                    cadena = "SELECT * FROM ESTADIO WHERE idEstadio= "+e.getIdEstadio()+";";
                    System.out.println("Cadena: "+cadena);
                    rs = ConexionBD.getInstancia().ejecutarConsulta(cadena);
                    if(rs.absolute(1)){//Existe el estadio
                        cadena = "INSERT INTO EQUIPO VALUES("+null+","+
                            e.getPosicion()+",'"+e.getNameEquipo()+"',"+e.getIdEstadio()+");"; 

                    }else{//No existe el estadio
                        System.out.println("No existe el estadio, se pone a null.");
                        cadena = "INSERT INTO EQUIPO VALUES("+null+","+
                            e.getPosicion()+",'"+e.getNameEquipo()+"',"+null+");"; 

                    }
                    //Agregamos el equipos
                    System.out.println("Cadena: "+cadena);
                    ConexionBD.getInstancia().ejecutar(cadena);
                    insertar = true;
                }
            }
                        
        }
        
        if(insertar){
            //Agregamos los jugadores.
            System.out.println("Agregamos los jugadores.");
            //Obtenemos los jugadores del equipo
            ArrayList<Jugador> listaJugadores = new ArrayList<Jugador>();
            listaJugadores = agregarJugadores(e);
            if(listaJugadores.size()==0){
                System.out.println("No hay suficientes jugadores en el sistema");
            }else{
                //Dar de alta al equipo
                System.out.println("Añadiendo los jugadores al equipo al equipo");
                int idBBDD = 0;
                if(e.getIdEquipo()==0){//Si el id del equipo, lo obtenemos
                    cadena = "SELECT * FROM EQUIPO WHERE EQUIPO.nameEquipo = '"+e.getNameEquipo()+"' ; ";
                    System.out.println("Cadena: "+cadena);
                    ResultSet rs = ConexionBD.getInstancia().ejecutarConsulta(cadena);
                    if(rs.absolute(1)){
                        rs.beforeFirst();
                        Equipo e2 = mapperEquipo(rs);
                        idBBDD = e2.getIdEquipo();
                        System.out.println("idEquipoBBDD = "+idBBDD);
                    }
                }else{//Coincide con el de la BBDD
                    idBBDD = e.getIdEquipo();
                }
                //Añadimos los jugadores al equipo
                for(int i=0;i<listaJugadores.size();i++){
                    cadena = "UPDATE JUGADOR SET JUGADOR.idEquipo ="+
                            e.getIdEquipo()+" WHERE id = "+listaJugadores.get(i).id+";";
                    System.out.println("Cadena: "+cadena);
                    ConexionBD.getInstancia().ejecutar(cadena);
                }
                
            }
        }

    }
    /**
     * agregarJjugadores
     * Metodo para obtener los jugadores que se han de agregar al equipo
     * @param e:Equipo
     * @return listajA: ArrayList<Jugador>
     * @throws SQLException 
     */
    public  ArrayList<Jugador> agregarJugadores(Equipo e) throws SQLException{
        int salir = 0; //!-1 continuar, -1 salir
        ArrayList<Jugador> listajA = new ArrayList<Jugador>(); //Jugadores del equipo
        HashMap<Integer, Jugador> listajB = new HashMap<Integer, Jugador>();//Jugadores disponibles
        Scanner reader = new Scanner(System.in);
        String aux;
        
        //Obtenemos la lista de jugadores
        cadena= "SELECT * FROM appFutbol.JUGADOR;";
        System.out.println("Cadena: "+cadena);
        ResultSet rs = ConexionBD.getInstancia().ejecutarConsulta(cadena);
        if(rs.absolute(1)){
            
            System.out.println("Lista de jugadores disponibles: ");
            rs.beforeFirst();//Rretrocedemos el puntero al principio de la lista
            while(rs.next()){
                Jugador j = mapperJugador(rs);
                if(j.getIdEquipo()==e.getIdEquipo() && e.getIdEquipo()!=0){
                    listajA.add(j);
                }else{
                    if(j.getIdEquipo()== 0){ //No pertecene a ningun equipo (campo esta null)
                        System.out.println("id: "+j.id+", nombre: "+j.nombre+", posicion: "+j.getPosicion());
                        listajB.put(j.id,j);
                    }
                }
                
                
            }
            
            System.out.println("listajA= "+listajA.size());
            System.out.println("listajB= "+listajB.size());
            if(listajA.size() <4 && listajB.size() > 4){ //Si hay 4 o más jugadres disponibles, y en el equipo hay menos de 4
                while(listajA.size()<4 && salir != -1 ){
                    System.out.print("Indroduce el id del jugador (<=0 para salir): ");
                    aux = reader.next();
                    try{
                        int key;
                        key = Integer.parseInt(aux);
                        if(key<=0){
                            salir = -1; //Salimos
                            listajA.clear();
                            System.out.println("Descartando cambios.");
                        }else{
                            if(listajB.get(key)!=null){
                                listajA.add(listajB.get(key));
                                listajB.remove(key);//Eliminos el jugador seleccionado
                            }else{
                                System.out.println("Jugador no disponible en la lista");
                            }
                        }
                    }catch(Exception io){
                        System.out.print("Error: "+io);
                    }
                }
            }else{
                if(listajB.size() < 4 && listajA.size()!=4 ){
                    System.out.println("\nNo hay suficientes jugadores en el sistema.");
                    listajA.clear();
                }else{
                    if(listajA.size() == 4){
                        System.out.println("\nEquipo completo");

                    }
                }
            }
            
        }else{
            //Sin jugadores en el sistema
            listajA.clear();
            salir = -1;
        }
        
        //if(salir== 0) //añadir jugadores
        return listajA; 
    }
    
    //
    public void bajaEquipo() throws SQLException{
        Scanner reader = new Scanner(System.in);
        String aux;
        int id;
        do{
            System.out.println("Lista de equipos en el sistema:");
            listarEquipos();//
            System.out.print("Indique el id del equipo (<=0 salir): ");
            aux = reader.next();
            try{
                id= Integer.parseInt(aux);
                if(id<=0){
                    System.out.println("Adios.");
                }else{
                    //Comprobamos si esta en la BBDD el jugador
                    cadena = "SELECT * FROM EQUIPO WHERE idEquipo= "+id+";";
                    System.out.println("Cadena: "+cadena);
                    ResultSet rs = ConexionBD.getInstancia().ejecutarConsulta(cadena);
                    if(rs.absolute(1)){//Esta en la base de datos
                        cadena = "DELETE FROM EQUIPO WHERE idEquipo = "+id+";";
                        System.out.println("Cadena: "+cadena);
                        ConexionBD.getInstancia().ejecutar(cadena);
                    }else{//No esta en la BBDD
                        System.out.println("Equipo no disponible en la BBDD");
                        aux="x";
                    }
                }
                
            }catch(Exception io){
                System.out.println("Error: "+io);
                aux="x";
            }
        }while(aux.equalsIgnoreCase("x"));
    }
    //
    public void altaJugador() throws SQLException{
        Scanner reader = new Scanner(System.in); 
        Jugador j = pedir.datosJugador();
        boolean insertar = false;
//        //Prueba
//        cadena= "INSERT INTO JUGADOR VALUES (null,'Ramon','andres@appfutol.es','+34689565123',1500,2,'Portero',false,null);";
//        Personas pe = new Personas(3,"Andres","a@a.es","+345");
//        Jugador j = new Jugador(pe,1500.0,2,"Defensa",true);
//        j.setIdEquipo(2);
//        //
            
        //Comprobamos si el identificador esta ocupado por otra persona
        cadena= "SELECT * FROM appFutbol.JUGADOR WHERE id="+j.id+";";
        System.out.println("Cadena: "+cadena);
        ResultSet rs = ConexionBD.getInstancia().ejecutarConsulta(cadena);
        if(rs.absolute(1) ){//id ocupado, porqye hay más de una fila
            String aux;
            do{
            System.out.print("id ocupado, ¿reemplazar¿? (y/n)");
            aux = reader.next();
            if(aux.equalsIgnoreCase("y")){
                //Elimamos y lo añadimos
                cadena = "DELETE FROM JUGADOR WHERE id ="+j.id+";";
                System.out.println("Cadena: "+cadena);
                ConexionBD.getInstancia().ejecutar(cadena);
                insertar = true;
            }else{
                if(!aux.equalsIgnoreCase("n")){//no escribio n
                    aux="x";
                }
            }
            }while(aux.equalsIgnoreCase("x"));
            
        }else{//id disponible
            insertar = true;
        }
        
        if(insertar){
            //Comprobamos que el estadio existe
            cadena= "SELECT * FROM appFutbol.EQUIPO WHERE idEquipo="+j.getIdEquipo()+";";
            System.out.println("Cadena: "+cadena);
            rs = ConexionBD.getInstancia().ejecutarConsulta(cadena);
            if(!rs.absolute(1) ){//el estadio no existe, porque no hay más de una fila
                System.out.println("El equipo no existe, pongo el idEquipo a null");
                cadena = "INSERT INTO JUGADOR VALUES("+j.id+",'"+j.nombre+"','"+
                        j.getEmail()+"','"+j.getTlf()+"',"+j.getSalario()+","+
                        j.getNum()+",'"+j.getPosicion()+"',"+j.getTitular()+","+
                        null+");";
            }else{//el estadio existe
            
            cadena = "INSERT INTO JUGADOR VALUES("+j.id+",'"+j.nombre+"','"+
                        j.getEmail()+"','"+j.getTlf()+"',"+j.getSalario()+","+
                        j.getNum()+",'"+j.getPosicion()+"',"+j.getTitular()+","+
                        j.getIdEquipo()+");";
            }
            //Agregamos el jugador a la base de datos
            System.out.println("Cadena: "+cadena);
            ConexionBD.getInstancia().getInstancia().ejecutar(cadena);
        }
    }
    
    /**
     * bajaJugdor
     * Elimina un jugador si existe del sistema.
     * @throws SQLException 
     */
    public void bajaJugador() throws SQLException{
        Scanner reader = new Scanner(System.in); 
        String aux;
        int id;
        do{
            listarJugadores();
            System.out.print("Indique el id del jugador a eliminar: ");
            aux = reader.next();

            try{
                id = Integer.parseInt(aux);
                //Comprobamos si el identificador esta ocupado por otra persona
                cadena= "SELECT * FROM appFutbol.JUGADOR WHERE id="+id+";";
                System.out.println("Cadena: "+cadena);
                ResultSet rs = ConexionBD.getInstancia().ejecutarConsulta(cadena);
                if(rs.absolute(1) ){//El id existe
                    String cadena = "DELETE FROM JUGADOR WHERE id = '"+ id +"';";
                    ConexionBD.getInstancia().ejecutar(cadena);
                    System.out.print("Borrado con exito");
                }else{
                    System.out.print("El id seleccionado no existe");
                }
                
            }catch(Exception io){
                System.out.println("Error: "+io);
                aux ="x";
                
                
            }
        }while(aux.equalsIgnoreCase("x"));
         
    }
    
    public void altaEstadio() throws SQLException{
        Estadio e = pedir.datosEstadio();
        String cadena;
        boolean insertar = false;
        //Comprobamos si el id del estadio es 0.
        if(e.getIdEstadio()==0){//El id es 0, por lo tanto por defecto
            cadena= "INSERT INTO ESTADIO VALUES (null,"+e.getCapacidad()+",'"
                    +e.getDireccion()+"','"+e.getCiudad()+"');";
            insertar = true;
        }else{//El id no es cero
            //Comprobamos si esta ocupado
            cadena = "SELECT * FROM appFutbol.Estadio WHERE idEstadio= "+e.getIdEstadio()+";";
            System.out.println("Cadena: "+cadena);
            ResultSet rs = ConexionBD.getInstancia().ejecutarConsulta(cadena);
            if(rs.absolute(1) ){//id ocupado, porque hay más de una fila
                Scanner reader = new Scanner(System.in);
                String aux;
                do{
                    System.out.print("id ocupado, ¿reemplazar¿? (y/n)");
                    aux = reader.next();
                    if(aux.equalsIgnoreCase("y")){
                        //Elimamos y lo añadimos
                        cadena = "DELETE FROM ESTADIO WHERE idEstadio ="+e.getIdEstadio()+";";
                        System.out.println("Cadena: "+cadena);
                        ConexionBD.getInstancia().ejecutar(cadena);
                        
                        cadena= "INSERT INTO ESTADIO VALUES ("+e.getIdEstadio()
                                +","+e.getCapacidad()+",'"+e.getDireccion()
                                +"','"+e.getCiudad()+"');";
                        insertar = true;
                    }else{
                        if(!aux.equalsIgnoreCase("n")){//no escribio n
                            aux="x";
                        }
                    }
                }while(aux.equalsIgnoreCase("x"));
            }else{//id Disponible
                cadena= "INSERT INTO ESTADIO VALUES ("+e.getIdEstadio()
                                +","+e.getCapacidad()+",'"+e.getDireccion()
                                +"','"+e.getCiudad()+"');";
                insertar = true;
            }
        }
        
        if(insertar){
            System.out.println("Cadena: "+cadena);
            ConexionBD.getInstancia().getInstancia().ejecutar(cadena);
        }
        
    }
    
    public void altaArbitro() throws SQLException{
        Arbitro a = pedir.datosArbitro();
        
        if(a.id==0){//Id por defecto
            //
            cadena = "INSERT INTO ARBITRO VALUES("+null+",'"+a.nombre+"','"+
                       a.getEmail()+"','"+a.getTlf()+"','"+a.getTipo()+"',"+
                       a.getSalario()+");";
            System.out.println("Cadena: "+cadena);
            ConexionBD.getInstancia().ejecutar(cadena);
            //
        }else{//Comprobamos si el id esta ocupado
            //
            cadena= "SELECT * FROM appFutbol.ARBITRO WHERE idArbitro="+a.id+";";
            System.out.println("Cadena: "+cadena);
            ResultSet rs = ConexionBD.getInstancia().ejecutarConsulta(cadena);
            if(!rs.absolute(1) ){//el id del arbitro disponible
                
                cadena = "INSERT INTO ARBITRO VALUES("+a.id+",'"+a.nombre+"','"+
                       a.getEmail()+"','"+a.getTlf()+"','"+a.getTipo()+"',"+
                       a.getSalario()+");";
                System.out.println("Cadena: "+cadena);
                ConexionBD.getInstancia().ejecutar(cadena);
                
            }else{//el id del arbitro ocupado
                Scanner reader = new Scanner(System.in);
                String aux;
                do{
                    System.out.print("¿Remplazar arbitro? (y/n):");
                    aux = reader.next();
                    if(aux.equalsIgnoreCase("y")){
                        //Remplazamos arbitro.
                        cadena = "UPDATE ARBITRO SET ARBITRO.nombre = '"+
                                a.nombre+"', ARBITRO.email = '"+a.getEmail()+
                                "', ARBITRO.tfl = '"+a.getTlf()+
                                "', ARBITRO.tipo = '"+a.getTipo()+
                                "', ARBITRO.salario = "+a.getSalario()+
                                " WHERE ARBITRO.idArbitro = "+a.id+";";
                        System.out.println("Cadena: "+cadena);
                        ConexionBD.getInstancia().ejecutar(cadena);
                    }else{
                        if(!aux.equalsIgnoreCase("n")) aux="x";
                    }
                }while(aux.equalsIgnoreCase("x"));
            }
            //
            
        }
    }
    
    public void bajaArbitro() throws SQLException{
        Scanner reader = new Scanner(System.in);
        String aux;
        int id;
        do{
            System.out.println("Lista de arbitros en el sistema:");
            listarArbitros();//
            System.out.print("Indique el id del Arbitro (<=0 salir): ");
            aux = reader.next();
            try{
                id= Integer.parseInt(aux);
                if(id<=0){
                    System.out.println("Adios.");
                }else{
                    //Comprobamos si esta en la BBDD el jugador
                    cadena = "SELECT * FROM ARBITRO WHERE idArbitro= "+id+";";
                    System.out.println("Cadena: "+cadena);
                    ResultSet rs = ConexionBD.getInstancia().ejecutarConsulta(cadena);
                    if(rs.absolute(1)){//Esta en la base de datos
                        cadena = "DELETE FROM ARBITRO WHERE idArbitro ="+id+";";
                        System.out.println("Cadena: "+cadena);
                        ConexionBD.getInstancia().ejecutar(cadena);
                    }else{//No esta en la BBDD
                        System.out.println("Arbitro no disponible en la BBDD");
                        aux="x";
                    }
                }
                
            }catch(Exception io){
                System.out.println("Error: "+io);
                aux="x";
            }
        }while(aux.equalsIgnoreCase("x"));
        
    }
    
    //
    public void altaPartido() throws SQLException{
        Partido p = pedir.datosPartido();
        boolean insertar = false;
        if(p==null){
            System.out.println("No se puede insertar el partido");
        }else{
            System.out.println("Iinsertando partido.");
            //Comprobamos el idPartido
            if(p.getIdPartido()==0){//Id por defecto (auto-incremento
                if(p.getIdArbitroB()==0){
                    cadena = "INSERT INTO PARTIDO VALUES("+null+","+p.getIdArbitroA()+","+
                       null+","+p.getIdEquipoA()+","+p.getIdEquipoB()+",'"+
                       p.getFecha()+"',"+p.getGolesA()+","+p.getGolesB()+","+p.getIda()+
                        ","+p.getIdEstadio()+");";
                }else{
                    cadena = "INSERT INTO PARTIDO VALUES("+null+","+p.getIdArbitroA()+","+
                       p.getIdArbitroB()+","+p.getIdEquipoA()+","+p.getIdEquipoB()+",'"+
                       p.getFecha()+"',"+p.getGolesA()+","+p.getGolesB()+","+p.getIda()+
                        ","+p.getIdEstadio()+");";
                }
                System.out.println("Cadena: "+cadena);
                ConexionBD.getInstancia().ejecutar(cadena);
                insertar = false;
            }else{
                //Comprobamos si el id del partido esta ocupado o no
                //
                Scanner reader = new Scanner(System.in);
                cadena= "SELECT * FROM appFutbol.PARTIDO WHERE idPartido="+p.getIdPartido()+";";
                System.out.println("Cadena: "+cadena);
                ResultSet rs = ConexionBD.getInstancia().ejecutarConsulta(cadena);
                if(rs.absolute(1) ){//id ocupado, porqye hay más de una fila
                    String aux;
                    do{
                    System.out.print("id ocupado, ¿reemplazar¿? (y/n)");
                    aux = reader.next();
                    if(aux.equalsIgnoreCase("y")){
                        //Elimamos y lo añadimos
                        cadena = "DELETE FROM PARTIDO WHERE idPartido = "+p.getIdPartido()+";";
                        System.out.println("Cadena: "+cadena);
                        ConexionBD.getInstancia().ejecutar(cadena);
                        insertar = true;
                    }else{
                        if(!aux.equalsIgnoreCase("n")){//no escribio n
                            aux="x";
                        }
                    }
                    }while(aux.equalsIgnoreCase("x"));

                }else{//id disponible
                    insertar = true;
                }
            }
            System.out.println("EEEE: "+p.getIdEstadio());
            if(insertar){
                if(p.getIdArbitroB()==0){
                    cadena = "INSERT INTO PARTIDO VALUES("+p.getIdPartido()+","+p.getIdArbitroA()+","+
                       null+","+p.getIdEquipoA()+","+p.getIdEquipoB()+",'"+
                       p.getFecha()+"',"+p.getGolesA()+","+p.getGolesB()+","+p.getIda()+
                        ","+p.getIdEstadio()+");";
                }else{
                    cadena = "INSERT INTO PARTIDO VALUES("+p.getIdPartido()+","+p.getIdArbitroA()+","+
                       p.getIdArbitroB()+","+p.getIdEquipoA()+","+p.getIdEquipoB()+",'"+
                       p.getFecha()+"',"+p.getGolesA()+","+p.getGolesB()+","+p.getIda()+
                        ","+p.getIdEstadio()+");";
                }
                
                System.out.println("Cadena: "+cadena);
                ConexionBD.getInstancia().ejecutar(cadena);
            }
        }
    }
    
    //
    public void bajaPartido() throws SQLException{
        Scanner reader = new Scanner(System.in);
        String aux;
        int id;
        do{
            System.out.println("Lista de partidos en el sistema:");
            listarPartidos();//
            System.out.print("Indique el id del Partido (<=0 salir): ");
            aux = reader.next();
            try{
                id= Integer.parseInt(aux);
                if(id<=0){
                    System.out.println("Adios.");
                }else{
                    //Comprobamos si esta en la BBDD el jugador
                    cadena = "SELECT * FROM PARTIDO WHERE idPartido= "+id+";";
                    System.out.println("Cadena: "+cadena);
                    ResultSet rs = ConexionBD.getInstancia().ejecutarConsulta(cadena);
                    if(rs.absolute(1)){//Esta en la base de datos
                        cadena = "DELETE FROM PARTIDO WHERE idPartido ="+id+";";
                        System.out.println("Cadena: "+cadena);
                        ConexionBD.getInstancia().ejecutar(cadena);
                    }else{//No esta en la BBDD
                        System.out.println("Partido no disponible en la BBDD");
                        aux="x";
                    }
                }
                
            }catch(Exception io){
                System.out.println("Error: "+io);
                aux="x";
            }
        }while(aux.equalsIgnoreCase("x"));
    }
    
    public void partidoFecha() throws SQLException{
        String fecha = pedir.pedirFecha();
        if(fecha != null){
            cadena= "SELECT idEquipoA as EquipoA, idEquipoB as EquipoB, "
                    + "golesA, golesB FROM appFutbol.PARTIDO Where fecha='"+fecha+"';";
            System.out.println("Cadena: "+cadena);
            ResultSet rs = ConexionBD.getInstancia().ejecutarConsulta(cadena);
            if(rs.absolute(1)){//No hay más de una fila por lo que no hay jugadores
                rs.beforeFirst();//esta regresa el puntero al inicio para no perder el 1er dato

                while(rs.next()){
                    int idEquipoA = rs.getInt("EquipoA");
                    int idEquipoB = rs.getInt("EquipoB");
                    int golesA = rs.getInt("golesA");
                    int golesB = rs.getInt("golesB");
                    System.out.print("EquipoA: "+idEquipoA+" EquipoB: "+idEquipoB+
                            "golesA: "+golesA+" golesB: "+golesB+"\n");

                }
            }else{
                System.out.print("No hay Partidos en la BBDD para esa fecha.\n");
            }
        }
    }
    
    public void partidoEquipo() throws SQLException{
        int idEquipo = pedir.pedirEquipo();
        if(idEquipo != 0){
            cadena= "SELECT idEquipoA as EquipoA, idEquipoB as EquipoB, "
                    + "golesA, golesB, fecha FROM appFutbol.PARTIDO Where idEquipoA= "+idEquipo+" or idEquipoB= "+idEquipo+";";
            System.out.println("Cadena: "+cadena);
            ResultSet rs = ConexionBD.getInstancia().ejecutarConsulta(cadena);
            if(rs.absolute(1)){//No hay más de una fila por lo que no hay jugadores
                rs.beforeFirst();//esta regresa el puntero al inicio para no perder el 1er dato

                while(rs.next()){
                    int idEquipoA = rs.getInt("EquipoA");
                    int idEquipoB = rs.getInt("EquipoB");
                    int golesA = rs.getInt("golesA");
                    int golesB = rs.getInt("golesB");
                    String fecha = rs.getString("fecha");
                    System.out.print("EquipoA: "+idEquipoA+" EquipoB: "+idEquipoB+
                            " golesA: "+golesA+" golesB: "+golesB+" fecha: "+fecha+"\n");

                }
            }else{
                System.out.print("No hay Partidos en la BBDD para es equipo.\n");
            }
        }
    }
    
    public void jugadoresPose() throws SQLException{
        String pose = pedir.pedirPose();
        if(pose != null){
            cadena= "SELECT nombre, email,tfl as tlf, salario, num, titular, "
                    + "idEquipo as Equipo FROM appFutbol.JUGADOR "
                    + "Where posicion= '"+pose+"';";
            System.out.println("Cadena: "+cadena);
            ResultSet rs = ConexionBD.getInstancia().ejecutarConsulta(cadena);
            if(rs.absolute(1)){//No hay más de una fila por lo que no hay jugadores
                rs.beforeFirst();//esta regresa el puntero al inicio para no perder el 1er dato

                while(rs.next()){
                    String nombre = rs.getString("nombre");
                    String email = rs.getString("email");
                    String tlf = rs.getString("tlf");
                    Double salario = rs.getDouble("salario");
                    int num = rs.getInt("num");
                    boolean titular = rs.getBoolean("titular");
                    int equipo = rs.getInt("Equipo");
                    
                    System.out.print("Nombre: "+nombre+" Email: "+email+
                            " Tlf: "+tlf+" Salario: "+salario+" Numero: "+num+
                            " Titular: "+titular+" Equipo:"+equipo+"\n");

                }
            }else{
                System.out.print("No hay Jugadores en la BBDD para esa posicion.\n");
            }
        }
    }
    
    public void JugadoresEquipo() throws SQLException{
        Scanner reader = new Scanner(System.in);
        String aux;
        
        listarEquipos();
        System.out.print("Inserte el nombre del equipo: ");
        aux = reader.nextLine();
        System.out.println("aux 14"+aux);
        //
        cadena= "SELECT nombre, email,tfl as tlf, salario, num, titular, "
                + "posicion FROM appFutbol.JUGADOR "
                + "Where idEquipo = (SELECT idEquipo FROM EQUIPO WHERE nombre ='"+aux+"');";
        System.out.println("Cadena: "+cadena);
        ResultSet rs = ConexionBD.getInstancia().ejecutarConsulta(cadena);
        if(rs.absolute(1)){//No hay más de una fila por lo que no hay jugadores
            rs.beforeFirst();//esta regresa el puntero al inicio para no perder el 1er dato

            while(rs.next()){
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String tlf = rs.getString("tlf");
                Double salario = rs.getDouble("salario");
                int num = rs.getInt("num");
                boolean titular = rs.getBoolean("titular");
                String posicion = rs.getString("posicion");

                System.out.print("Nombre: "+nombre+" Email: "+email+
                        " Tlf: "+tlf+" Salario: "+salario+" Numero: "+num+
                        " Titular: "+titular+" posicion:"+posicion+"\n");

            }
        //
        }else{
            System.out.println("No hay jugadores para ese quipo. ");
        }
    }
    
    /**
     * mostrarDatos
     * Muestra los datos del Sistema
     * @throws SQLException 
     */
    public void mostrarDatos() throws SQLException{
        System.out.println("------------------------------------------");
        System.out.println("Los datos en el sistema son: ");
        System.out.println("Jugadores: ");
        listarJugadores();
        System.out.println("Equipos: ");
        listarEquipos();
        System.out.println("Estadios: ");
        listarEstadios();
        System.out.println("Arbitro: ");
        listarArbitros();
        System.out.println("Partidos: ");
        listarPartidos();
        System.out.println("------------------------------------------");
    }
    
    /**
     * listarJugadores
     * Muestra por pantalla los jugadores que hay en el sistema.
     * @throws SQLException 
     */
    public void listarJugadores() throws SQLException{
        
        cadena= "SELECT * FROM appFutbol.JUGADOR;";
        System.out.println("Cadena: "+cadena);
        ResultSet rs = ConexionBD.getInstancia().ejecutarConsulta(cadena);
        if(rs.absolute(1)){//No hay más de una fila por lo que no hay jugadores
            rs.beforeFirst();//esta regresa el puntero al inicio para no perder el 1er dato
            while(rs.next()){
                Jugador j = mapperJugador(rs);
                System.out.print("id: "+j.id+" nombre: "+j.nombre+"\n");
            }
        }else{
            System.out.print("No hay jugadores en la BBDD.\n");
        }
    }
    
    /**
     * listarEquipos
     * Lista los equipos que hay en el sistema sin los jugadores
     * @throws SQLException 
     */
     public void listarEquipos() throws SQLException{
        cadena= "SELECT * FROM appFutbol.EQUIPO;";
        System.out.println("Cadena: "+cadena);
        ResultSet rs = ConexionBD.getInstancia().ejecutarConsulta(cadena);
        if(rs.absolute(1)){//No hay más de una fila por lo que no hay jugadores
            rs.beforeFirst();//esta regresa el puntero al inicio para no perder el 1er dato
            while(rs.next()){
                Equipo e = mapperEquipo(rs);
                System.out.print("id: "+e.getIdEquipo()+" nombre: "+e.getNameEquipo()+"\n");
               
            }
        }else{
            System.out.print("No hay Equipos en la BBDD.\n");
        }
     }
     
     /**
     * listarEstadios
     * Lista los estadios que hay en el sistema
     * @throws SQLException 
     */
     public void listarEstadios() throws SQLException{
        cadena= "SELECT * FROM appFutbol.ESTADIO;";
         System.out.println("Cadena: "+cadena);
        ResultSet rs = ConexionBD.getInstancia().ejecutarConsulta(cadena);
        if(rs.absolute(1)){//No hay más de una fila por lo que no hay jugadores
            rs.beforeFirst();//esta regresa el puntero al inicio para no perder el 1er dato
            
            while(rs.next()){
                Estadio e = mapperEstadio(rs);
                System.out.print("id: "+e.getIdEstadio()+" nombre: "+e.getCiudad()+"\n");
               
            }
        }else{
            System.out.print("No hay Estadios en la BBDD.\n");
        }
     }

     /**
     * listarArbitros
     * Lista los arbitros que hay en el sistema
     * @throws SQLException 
     */
     public void listarArbitros() throws SQLException{
        cadena= "SELECT * FROM appFutbol.ARBITRO;";
         System.out.println("Cadena: "+cadena);
        ResultSet rs = ConexionBD.getInstancia().ejecutarConsulta(cadena);
        if(rs.absolute(1)){//No hay más de una fila por lo que no hay jugadores
            rs.beforeFirst();//esta regresa el puntero al inicio para no perder el 1er dato
            
            while(rs.next()){
                Arbitro a = mapperArbitro(rs);
                System.out.print("id: "+a.id+" nombre: "+a.nombre+"\n");
               
            }
        }else{
            System.out.print("No hay Estadios en la BBDD.\n");
        }
     }
     
     /**
     * listarPartidos
     * Lista los paritidos que hay en el sistema
     * @throws SQLException 
     */
     public void listarPartidos() throws SQLException{
        cadena= "SELECT * FROM appFutbol.PARTIDO;";
        System.out.println("Cadena: "+cadena);
        ResultSet rs = ConexionBD.getInstancia().ejecutarConsulta(cadena);
        if(rs.absolute(1)){//No hay más de una fila por lo que no hay jugadores
            rs.beforeFirst();//esta regresa el puntero al inicio para no perder el 1er dato
            
            while(rs.next()){
                Partido p = mapperPartido(rs);
                System.out.print("idPartido: "+p.getIdPartido()+" fecha: "+p.getFecha()+"\n");
               
            }
        }else{
            System.out.print("No hay Estadios en la BBDD.\n");
        }
     }
     
     
}
