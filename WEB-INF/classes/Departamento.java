package com.administradorcalendario.www.departamentos;

import java.lang.Object;
import java.lang.String;

public class Departamento extends Object{
  
  private String nombre = null;
  
  public Departamento(){
  }
  
  public Departamento(String nombre){
    this.nombre = nombre;
  }
  
  public String getNombre(){
    return nombre;
  }
  
  public void setNombre(String nombre){
    this.nombre = nombre;
  }
  
}