package com.administradorcalendario.www.listados;

import java.lang.Object;
import java.lang.String;
import java.util.ArrayList;

public class Listado extends Object{
  
  protected ArrayList contenido = null;
  
  public Listado(){
  }
  
  public Listado(ArrayList contenido){
    this.contenido = contenido;
  }
  
  public ArrayList getContenido(){
    return contenido;
  }
  
  public void setContenido(ArrayList contenido){
    this.contenido = contenido;
  }
  
  public Object getSiguiente(int indice){
    return this.contenido.get(indice);
  }
  
  public long getTamano(){
    return this.contenido.size();
  }  
  
}