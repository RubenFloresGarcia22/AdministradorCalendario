package com.administradorcalendario.www.usuarios.servicios;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.RequestDispatcher;

public class ServicioBase extends HttpServlet{
  
  public void doGet(HttpServletRequest peticion, HttpServletResponse respuesta)throws ServletException, IOException{
  }
  
  public void doPost(HttpServletRequest peticion, HttpServletResponse respuesta)throws ServletException, IOException{  
  }
  
  protected void enviarA(String recurso, HttpServletRequest peticion, HttpServletResponse respuesta)throws ServletException, IOException{
    try{
      RequestDispatcher despachadorRecurso = this.getServletContext().getRequestDispatcher(recurso);
      despachadorRecurso.forward(peticion, respuesta);
    }catch(ServletException se){
      se.printStackTrace();
      System.err.println(se.getMessage());
      throw se;
    }
  }
  
  protected void establecerEncabezado(HttpServletResponse respuesta){
    respuesta.setHeader("content-type", "text/html");
    respuesta.setHeader("cache-control", "no-cache");
  }
  
}