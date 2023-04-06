package com.administradorcalendario.www.eventos.servicios;

import com.administradorcalendario.www.usuarios.servicios.ServicioBase;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import com.administradorcalendario.www.eventos.Evento;
import com.administradorcalendario.www.eventos.basedatos.ConectorEventos;

public class ServicioConsultarEvento extends ServicioBase{
  
  private final String VISTA_CALENDARIOS="/Vistas/Calendarios/Calendario.jsp";
  
  private final String VISTA_EVENTO="/Vistas/Eventos/Evento.jsp";
  
  public void doGet(HttpServletRequest peticion, HttpServletResponse respuesta)throws ServletException, IOException{
    this.establecerEncabezado(respuesta);
    if(peticion.getParameter("btnConsultar")!= null){
      Evento evento = new Evento();
      evento.setId(Integer.parseInt(peticion.getParameter("hdnIdEvento")));
      ConectorEventos conectorEventos = new ConectorEventos();
      try{
        evento = conectorEventos.getEvento(evento);
        peticion.setAttribute("evento", evento);
      }catch(Exception e){
        e.printStackTrace();
        System.err.println(e.getMessage());
        throw (ServletException)e;
      }
      this.enviarA(VISTA_EVENTO, peticion, respuesta);
    }
  }
  
  public void doPost(HttpServletRequest peticion, HttpServletResponse respuesta)throws ServletException,IOException{
    this.establecerEncabezado(respuesta);
    
    this.enviarA(VISTA_EVENTO, peticion, respuesta);
  }
  
}