package com.administrdorcalendario.www.utilerias;

import java.lang.Object;
import java.lang.String;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Utileria{
  
  public Utileria(){
  }
  
  public static String getAnioActual(){
     Date fechaActual = new Date();
     SimpleDateFormat formateadorFecha = new SimpleDateFormat("yyyy-MM-dd");
     String fechaFormateada = formateadorFecha.format(fechaActual);
     String[] valorFecha = fechaFormateada.split("-");
     String valor = valorFecha[0];
     return valor;
  }
  
  public static String[] fechaFormateada(String fecha){
    String cadenaFecha = fecha;
    String[] arregloCadena = cadenaFecha.split("-");
    return arregloCadena;
  }
    
}