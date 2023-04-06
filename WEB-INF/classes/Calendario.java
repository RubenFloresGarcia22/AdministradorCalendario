package com.administradorcalendario.www.calendarios;

import java.lang.Object;
import java.lang.String;
import java.util.ArrayList;
import com.administradorcalendario.www.usuarios.Usuario;
import com.administradorcalendario.www.calendarios.basedatos.ConectorCalendarios;
import java.sql.SQLException;

public class Calendario extends Object{
  
  private int id = 0;
  private ArrayList eventos = new ArrayList();
  private String fechaCreacion = null;
  private String nombre = null;
  
  public Calendario(){
  }
  
  public Calendario(int id, ArrayList eventos, String fechaCreacion, String nombre){
    this.id = id;
    this.eventos = eventos;
    this.fechaCreacion = fechaCreacion;
    this.nombre = nombre;
  }
  
  public int getId(){
    return id;
  }
  
  public void setId(int id){
    this.id = id;
  }
  
  public ArrayList getEventos(){
    return eventos;
  }
  
  public void setEventos(ArrayList eventos){
    this.eventos = eventos;
  }
  
  public String getFechaCreacion(){
    return fechaCreacion;
  }
  
  public void setFechaCreacion(String fechaCreacion){
    this.fechaCreacion = fechaCreacion;
  }
  
  public String getNombre(){
    return nombre;
  }
  
  public void setNombre(String nombre){
    this.nombre = nombre;
  }
  
  public static Calendario getCalendario(Usuario usuario)throws SQLException{
    ConectorCalendarios conectorCalendarios = new ConectorCalendarios();
    Calendario calendario = conectorCalendarios.getCalendario(usuario);
    return calendario;
  }
  
}