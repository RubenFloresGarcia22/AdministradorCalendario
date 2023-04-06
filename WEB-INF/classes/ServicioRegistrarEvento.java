package com.administradorcalendario.www.eventos.servicios;

import com.administradorcalendario.www.usuarios.servicios.ServicioBase;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import com.administradorcalendario.www.eventos.Evento;
import com.administradorcalendario.www.eventos.basedatos.ConectorEventos;
import java.util.ArrayList;
import com.administradorcalendario.www.direcciones.Direccion;
import com.administradorcalendario.www.participantes.Participante;
import com.administradorcalendario.www.participantes.TipoParticipante;
import javax.servlet.http.HttpSession;
import com.administradorcalendario.www.usuarios.Usuario;

public class ServicioRegistrarEvento extends ServicioBase{
  
  private final String PAGINA_REGISTRO_EVENTO="/Vistas/Eventos/RegistrarEvento.jsp";
  private final String PAGINA_CALENDARIO="/Vistas/Calendarios/Calendario.jsp";
  
  public void doGet(HttpServletRequest peticion, HttpServletResponse respuesta)throws ServletException, IOException{
    this.establecerEncabezado(respuesta);
    this.enviarA(PAGINA_CALENDARIO, peticion, respuesta);
  }
  
  public void doPost(HttpServletRequest peticion, HttpServletResponse respuesta)throws ServletException, IOException{
    this.establecerEncabezado(respuesta);
    if(peticion.getParameter("btnAgregar")!= null){
      this.enviarA(PAGINA_REGISTRO_EVENTO, peticion, respuesta);
    }else{
      if(peticion.getParameter("btnRegistrar") != null){
        Evento evento = new Evento();
        evento.setFecha(peticion.getParameter("lstDias")+"-"+peticion.getParameter("lstMeses")+"-"+peticion.getParameter("lstAnios"));
        System.out.println();
        System.out.println("REVISION PARAMETROS PETICION...........");
        System.out.println((peticion.getParameter("lstDias")+"-"+peticion.getParameter("lstMeses")+"-"+peticion.getParameter("lstAnios")));
        evento.setNombre(peticion.getParameter("txtNombre"));
        System.out.println(peticion.getParameter("txtNombre"));
        evento.setHora(peticion.getParameter("lstHoras")+":"+peticion.getParameter("lstSegundos"));
        System.out.println(peticion.getParameter("lstHoras")+":"+peticion.getParameter("lstSegundos"));
        evento.setDescripcion(peticion.getParameter("txtAreaDescripcion"));
        System.out.println(peticion.getParameter("txtAreaDescripcion"));
        Direccion direccion = new Direccion();
        direccion.setNombreCalle(peticion.getParameter("txtNombreCalle"));
        System.out.println(peticion.getParameter("txtNombreCalle"));
        direccion.setCodigoPostal(Integer.parseInt(peticion.getParameter("txtCodigoPostal").trim()));
        System.out.println(peticion.getParameter("txtCodigoPostal").trim());
        direccion.setNombreColonia(peticion.getParameter("txtNombreColonia"));
        System.out.println(peticion.getParameter("txtNombreColonia"));
        direccion.setNombreCiudad(peticion.getParameter("txtNombreCiudad"));
        System.out.println(peticion.getParameter("txtNombreCiudad"));
        evento.setDireccion(direccion);
        Participante participante = new Participante();
        String[] informacionParticipantes = peticion.getParameterValues("lstParticipantes");
        System.out.println(peticion.getParameterValues("lstParticipantes"));
        ArrayList participantes = new ArrayList();
        for(int p=0; p<informacionParticipantes.length; p++){
          String informacionParticipante = informacionParticipantes[p];
          String[] datosParticipante = informacionParticipante.split("&");
          participante.setNombre(datosParticipante[0]);
          participante.setEmail(datosParticipante[1]);
          participante.setEmpresa(datosParticipante[2]);
          TipoParticipante tipo = new TipoParticipante();
          tipo.setId(Integer.parseInt(datosParticipante[3]));
          participante.setTipo(tipo);
          System.out.println("Participantes agregados: "+p);
          participantes.add(participante);
        }
        HttpSession sesion = peticion.getSession();
        Usuario usuario = (Usuario)sesion.getAttribute("usuario");
        System.out.println("Usuario session: "+usuario.getAlias()+" "+usuario.getPassword());
        Participante organizador = new Participante();
        organizador.setNombre(usuario.getAlias());
        organizador.setEmail(usuario.getEmail());
        organizador.setEmpresa(usuario.getEmpresa().getNombre());
        System.out.println("Empresa organizador: "+usuario.getEmpresa().getNombre());
        TipoParticipante tipo = new TipoParticipante();
        tipo.setId(TipoParticipante.ORGANIZADOR);
        organizador.setTipo(tipo);
        participantes.add(organizador);
        evento.setParticipantes(participantes);
        evento.setCalendario(usuario.getCalendario());
        ConectorEventos conectorEventos = new ConectorEventos();
        try{
          conectorEventos.guardar(evento);//parametros por referencia (cuando mando una instancia como parametro se modifica en la instancia original)
          //peticion.setAttribute("lblEventoAgregado","Evento Registrado.");
          peticion.setAttribute("lblEventoAgregado", this.getInitParameter("lblEventoAgregado"));
          peticion.setAttribute("idEvento",evento.getId());
          this.enviarA(PAGINA_CALENDARIO, peticion, respuesta);
        }catch(Exception e){
          System.err.println(e.getMessage());
          e.printStackTrace();
          throw (ServletException)e;
        }
      }
    }
    
  }
  
}