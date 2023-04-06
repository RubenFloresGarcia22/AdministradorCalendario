package com.administradorcalendario.www.participantes;



public class TipoParticipante{
  
  public static final int ORGANIZADOR=1;
  public static final int INVITADO=2;
  private int id = 0;
  private String nombre = null;
  
  public TipoParticipante(){
  }
  
  public TipoParticipante(int id, String nombre){
    this.id = id;
    this.nombre = nombre;
  }

  public int getId(){
    return id;
  }
  
  public void setId(int id){
    this.id = id;
  }
  
  public String getNombre(){
    return nombre;
  }
  
  public void setNombre(String nombre){
    this.nombre = nombre;
  }
  
}