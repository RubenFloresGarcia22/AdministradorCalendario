package com.administradorcalendario.www.personas;

import java.lang.Object;
import java.lang.String;

public class Persona extends Object{
  
  private int id = 0;
  private String nombre = null;
  private String apellidoPaterno = null;
  private String apellidoMaterno = null;
  private int edad = 0;
  private String sexo = null;
  
  public Persona(){
  }
  
  public Persona(int id, String nombre, String apellidoPaterno, String apellidoMaterno, int edad, String sexo){
    this.id = id;
    this.nombre = nombre;
    this.apellidoPaterno = apellidoPaterno;
    this.apellidoMaterno = apellidoMaterno;
    this.edad = edad;
    this.sexo = sexo;
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
  
  public String getApellidoPaterno(){
    return apellidoPaterno;
  }
  
  public void setApellidoPaterno(String apellidoPaterno){
    this.apellidoPaterno = apellidoPaterno;
  }
  
  public String getApellidoMaterno(){
    return apellidoMaterno;
  }
  
  public void setApellidoMaterno(String apellidoMaterno){
    this.apellidoMaterno = apellidoMaterno;
  }
  
  public int getEdad(){
    return edad;
  }
  
  public void setEdad(int edad){
    this.edad = edad;
  }
  
  public String getSexo(){
    return sexo;
  }
  
  public void setSexo(String sexo){
    this.sexo = sexo;
  }
  
}