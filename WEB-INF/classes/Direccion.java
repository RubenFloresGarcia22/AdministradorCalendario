package com.administradorcalendario.www.direcciones;

import java.lang.Object;
import java.lang.String;

public class Direccion extends Object{
  
  private int id = 0;
  private String nombreCalle = null;
  private int codigoPostal = 0;
  private String nombreColonia = null;
  private String nombreCiudad = null;
  
  public Direccion(){
  }
  
  public Direccion(int id, String nombreCalle, int codigoPostal, String nombreColonia, String nombreCiudad){
    this.id = id;
    this.nombreCalle = nombreCalle;
    this.codigoPostal = codigoPostal;
    this.nombreColonia = nombreColonia;
    this.nombreCiudad = nombreCiudad;
  }
  
  public int getId(){
    return id;
  }
  
  public void setId(int id){
    this.id = id;
  }
  
  public String getNombreCalle(){
    return nombreCalle;
  }
  
  public void setNombreCalle(String nombreCalle){
    this.nombreCalle = nombreCalle;
  }
    
  public int getCodigoPostal(){
    return codigoPostal;
  }
  
  public void setCodigoPostal(int codigoPostal){
    this.codigoPostal = codigoPostal;
  }
  
  public String getNombreColonia(){
    return nombreColonia;
  }
  
  public void setNombreColonia(String nombreColonia){
    this.nombreColonia = nombreColonia;
  }
  
  public String getNombreCiudad(){
    return nombreCiudad;
  }
  
  public void setNombreCiudad(String nombreCiudad){
    this.nombreCiudad = nombreCiudad;
  }
  
  public String toString(){
    return("Calle: "+this.nombreCalle+"\n CodigoPostal: #"+this.codigoPostal+" \n Colonia: "+this.nombreColonia+" \n Ciudad: "+this.nombreCiudad);
  }
  
}