package com.administradorcalendario.www.listados;

import java.lang.Object;
import java.lang.String;
import com.administradorcalendario.www.listados.Listado;
import com.administradorcalendario.www.tiposParticipante.basedatos.ConectorTiposParticipante;

public class ListadoTiposParticipante extends Listado{
  
  public ListadoTiposParticipante()throws Exception{
    super();
    try{
      ConectorTiposParticipante conectorTiposParticipante = new ConectorTiposParticipante();
      this.contenido = conectorTiposParticipante.getTiposParticipante();
    }catch(Exception e){
      System.err.println(e.getMessage());
      e.printStackTrace();
      throw e;
    }
  }
  
}