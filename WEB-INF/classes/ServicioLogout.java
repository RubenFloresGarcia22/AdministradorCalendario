package com.administradorcalendario.www.usuarios.servicios;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import com.administradorcalendario.www.usuarios.servicios.ServicioBase;

public class ServicioLogout extends ServicioBase{
  
  private final String PAGINA_LOGIN="/Vistas/Usuarios/Login.jsp";
  
  private final String PAGINA_ERROR_ACCESO="/Vistas/Usuarios/ErrorAcceso.jsp";
  
  public void doGet(HttpServletRequest peticion, HttpServletResponse respuesta)throws ServletException, IOException{
    this.establecerEncabezado(respuesta);
    peticion.setAttribute("lblErrorAcceso","Error: Acceso no autorizado...");
    this.enviarA(PAGINA_LOGIN, peticion, respuesta);
  }
  
  public void doPost(HttpServletRequest peticion, HttpServletResponse respuesta)throws ServletException, IOException{
    if(peticion.getParameter("btnLogout")!= null){
      HttpSession session = peticion.getSession();
      session.removeAttribute("usuario");
      this.enviarA(PAGINA_LOGIN, peticion, respuesta);
    }else{
      this.enviarA(PAGINA_ERROR_ACCESO, peticion, respuesta);
    }
  }
    
}