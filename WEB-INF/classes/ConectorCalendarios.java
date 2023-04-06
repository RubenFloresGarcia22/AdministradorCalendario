package com.administradorcalendario.www.calendarios.basedatos;

import com.administradorcalendario.www.basedatos.Conector;
import com.administradorcalendario.www.calendarios.Calendario;
import com.administradorcalendario.www.usuarios.Usuario;
import com.administradorcalendario.www.eventos.Evento;
import com.administradorcalendario.www.participantes.Participante;
import com.administradorcalendario.www.participantes.TipoParticipante;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.administradorcalendario.www.direcciones.Direccion;

public class ConectorCalendarios extends Conector{
  
  private final String SELECT_CALENDARIO_USUARIO="SELECT Id, FechaCreacion, Nombre FROM Calendarios  WHERE IdUsuario = ?";
  
  private final String SELECT_EVENTOS_CALENDARIO="SELECT e.Id, e.Fecha, e.Hora, e.Descripcion, d.Id, d.NombreCalle, d.CodigoPostal, d.NombreColonia, d.NombreCiudad FROM Eventos e, Direcciones d WHERE e.IdDireccion = d.Id AND e.IdCalendario=?";
  //"SELECT e.Id, e.Fecha, e.Hora, e.Descripcion, d.Id, d.NombreCalle, d.CodigoPostal, d.NombreColonia, d.NombreCiudad FROM Eventos e, Direcciones d WHERE e.Id = e.IdCalendario=?"
  private final String SELECT_PARTICIPANTES_EVENTOS="SELECT p.Id, p.Nombre, p.Email, p.Empresa, p.TipoParticipante, tp.Nombre FROM Participantes p, TiposParticipantes tp WHERE p.TipoParticipante = tp.Id AND p.IdEvento=?";
  
  //private final String SELECT_PARTICIPANTES_EVENTOS_DIRECCION="SELECT p.Id, p.Nombre, p.Email, p.Empresa, p.TipoParticipante, p.IdEvento, tp.Id, tp.Nombre, d.Id, d.NombreCalle, d.CodigoPostal, d.NombreColonia, d.NombreCiudad, d.IdEvento FROM Participantes p, TiposParticipantes tp, Direcciones d WHERE p.TipoParticipante=tp.Id AND p.IdEvento=d.IdEvento AND p.IdEvento=?";
  
  public ConectorCalendarios(){
  }
  
  public Calendario getCalendario(Usuario usuario)throws SQLException{
    Connection conexion = null;
    Calendario calendario = null;
    try{
      conexion = this.getConexion();
      PreparedStatement comando = conexion.prepareStatement(SELECT_CALENDARIO_USUARIO);
      comando.setInt(1, usuario.getId());
      ResultSet resultado = comando.executeQuery();
      while(resultado.next()){
        calendario = new Calendario();
        calendario.setId(resultado.getInt(1));
        calendario.setFechaCreacion(resultado.getString(2));
        calendario.setNombre(resultado.getString(3));
        calendario.setEventos(getEventos(calendario, conexion));
      }
      resultado.close();
      comando.close();
    }catch(SQLException sqle){
      sqle.printStackTrace();
      System.err.println(sqle.getMessage());
    }finally{
      this.cerrarConexion(conexion);
    }
    return calendario;
  }
  
  private ArrayList getEventos(Calendario calendario, Connection conexion)throws SQLException{
    ArrayList eventos = new ArrayList();
    PreparedStatement comando = conexion.prepareStatement(SELECT_EVENTOS_CALENDARIO);
    comando.setInt(1, calendario.getId());
    ResultSet resultado = comando.executeQuery();
    while(resultado.next()){
      Evento evento = new Evento();
      evento.setId(resultado.getInt(1));
      evento.setFecha(resultado.getString(2));
      evento.setHora(resultado.getString(3));
      evento.setDescripcion(resultado.getString(4));
      evento.setParticipantes(getParticipantes(evento, conexion));
      eventos.add(evento);
      Direccion direccion = new Direccion();
      direccion.setId(resultado.getInt(5));
      direccion.setNombreCalle(resultado.getString(6));
      direccion.setCodigoPostal(resultado.getInt(7));
      direccion.setNombreColonia(resultado.getString(8));
      direccion.setNombreCiudad(resultado.getString(9));
      evento.setDireccion(direccion);
    }
    resultado.close();
    comando.close();
    return eventos;
  }
  
  private ArrayList getParticipantes(Evento evento, Connection conexion)throws SQLException{
    ArrayList participantes = new ArrayList();
    PreparedStatement comando = conexion.prepareStatement(SELECT_PARTICIPANTES_EVENTOS);
    comando.setInt(1, evento.getId());
    ResultSet resultado = comando.executeQuery();
    while(resultado.next()){
      Participante participante = new Participante();
      participante.setId(resultado.getInt(1));
      participante.setNombre(resultado.getString(2));
      participante.setEmail(resultado.getString(3));
      participante.setEmpresa(resultado.getString(4));
      TipoParticipante tipo = new TipoParticipante();
      tipo.setId(resultado.getInt(5));
      tipo.setNombre(resultado.getString(6));
      participante.setTipo(tipo);
      participantes.add(participante);
    }
    return participantes;
  }
  
}