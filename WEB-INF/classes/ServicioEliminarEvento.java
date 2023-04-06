package com.administradorcalendario.www.eventos.servicios;

import com.administradorcalendario.www.usuarios.servicios.ServicioBase;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import com.administradorcalendario.www.eventos.Evento;
import com.administradorcalendario.www.eventos.basedatos.ConectorEventos;

public class ServicioEliminarEvento extends ServicioBase{
  
  private final String VISTA_LISTADO_CALENDARIO="/Vistas/Calendarios/Calendario.jsp";
  
  public void doGet(HttpServletRequest peticion, HttpServletResponse respuesta)throws ServletException, IOException{
    this.establecerEncabezado(respuesta);
    this.enviarA(VISTA_LISTADO_CALENDARIO, peticion, respuesta);
  }
  
  public void doPost(HttpServletRequest peticion, HttpServletResponse respuesta)throws ServletException, IOException{
    this.establecerEncabezado(respuesta);
    Evento evento = new Evento();
    evento.setId(Integer.parseInt(peticion.getParameter("hdnIdEvento").trim()));
    ConectorEventos conectorEventos = new ConectorEventos();
    try{
      conectorEventos.eliminar(evento);
      peticion.setAttribute("lblEventoEliminado", "Evento eliminado.");
      this.enviarA(VISTA_LISTADO_CALENDARIO, peticion, respuesta);
    }catch(Exception ex){
      ex.printStackTrace(System.err);
      throw (ServletException)ex;
    }
  }
  
}