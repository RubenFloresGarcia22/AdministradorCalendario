package com.administradorcalendario.www.empresas;

import java.lang.Object;
import java.lang.String;
import com.administradorcalendario.www.departamentos.Departamento;

public class Empresa extends Object{
  
  private int id = 0;
  private String nombre = null;
  private Departamento departamento = null;
  
  public Empresa(){
  }
  
  public Empresa(int id, String nombre, Departamento departamento){
    this.id = id;
    this.nombre = nombre;
    this.departamento = departamento;
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
  
  public Departamento getDepartamento(){
    return departamento;
  }
  
  public void setDepartamento(Departamento departamento){
    this.departamento = departamento;
  }
  
}