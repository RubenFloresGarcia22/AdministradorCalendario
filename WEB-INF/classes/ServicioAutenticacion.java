package com.administradorcalendario.www.usuarios.servicios;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import com.administradorcalendario.www.usuarios.Usuario;
import com.administradorcalendario.www.usuarios.basedatos.ConectorUsuarios;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;
import com.administradorcalendario.www.usuarios.servicios.ServicioBase;

public class ServicioAutenticacion extends ServicioBase{
  
  private final String VISTA_LOGIN="/Vistas/Usuarios/Login.jsp";
  
  private final String VISTA_CALENDARIO="/Vistas/Calendarios/Calendario.jsp";
    
  public void doGet(HttpServletRequest peticion, HttpServletResponse respuesta)throws ServletException, IOException{
    this.establecerEncabezado(respuesta);
    this.enviarA(VISTA_LOGIN, peticion, respuesta);
  }
  
  public void doPost(HttpServletRequest peticion, HttpServletResponse respuesta)throws ServletException, IOException{
    this.establecerEncabezado(respuesta);
    if(peticion.getParameter("btnAutenticar")== null){
      this.enviarA(VISTA_LOGIN, peticion, respuesta);
    }else{
      Usuario usuario = new Usuario();
      usuario.setAlias(peticion.getParameter("txtAlias"));
      usuario.setPassword(peticion.getParameter("txtPassword"));
      ConectorUsuarios conectorUsuarios = new ConectorUsuarios();
      try{
        boolean usuarioValido = conectorUsuarios.isValido(usuario);
        System.out.println("UsuarioBaseDatos: "+usuarioValido);
        if(usuarioValido){
          HttpSession sesion = peticion.getSession();
          usuario = conectorUsuarios.getUsuario(usuario);
          sesion.setAttribute("usuario", usuario);
          this.enviarA(VISTA_CALENDARIO, peticion, respuesta);
        }else{
          peticion.setAttribute("lblUsuarioInvalido", "Error: Usuario invalido.");
          this.enviarA(VISTA_LOGIN, peticion, respuesta);
        }
      }catch(Exception e){
        e.printStackTrace();
        System.err.println(e.getMessage());
        throw (ServletException)e;
      }
      System.out.println("Datos Peticion: "+usuario.getAlias()+" "+usuario.getPassword()+" "+usuario.getEmpresa().getNombre());
      this.enviarA(VISTA_CALENDARIO, peticion, respuesta);
    }
  }
    
}