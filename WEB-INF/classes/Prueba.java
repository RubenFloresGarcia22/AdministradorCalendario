package com.pruebas.www;

import com.administradorcalendario.www.usuarios.Usuario;
import com.administradorcalendario.www.calendarios.Calendario;
import com.administradorcalendario.www.calendarios.basedatos.ConectorCalendarios;
import com.administradorcalendario.www.eventos.basedatos.ConectorEventos;
import com.administradorcalendario.www.eventos.Evento;
import com.administradorcalendario.www.direcciones.Direccion;
import com.administradorcalendario.www.participantes.Participante;
import com.administradorcalendario.www.participantes.TipoParticipante;


import java.util.ArrayList;

public class Prueba{
  
  public Prueba(){
  }
  
  public static void main(String[] args)throws Exception{
    
    ConectorEventos conector = new ConectorEventos();
    /*Evento evento = new Evento();
    evento.setFecha("26-Sep-2017");
    evento.setNombre("Junta Empresarial");
    evento.setHora("10:30am");
    evento.setDescripcion("Junta para ver las ganancias.");
    
    Direccion direccion = new Direccion();
    direccion.setNombreCalle("Herreria");
    direccion.setCodigoPostal(33265);
    direccion.setNombreColonia("La Rosa");
    direccion.setNombreCiudad("Saltillo");
    evento.setDireccion(direccion);
    
    ArrayList participantes = new ArrayList();
    Participante participante = null;
    TipoParticipante tipo = null;
    participante = new Participante();
    participante.setNombre("Melissa TXM");
    participante.setEmail("melissa.txm.com");
    participante.setEmpresa("TXM Cloud");
    tipo =  new TipoParticipante();
    tipo.setId(1);
    participante.setTipo(tipo);
    participantes.add(participante);
    
    participante = new Participante();
    participante.setNombre("Alejandra TXM");
    participante.setEmail("alejandra.txm.com");
    participante.setEmpresa("TXM Cloud");
    tipo =  new TipoParticipante();
    tipo.setId(1);
    participante.setTipo(tipo);
    participantes.add(participante);
    
    participante = new Participante();
    participante.setNombre("Ruben TXM");
    participante.setEmail("ruben.txm.com");
    participante.setEmpresa("TXM Cloud");
    tipo =  new TipoParticipante();
    tipo.setId(1);
    participante.setTipo(tipo);
    participantes.add(participante);
    
    evento.setParticipantes(participantes);
    
    Calendario calendario = new Calendario();
    calendario.setFechaCreacion("26-Sep-2017");
    calendario.setNombre("Calendario TXM");
    evento.setCalendario(calendario);
    
    conector.guardar(evento);
    
    System.out.println("Se guardo registro....");*/
    
    /*Evento evento = new Evento();
    evento.setId(10);
    boolean isEliminacionExitosa = conector.eliminar(evento);
    System.out.println((isEliminacionExitosa)?"Se elimino la informacion con exito":"Error al eliminar la informacion.");*/
    
    //Obtener toda la informacion del Evento.
    
    /*Evento evento = new Evento();
    evento.setId(3);
    evento = conector.getEvento(evento);
    System.out.println((evento != null)?"Si trae informacion.":"No trae informacion.");
    
    System.out.println(" -----------------------------------------------------------------------------------------------------------------");
    System.out.println("|                                                 Evento                                                          |");
    System.out.println(" -----------------------------------------------------------------------------------------------------------------");
    System.out.println("|  Id  |   Fecha   |       Nombre       |   Hora   |              Descripcion                  |   Id Calendario   |");
    System.out.println(" -----------------------------------------------------------------------------------------------------------------");
    System.out.println("|   "+evento.getId()+"  |   "+evento.getFecha()+"|    "+evento.getNombre()+"|   "+evento.getHora()+"  |    "+evento.getDescripcion()+"         |                 "+evento.getCalendario().getId()+" |");
    System.out.println(" -----------------------------------------------------------------------------------------------------------------");
    System.out.println();
    System.out.println(" ------------------------------------------------------------------------------------------------------------------------------");
    System.out.println("|                                                    Direccion                                                                |");
    System.out.println(" ------------------------------------------------------------------------------------------------------------------------------");
    System.out.println("|     Id Direccion       |       Nombre Calle      |     Codigo Postal     |           Colonia           |        Ciudad      |");
    System.out.println(" ------------------------------------------------------------------------------------------------------------------------------");
    System.out.println("|           "+evento.getDireccion().getId()+"            |     "+evento.getDireccion().getNombreCalle()+"             |         "+evento.getDireccion().getCodigoPostal()+"         |          "+evento.getDireccion().getNombreColonia()+"       |     "+evento.getDireccion().getNombreCiudad()+"       |");
    System.out.println(" ------------------------------------------------------------------------------------------------------------------------------");
    
    System.out.println();
    System.out.println(" ----------------------------------------------------------------------------------------------------------------------------------");
    System.out.println("|                                            Participantes del Evento: "+evento.getNombre()+"                                           |");
    System.out.println(" ----------------------------------------------------------------------------------------------------------------------------------");
    System.out.println("|          Id        |          Nombre         |         Email          |         Empresa         | Tipo Participante(Id Nombre)  |");
    System.out.println(" ----------------------------------------------------------------------------------------------------------------------------------");
    for(int e=0; e<evento.getParticipantes().size();e++){
      Participante participante = (Participante)evento.getParticipantes().get(e);
      System.out.println("|            "+participante.getId()+"       |           "+participante.getNombre()+"        | "+participante.getEmail()+"   |           "+participante.getEmpresa()+"           |         "+participante.getTipo().getId()+" "+participante.getTipo().getNombre()+"            |");
    }
    System.out.println(" ----------------------------------------------------------------------------------------------------------------------------------");*/
    
    //Actualizacion de toda la informacion del Evento.
    Evento evento = new Evento();
    evento.setId(29);
    evento.setFecha("11-10-2017");
    evento.setNombre("Junta General");
    evento.setHora("12:00 pm");
    evento.setDescripcion("Junta General de TXM");
    Calendario calendario = new Calendario();
    calendario.setId(1);
    calendario.setFechaCreacion("12:49 pm");
    calendario.setNombre("Calendario Laboral");
    evento.setCalendario(calendario);
    Direccion direccion = new Direccion();
    direccion.setId(29);
    direccion.setNombreCalle("Herreria");
    direccion.setCodigoPostal(22222);
    direccion.setNombreColonia("LA ROSA");
    direccion.setNombreCiudad("Saltillo.");
    evento.setDireccion(direccion);
    ArrayList participantes = new ArrayList();
    Participante participante = new Participante();
    participante.setNombre("Andres Gomez");
    participante.setEmail("aGomez@txmglobal.com");
    participante.setEmpresa("TXM");
    TipoParticipante tipo = new TipoParticipante();
    tipo.setId(2);
    participante.setTipo(tipo);
    participantes.add(participante);
    
    participante = new Participante();
    participante.setNombre("Melissa Arellano");
    participante.setEmail("mArellano@txmglobal.com");
    participante.setEmpresa("HEXAWERE");
    tipo = new TipoParticipante();
    tipo.setId(2);
    participante.setTipo(tipo);
    participantes.add(participante);
    
    participante = new Participante();
    participante.setNombre("Alejandra Luna");
    participante.setEmail("aLuna@txmglobal.com");
    participante.setEmpresa("Neoris");
    tipo = new TipoParticipante();
    tipo.setId(2);
    participante.setTipo(tipo);
    participantes.add(participante);
    
    evento.setParticipantes(participantes);
    System.out.println("Tamano participantes: "+evento.getParticipantes().size());
    conector.actualizar(evento);
    
    System.out.println("Se realizo modificacion de evento.");
  }
  
}