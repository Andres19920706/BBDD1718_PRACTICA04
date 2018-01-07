/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import static bbdd1718.practica04.rowMapper.mapperEstadio.mapperEstadio;
import static bbdd1718.practica04.rowMapper.mapperPartido.mapperPartido;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author Andres
 */
public class pedirDatos {
    public pedirDatos(){};
    
    /**
     * Datos del jugador
     * Captura los datos del jugador.
     * @return j: Jugador
     */
    public Jugador datosJugador(){
        Scanner reader = new Scanner(System.in);
        boolean resultado = false;
        String aux;
        //
        int id;
        String nombre;
        String tfl;
        String email;
        boolean titular = false;
        String posicion;
        Double salario;
        int idEquipo;
        int num;
        
        //Id del jugador
        do{
            System.out.print("Inserte el identificador del jugador (>0): ");
            aux = reader.next();
            try{
                id = Integer.parseInt(aux);
            }catch(Exception e){
                System.out.println("Error "+e);
                id = 0;
            }
        }while(id<=0);

        //Nombre del jugador
        System.out.print("Inserte el nombre del jugador: ");
        nombre = reader.next();

        //Telefono del jugador
        do{
            System.out.print("Inserte el telefono del jugador (+xxx...): ");
            tfl = reader.next();
        }while(!isTele(tfl));

        //Email del jugador
        do{
            System.out.print("Inserte el email del jugador (x@x.x): ");
            email = reader.next();
        }while(!isEmail(email));

        //Comprobamos si es titular el jugador o no.
        do{
            System.out.print("¿El jugador es titular? (y/n): ");
            aux = reader.next();
            if(aux.equalsIgnoreCase("y")){
                titular = true;
            }else{
                if(aux.equalsIgnoreCase("n")){
                    titular = false;
                }else{
                    aux = "x";
                }

            }
        }while(aux.equalsIgnoreCase("x"));

        //Posicion
        do{
            System.out.print("Posiciones diponibles: \n"+
                    "1 Portero. \n"+
                    "2 Defensa. \n"+
                    "3 Medio. \n"+
                    "4 Ataque. \n"+
                    "Indique la posicion del jugador: ");
            aux = reader.next();
            try{
                if(Integer.parseInt(aux)==1){
                    posicion = "Portero";
                }else{
                    if(Integer.parseInt(aux)==2){
                        posicion = "Defensa";
                    }else{
                        if(Integer.parseInt(aux)==3){
                            posicion = "Medio";
                        }else{
                            if(Integer.parseInt(aux)==4){
                                posicion = "Ataque";
                            }else{
                                posicion = "x";
                            }
                        }
                    }
                }
            }catch(Exception io){
                System.out.println("Error "+io);
                posicion = "x";
            }

        }while(posicion.equalsIgnoreCase("x"));

        //Salario
        do{
            System.out.print("Salario del jugador: ");
            aux = reader.next();
            try{
                salario = Double.parseDouble(aux);
            }catch(Exception io){
                System.out.println("Error "+io);
                salario = 0.0;
            }
        }while(salario<=0.0);

        //idEquipo
        do{
            System.out.print("Identificador del equipo del jugador (0 por defecto): ");
            aux = reader.next();
            try{
                idEquipo = Integer.parseInt(aux);
            }catch(Exception io){
                System.out.println("Error "+io);
                idEquipo = 0;
            }
        }while(idEquipo<0);

        //num
        do{
            System.out.print("Numero del jugador: ");
            aux = reader.next();
            try{
                num = Integer.parseInt(aux);
            }catch(Exception io){
                System.out.println("Error "+io);
                num = 0;
            }
        }while(num<=0);
        //Inicializamos
        //Instanciamos con los datos leidos
        Personas pe = new Personas(id,nombre,email,tfl);
        Jugador ju = new Jugador(pe,salario,num,posicion,titular);
        ju.setIdEquipo(idEquipo);
        
        return ju;
    }
    
    /**
     * datosEstadio
     * Captura los datos del Estadio
     * @return e:Estadio
     */
    public Estadio datosEstadio(){
        Scanner reader = new Scanner(System.in);
        boolean resultado = false;
        String aux;
        
        int idEstadio;
        int capacidad;
        String direccion;
        String ciudad;
        //idEstadio
        do{
            System.out.print("Inserte el id del estadio (0 por defecto): ");
            aux = reader.next();

            try{
                idEstadio = Integer.parseInt(aux);
            }catch(Exception io){
                System.out.println("Error: "+io);
                idEstadio= -1;
            }
        }while(idEstadio<0);
        
        //capacidad
        do{
            System.out.print("Inserte la capacidad del estadio (>100): ");
            aux = reader.next();

            try{
                capacidad = Integer.parseInt(aux);
            }catch(Exception io){
                System.out.println("Error: "+io);
                capacidad= -1;
            }
        }while(capacidad <= 100);
        
        //direccion

        System.out.print("Inserte la direccion del estadio: ");
        System.out.flush(); reader.nextLine(); //Limpiamos el buffer de entrad
        aux = reader.nextLine();
        direccion = aux;
    

        
        //ciudad

        System.out.print("Inserte la ciudad del estadio: ");
        System.out.flush(); //reader.nextLine(); //Limpiamos el buffer de entrad
        aux = reader.nextLine();
        ciudad = aux;
            
       
        
        //Instanciamos
        Estadio e = new Estadio(idEstadio,capacidad,direccion,ciudad);
        return e;
        
    }
    
    //
    public Equipo datosEquipo(){
        Scanner reader = new Scanner(System.in);
        boolean resultado = false;
        String aux;
        
        int idEquipo;
        int posicion;
        String nameEquipo;
        int idEstadio;
        //idEquipo
        do{
            System.out.print("Inserte el id del equipo (0 por defecto): ");
            aux = reader.next();

            try{
                idEquipo = Integer.parseInt(aux);
            }catch(Exception io){
                System.out.println("Error: "+io);
                idEquipo= -1;
            }
        }while(idEquipo<0);
        
        //posicion
        do{
            System.out.print("Inserte la posicon del equipo en la liga (0 por defecto): ");
            aux = reader.next();

            try{
                posicion = Integer.parseInt(aux);
            }catch(Exception io){
                System.out.println("Error: "+io);
                posicion= -1;
            }
        }while(posicion<0);
        
        //nameEquipo
        System.out.print("Inserte el nombre del equipo: ");
        System.out.flush(); reader.nextLine(); //Limpiamos el buffer de entrad
        aux = reader.nextLine();
        nameEquipo = aux;
        
        //idEstadio
        do{
            System.out.print("Inserte el id del Estadio que pertenece el equipo (0 por defecto): ");
            aux = reader.next();

            try{
                idEstadio = Integer.parseInt(aux);
            }catch(Exception io){
                System.out.println("Error: "+io);
                idEstadio= -1;
            }
        }while(idEstadio<0);
        
        
        //Instanciamos
        Equipo e = new Equipo(idEquipo, posicion, nameEquipo, idEstadio);
        return e;
    }
    
    /**
     * datosArbitro
     * Captura los datos del Arbitro
     * @return a: Arbitro
     */
    public Arbitro datosArbitro(){
        Scanner reader = new Scanner(System.in);
        String aux;
        
        int id = 0;
        String nombre;
        String email;
        String tfl;
        Double salario = 0.0;
        String tipo = "";
        
        //id
        do{
            System.out.print("Inserte id del arbitro (0 por defecto): ");
            aux = reader.next();
            try{
               id = Integer.parseInt(aux);
               if(id<0){
                   aux = "x";
               }
            }catch(Exception io){
                System.out.println("Error: "+io);
                aux = "x";
            }
        }while(aux.equalsIgnoreCase("x"));
        
        //nombre
        System.out.print("Inserte el nombre del arbitro: ");
        reader.nextLine(); //Limpiar el buffer de entrada
        nombre = reader.nextLine();
        
        //email;
        do{
           System.out.print("Inserte el email del arbitro: ");
           aux = reader.next(); 
           if(!isEmail(aux) ) System.out.print("Email incorrecto. \n ");
        }while(!isEmail(aux));
        email = aux;
        
        //tfl;
        do{
           System.out.print("Inserte el telefono del arbitro: ");
           aux = reader.next(); 
           if(!isTele(aux) ) System.out.print("Telefono incorrecto. \n ");
        }while(!isTele(aux));
        tfl = aux;
        
        //salario;
        do{
            System.out.print("Inserte salario del arbitro (>0): ");
            aux = reader.next();
            try{
               salario = Double.parseDouble(aux);
               if(salario<=0){
                   aux = "x";
               }
            }catch(Exception io){
                System.out.println("Error: "+io);
                aux = "x";
            }
        }while(aux.equalsIgnoreCase("x"));
        
        //tipo;
        do{
            System.out.print("Tipo de arbitro: \n"+
                    "1 Principal \n"+
                    "2 Asistente \n"+
                    "Indique el tipo arbitro (1 o 2): ");
            aux = reader.next();
            try{
                int pivote;
                pivote = Integer.parseInt(aux);
                if(pivote==1){
                   tipo = "Principal";
                }else{
                    if(pivote==2){
                        tipo = "Asistente";
                    }else{
                        aux ="x";
                    }
                }
            }catch(Exception io){
                System.out.println("Error: "+io);
                aux = "x";
            }
        }while(aux.equalsIgnoreCase("x"));
        
        //Instanciamos
        Personas p = new Personas(id,nombre,email,tfl);
        Arbitro a = new Arbitro(p,tipo,salario);
        
        return a;
    }
    
    /**
     * datosPartido
     * Captura los datos de un Partido
     * @return p: Paritdo
     */
    public Partido datosPartido() throws SQLException{
        Scanner reader = new Scanner(System.in);
        String aux;
                
                
        int idPartido = 0;
	int idArbitroA = 0;		
        int idArbitroB = 0;	
        int idEquipoA = 0;
        int idEquipoB = 0;
        String fecha = null;
        int golesA = 0;
        int golesB = 0;
        boolean ida = false;
        int idEstadio = 0;
        
        Partido p = null;
        HashMap<Integer,Equipo> lEquipos = new HashMap<Integer,Equipo>();
        HashMap<Integer,Arbitro> lArbitro = new HashMap<Integer,Arbitro>();
        HashMap<Integer,Estadio> lEstadio = new HashMap<Integer,Estadio>();
        //idPartido;
        do{
            System.out.print("Inserte el id del equipo (0 por defecto): ");
            aux = reader.next();
            try{
                idPartido = Integer.parseInt(aux);
                if(idPartido<0) aux = "x";
            }catch(Exception io){
                System.out.println("Error: "+io);
                aux = "x";
            }
        }while(aux.equalsIgnoreCase("x"));
        
        //idEquipoA;
        lEquipos = obtenerEquipos();
        if(lEquipos==null){
            p = null;
            idEquipoA=0;
            idEquipoB = 0;
            idArbitroA = 0;  
        }else{
            do{
                System.out.print("Lista de Equipos: \n");

                //lEquipos.forEach((k,v) -> System.out.println("Key: " + k + ": Value: " + v)); //Para versioans de Java >=8
                lEquipos.forEach((key,valor) -> 
                        System.out.println("idEquipo: " + valor.getIdEquipo()
                                +", nombre:"+ valor.getNameEquipo()));

                System.out.print("Inserte el id del equipo A: ");
                aux = reader.next();
                try{
                    int key;
                    key = Integer.parseInt(aux);
                    if(key<0){
                        aux = "x";
                    }else{
                        if(lEquipos.get(key)!=null){
                            idEquipoA = key;
                        }else{
                            aux = "x";
                        }
                    }
                }catch(Exception io){
                    System.out.println("Error: "+io);
                    aux = "x";
                }
            }while(aux.equalsIgnoreCase("x"));
            
            //idEquipoB;
            if(idEquipoA!=0){
                if(lEquipos.size()>=2){
                    do{
                        System.out.print("Lista de Equipos: \n");

                        //lEquipos.forEach((k,v) -> System.out.println("Key: " + k + ": Value: " + v)); //Para versioans de Java >=8
                        lEquipos.forEach((key,valor) -> 
                                System.out.println("idEquipo: " + valor.getIdEquipo()
                                        +", nombre:"+ valor.getNameEquipo()));

                        System.out.print("Inserte el id del equipo B: ");
                        aux = reader.next();
                        try{
                            int key;
                            key = Integer.parseInt(aux);
                            if(key<0){
                                aux = "x";
                            }else{
                                if(lEquipos.get(key)!=null && key!=idEquipoA){
                                    //Comprobamos que el id introducido no coincide con el idEquipoA
                                    idEquipoB = key;
                                }else{
                                    aux = "x";
                                }
                            }
                        }catch(Exception io){
                            System.out.println("Error: "+io);
                            aux = "x";
                        }
                    }while(aux.equalsIgnoreCase("x"));
                }else{//No hay mas de 1 equipo en el sistema, no puede haber partido
                    System.out.println("Se cancela el partido por falta de equipos.");
                    idEquipoA = 0;
                    p= null;
                }
            }
            
            //idArbitroA;
            lArbitro = obtenerArbitros();
            //continuara
            if(lArbitro==null){
                p=null;
                idEquipoA=0;
                idEquipoB = 0;
                idArbitroA = 0;  
            }else{
                if(idEquipoA!=0 && idEquipoB!=0){
                    do{
                        System.out.print("Lista de Arbitros: \n");

                        //lEquipos.forEach((k,v) -> System.out.println("Key: " + k + ": Value: " + v)); //Para versioans de Java >=8
                        lArbitro.forEach((key,valor) -> 
                                System.out.println("idArbitro: " + valor.id
                                        +", nombre:"+ valor.nombre+", tipo: "+valor.getTipo()));

                        System.out.print("Inserte el id del arbitro A: ");
                        aux = reader.next();
                        try{
                            int key;
                            key = Integer.parseInt(aux);
                            if(key<0){
                                aux = "x";
                            }else{
                                if(lArbitro.get(key)!=null){
                                    //Comprobamos que el id introducido no coincide con el idEquipoA
                                    idArbitroA = key;
                                    lArbitro.remove(key);//Lo quitamos de la lista.
                                }else{
                                    aux = "x";
                                }
                            }
                        }catch(Exception io){
                            System.out.println("Error: "+io);
                            aux = "x";
                        }
                    }while(aux.equalsIgnoreCase("x"));
                } 
            }
            
            //idArbitroB;
            if(idArbitroA!=0){
                if(lArbitro.size()>=2){//Hay mas de 1 arbitro en el sistema
                    do{
                        System.out.print("Lista de Arbitros: \n");

                        //lEquipos.forEach((k,v) -> System.out.println("Key: " + k + ": Value: " + v)); //Para versioans de Java >=8
                        lArbitro.forEach((key,valor) -> 
                                    System.out.println("idArbitro: " + valor.id
                                            +", nombre:"+ valor.nombre+", tipo: "+valor.getTipo()));

                        System.out.print("Inserte el id del arbitro B: ");
                        aux = reader.next();
                        try{
                            int key;
                            key = Integer.parseInt(aux);
                            if(key<0){
                                aux = "x";
                            }else{
                                if(lArbitro.get(key)!=null && key!=idArbitroA){
                                    //Comprobamos que el id introducido no coincide con el idEquipoA
                                    idArbitroB = key;
                                }else{
                                    aux = "x";
                                }
                            }
                        }catch(Exception io){
                            System.out.println("Error: "+io);
                            aux = "x";
                        }
                    }while(aux.equalsIgnoreCase("x"));
                }else{//No hay mas de dos Arbitros
                    System.out.println("Solo puede haber un arbrito. ");
                    idArbitroB = 0;
                }
            }
            //fecha;
            if(idArbitroA!=0 && idEquipoA!=0 && idEquipoB!=0){
                Long horaMilis = new Date().getTime();//Obtenemos la hora
                //Long sessionExpired = horaMilis + (Long) incremetoMilis;
                Timestamp fecha2 = new Timestamp(horaMilis);//Lo pasmaos al fotmarto "Timestamp"
                do{
                    System.out.print("¿Cuantos dias quedan para el partido?: ");
                    aux = reader.next();
                    try{
                        Long time =  Long.parseLong(aux);
                        time = time * 24 * 3600 *1000;
                        fecha2.setTime(fecha2.getTime()+time);
                        do{
                            System.out.println("La fecha del paritdo es: "+ 
                                    fecha2.toString().split(" ")[0]+
                                    ", ¿Correcto? (y/n)");
                            aux = reader.next();
                            if(aux.equalsIgnoreCase("y")){
                                fecha = fecha2.toString().split(" ")[0];
                            }else{
                                if(aux.equalsIgnoreCase("n")){ 
                                    aux="x";
                                }else{
                                    aux = "r";
                                }
                            }
                        }while(aux.equalsIgnoreCase("r"));
                    }catch(Exception io){
                        System.out.println("Error: "+io);
                        aux="x";
                    }
                    
                }while(aux.equalsIgnoreCase("x"));
                
            }
            
            //golesA;
            if(idEquipoA!=0 && idEquipoB!=0 && idArbitroA!=0){
                //
                do{
                    System.out.print("Inserter los goles del equipo A: ");
                    try{
                        golesA = reader.nextInt();
                    }catch(Exception io){
                        System.out.println("Error: "+io);
                        golesA=-1;
                    }

                }while(golesA<0);
                //
            }else{
                golesA = 0;
            }
            
            //golesB;
            if(idEquipoA!=0 && idEquipoB!=0 && idArbitroA!=0){
                //
                do{
                    System.out.print("Inserter los goles del equipo B: ");
                    try{
                        golesB = reader.nextInt();
                    }catch(Exception io){
                        System.out.println("Error: "+io);
                        golesB=-1;
                    }

                }while(golesB<0);
                //
            }else{
                golesB = 0;
            }
            //ida;
            if(idEquipoA!=0 && idEquipoB!=0 && idArbitroA!=0){
                do{
                    System.out.print("¿Paritdo de ida? (y/n): ");
                    try{
                        aux = reader.next();
                        if(aux.equalsIgnoreCase("y")){
                            ida = true;
                        }else{
                            if(aux.equalsIgnoreCase("n")){
                                ida = false;
                            }else{
                                aux = "x";
                            }
                        }
                    }catch(Exception io){
                        System.out.println("Error: "+io);
                        aux = "x";
                    }

                }while(aux.equalsIgnoreCase("x"));
            }
            
            //idEstadio;
            lEstadio = obtenerEstadios();
            if(lEstadio == null || idEquipoA == 0 || idEquipoB == 0){
                p= null;
                idEquipoA=0;
                idEquipoB = 0;
                idArbitroA = 0;    
            }else{
                //
                do{
                    System.out.print("Lista de Estadios: \n");

                    //lEquipos.forEach((k,v) -> System.out.println("Key: " + k + ": Value: " + v)); //Para versioans de Java >=8
                    lEstadio.forEach((key,valor) -> 
                                System.out.println("idEstadio: " + valor.getIdEstadio()
                                        +", ciudad:"+ valor.getCiudad()+", direccion: "+valor.getDireccion() ));

                    System.out.print("Inserte el id del estadio: ");
                    aux = reader.next();
                    try{
                        int key;
                        key = Integer.parseInt(aux);
                        if(key<0){
                            aux = "x";
                        }else{
                            if(lEstadio.get(key)!=null){
                                //Comprobamos que el id introducido no coincide con el idEquipoA
                                idEstadio = key;
                            }else{
                                aux = "x";
                            }
                        }
                    }catch(Exception io){
                        System.out.println("Error: "+io);
                        aux = "x";
                    }
                }while(aux.equalsIgnoreCase("x"));
                //
            }
            
        }
        
        if(idEquipoA==0 || idEquipoB==0 || idArbitroA==0 || fecha == "null"){
                p=null;
        }else{
            //Instanciamos
            p = new Partido(idPartido, golesA, golesB,  ida,
             fecha,  idEstadio,  idEquipoA,  idEquipoB, 
             idArbitroA,  idArbitroB);  
        }
        
        
        
        return p;
        
    }
    
    /**
     * pedirFecha
     * Pide los fecha de los partidos disponibles.
     * @return aux:String
     * @throws SQLException 
     */
    public String pedirFecha() throws SQLException{
        HashMap<Integer, Partido> p = obtenerPartidos();
        Scanner reader = new Scanner(System.in);
        String aux = null;
        if(p!=null){
            System.out.print("Los paritdos en el sistema son: ");
            for(Integer key : p.keySet()){
                Partido p2 = p.get(key);
                System.out.println("EquipoA: "+p2.getIdEquipoA()+" EquipoB: "+
                        p2.getIdEquipoB()+", fecha: "+p2.getFecha());
                
            }
            System.out.print("Inserte una fecha para ver los partidos de esa fecha: ");
            aux = reader.next();
        }
        
        return aux;
        
    }
    
    /**
     * pedirEquipo
     * Pide el id de los equipos disponibles.
     * @return id:int
     * @throws SQLException 
     */
    public int pedirEquipo() throws SQLException{
        HashMap<Integer, Equipo> e = obtenerEquipos();
        Scanner reader = new Scanner(System.in);
        int id=0;
        if(e!=null){
            do{
                System.out.print("Los equipos en el sistema son:\n ");
                for(Integer key : e.keySet()){
                    Equipo e2 = e.get(key);
                    System.out.println("id: "+e2.getIdEquipo()+" nombre:"+
                            e2.getNameEquipo()+", Posicion"+e2.getPosicion());

                }
                System.out.print("Inserte el id para ver los partidos de ese equipo: ");

                try{
                    id = reader.nextInt();
                    if(e.get(id)==null) id=-1;
                }catch(Exception io){
                    System.out.println("Error: "+io);
                    id = -1;
                }
                }while(id==-1);
        }
        
        return id;
        
    }
    

    /**
     * pedirEquipo
     * Pide el id de los equipos disponibles.
     * @return id:int
     * @throws SQLException 
     */
    public String pedirPose() throws SQLException{
        HashMap<Integer, Equipo> e = obtenerEquipos();
        Scanner reader = new Scanner(System.in);
        String aux = null;
        if(e!=null){
            do{
                System.out.print("Las posicion de los jugadores son:\n"
                        + "Portero \n"
                        + "Medio \n"
                        + "Defensa \n"
                        + "Ataque \n");
                
                System.out.print("Inserte la posicion deseada: ");
                aux = reader.next();
                if(!aux.equalsIgnoreCase("Portero") && !aux.equalsIgnoreCase("Medio") &&
                        !aux.equalsIgnoreCase("Defensa") && !aux.equalsIgnoreCase("Ataque")){
                    aux = "x";
                }
                
            }while(aux.equalsIgnoreCase("x"));
        }
        
        return aux;
        
    }
            
    //Métodos para validar
    
    //Metodo para validar el telefono
    public boolean isTele(String telefono){
        String o = "^\\+\\d{1,3}?[- .]?\\(?(?:\\d{2,3})\\)?[- .]?\\d\\d\\d[- .]?\\d\\d\\d\\d$";
        //Si queremos que el + sea opciona: "^\\+?\\d{1,3}?[- .]?\\(?(?:\\d{2,3})\\)?[- .]?\\d\\d\\d[- .]?\\d\\d\\d\\d$"
        if(Pattern.matches(o,telefono)){
            return true;
        }else{
            return false;
        }
    }
    //Metodo para validar el correo electronico
    public boolean isEmail(String email){
        String o = "^[a-zA-Z0-9]+[@]{1}+[a-zA-Z0-9]+[.]{1}+[a-zA-Z0-9]+$";
       
        if(Pattern.matches(o,email)){
            return true;
        }else{
            return false;
        }
    }
    
    
    /**
     * obtenerEquipos
     * Obtiene los equipos que hay en el sistema sin los jugadores
     * @return e:HashMap<Integer,Equipo>
     * @throws SQLException 
     */
     public HashMap<Integer,Equipo> obtenerEquipos() throws SQLException{
         HashMap<Integer,Equipo> lEquipos = new HashMap<Integer,Equipo>();
        String cadena= "SELECT * FROM appFutbol.EQUIPO;";
        System.out.println("Cadena: "+cadena);
        ResultSet rs = ConexionBD.getInstancia().ejecutarConsulta(cadena);
        if(rs.absolute(1)){//No hay más de una fila por lo que no hay jugadores
            rs.beforeFirst();//esta regresa el puntero al inicio para no perder el 1er dato
            while(rs.next()){
                Equipo e = mapperEquipo(rs);
                lEquipos.put(e.getIdEquipo(), e);
            }
        }else{
            System.out.print("No hay Equipos en la BBDD.\n");
            lEquipos = null;
        }
        return lEquipos;
     }
     
     /**
     * obtenerArbitros
     * Obtiene los arbitros que hay en el sistema.
     * @return a:HashMap<Integer,Arbitro>
     * @throws SQLException 
     */
     public HashMap<Integer,Arbitro> obtenerArbitros() throws SQLException{
         HashMap<Integer,Arbitro> lArbitros = new HashMap<Integer,Arbitro>();
        String cadena= "SELECT * FROM appFutbol.ARBITRO;";
        System.out.println("Cadena: "+cadena);
        ResultSet rs = ConexionBD.getInstancia().ejecutarConsulta(cadena);
        if(rs.absolute(1)){//No hay más de una fila por lo que no hay jugadores
            rs.beforeFirst();//esta regresa el puntero al inicio para no perder el 1er dato
            while(rs.next()){
                Arbitro a = mapperArbitro(rs);
                lArbitros.put(a.id, a);
            }
        }else{
            System.out.print("No hay Arbitros en la BBDD.\n");
            lArbitros = null;
        }
        return lArbitros;
     }
     
     /**
     * obtenerEstadios
     * Obtiene los arbitros que hay en el sistema.
     * @return a:HashMap<Integer,Arbitro>
     * @throws SQLException 
     */
     public HashMap<Integer,Estadio> obtenerEstadios() throws SQLException{
         HashMap<Integer,Estadio> lEstadio = new HashMap<Integer,Estadio>();
        String cadena= "SELECT * FROM appFutbol.ESTADIO;";
        System.out.println("Cadena: "+cadena);
        ResultSet rs = ConexionBD.getInstancia().ejecutarConsulta(cadena);
        if(rs.absolute(1)){//No hay más de una fila por lo que no hay jugadores
            rs.beforeFirst();//esta regresa el puntero al inicio para no perder el 1er dato
            while(rs.next()){
                Estadio e = mapperEstadio(rs);
                lEstadio.put(e.getIdEstadio(), e);
            }
        }else{
            System.out.print("No hay Estdios en la BBDD.\n");
            lEstadio = null;
        }
        return lEstadio;
     }
     
     /**
     * obtenerPartidos
     * Obtiene los partidos que hay en el sistema.
     * @return p:HashMap<Integer,Partido>
     * @throws SQLException 
     */
     public HashMap<Integer,Partido> obtenerPartidos() throws SQLException{
         HashMap<Integer,Partido> lPartido = new HashMap<Integer,Partido>();
        String cadena= "SELECT * FROM appFutbol.PARTIDO;";
        System.out.println("Cadena: "+cadena);
        ResultSet rs = ConexionBD.getInstancia().ejecutarConsulta(cadena);
        if(rs.absolute(1)){//No hay más de una fila por lo que no hay jugadores
            rs.beforeFirst();//esta regresa el puntero al inicio para no perder el 1er dato
            while(rs.next()){
                Partido p = mapperPartido(rs);
                lPartido.put(p.getIdPartido(), p);
            }
        }else{
            System.out.print("No hay Partidos en la BBDD.\n");
            lPartido = null;
        }
        return lPartido;
     }

}
