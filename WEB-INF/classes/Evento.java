package com.administradorcalendario.www.eventos;

import java.lang.Object;
import java.lang.String;
import com.administradorcalendario.www.calendarios.Calendario;
import com.administradorcalendario.www.direcciones.Direccion;
import java.util.ArrayList;

public class Evento extends Object{
  
  private int id = 0;
  private String fecha = null;
  private String nombre = null;
  private String hora = null;
  private String descripcion = null;
  private ArrayList participantes = null;
  private Calendario calendario = null;
  private Direccion direccion = null;
  
  public Evento(){
  }
  
  public Evento(int id, String fecha, String nombre, String hora, String descripcion, ArrayList participantes, Direccion direccion){
    this.id = id;
    this.fecha = fecha;
    this.nombre = nombre;
    this.hora = hora;
    this.descripcion = descripcion;
    this.participantes = participantes;
    this.direccion = direccion;
  }
  
  public int getId(){
    return id;
  }
  
  public void setId(int id){
    this.id = id;
  }
  
  public String getFecha(){
    return fecha;
  }
  
  public void setFecha(String fecha){
    this.fecha = fecha;
  }
  
  public String getNombre(){
    return nombre;
  }
  
  public void setNombre(String nombre){
    this.nombre = nombre;
  }
  
  public String getHora(){
    return hora;
  }
  
  public void setHora(String hora){
    this.hora = hora;
  }
  
  public String getDescripcion(){
    return descripcion;
  }
  
  public void setDescripcion(String descripcion){
    this.descripcion = descripcion;
  }
  
  public ArrayList getParticipantes(){
    return participantes;
  }
  
  public void setParticipantes(ArrayList participantes){
    this.participantes = participantes;
  }
  
  public Calendario getCalendario(){
    return calendario;
  }
  
  public void setCalendario(Calendario calendario){
    this.calendario = calendario;
  }
  
  public Direccion getDireccion(){
    return direccion;
  }
  
  public void setDireccion(Direccion direccion){
    this.direccion = direccion;
  }
  
}