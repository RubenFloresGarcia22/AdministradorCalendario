package com.administradorcalendario.www.eventos.basedatos;

import java.lang.Object;
import java.lang.String;
import com.administradorcalendario.www.basedatos.Conector;
import java.sql.SQLException;
import com.administradorcalendario.www.eventos.Evento;
import com.administradorcalendario.www.participantes.Participante;
import com.administradorcalendario.www.participantes.TipoParticipante;
import com.administradorcalendario.www.calendarios.Calendario;
import com.administradorcalendario.www.direcciones.Direccion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ConectorEventos extends Conector{
  
  private final String INSERT_EVENTO="INSERT INTO Eventos(Fecha, Nombre, Hora, Descripcion, IdCalendario, IdDireccion)VALUES(?,?,?,?,?,?)";
  
  private final String SELECT_ID_EVENTO="SELECT MAX(Id) FROM Eventos";
    
  private final String INSERT_PARTICIPANTE="INSERT INTO Participantes(Nombre, Email, Empresa, TipoParticipante, IdEvento)VALUES(?,?,?,?,?)";
  
  private final String INSERT_DIRECCION="INSERT INTO Direcciones(NombreCalle, CodigoPostal, NombreColonia, NombreCiudad)VALUES(?,?,?,?)";
  
  private final String SELECT_ID_DIRECCION = "SELECT Id FROM Direcciones WHERE NombreCalle=? AND CodigoPostal=? AND NombreColonia=? LIMIT 1";
  
  
  private final String DELETE_EVENTO="DELETE FROM Eventos WHERE Id=?";
  
  private final String DELETE_PARTICIPANTES="DELETE FROM Participantes WHERE IdEvento=?";
  
  private final String DELETE_DIRECCION="DELETE FROM Direcciones WHERE Id=?";
  
  private final String SELECT_EVENTO_ELIMINAR="SELECT Id, Fecha, Nombre, Hora, Descripcion, IdCalendario, IdDireccion FROM Eventos WHERE Id=?";
  
  private final String SELECT_EVENTO="SELECT e.Id, e.Fecha, e.Nombre, e.Hora, e.Descripcion, e.IdCalendario, d.Id, d.NombreCalle, d.CodigoPostal, d.NombreColonia, d.NombreCiudad FROM Eventos e, Direcciones d WHERE e.IdDireccion = d.Id AND e.Id=?";
  
  private final String SELECT_PARTICIPANTES="SELECT p.Id, p.Nombre, p.Email, p.Empresa, tp.Id, tp.Nombre FROM Participantes p, TiposParticipantes tp WHERE p.TipoParticipante = tp.Id AND IdEvento=? AND TipoParticipante=2";
  
  private final String UPDATE_EVENTO="UPDATE Eventos SET Fecha=?, Nombre=?, Hora=?, Descripcion=?, IdCalendario=?, IdDireccion=? WHERE Id=?";
  
  private final String UPDATE_DIRECCION_EVENTO="UPDATE Direcciones SET NombreCalle=?, CodigoPostal=?, NombreColonia=?, NombreCiudad=? WHERE Id=?";
  
  //private final String DELETE_EMPRESA="DELETE FROM Empresas WHERE Id IN (SELECT Empresa FROM Participantes WHERE Evento=?)";
  
  
  public ConectorEventos(){
  }
  
  public void guardar(Evento evento)throws SQLException{
    Connection conexion = null;
    try{
      conexion =  this.getConexion();
      conexion.setAutoCommit(false);
      guardar(evento.getDireccion(), conexion);
      System.out.println(evento.getDireccion().getId());
      PreparedStatement comando = conexion.prepareStatement(INSERT_EVENTO);
      comando.setString(1, evento.getFecha());
      comando.setString(2, evento.getNombre());
      comando.setString(3, evento.getHora());
      comando.setString(4, evento.getDescripcion());
      comando.setInt(5, evento.getCalendario().getId());
      comando.setInt(6, evento.getDireccion().getId());
      comando.execute();
      comando = conexion.prepareStatement(SELECT_ID_EVENTO);
      ResultSet resultado =  comando.executeQuery();
      while(resultado.next()){
        evento.setId(resultado.getInt(1));
      }
      resultado.close();
      comando.close();
      guardar(evento, evento.getParticipantes(), conexion);
      conexion.commit();
    }catch(SQLException sqle){
      System.err.println(sqle.getMessage());
      if(conexion != null){
        conexion.rollback();
      }
      sqle.printStackTrace();
    }finally{
      this.cerrarConexion(conexion);
    }
  }
      
  private void guardar(Direccion direccion, Connection conexion)throws SQLException{
    PreparedStatement comando = conexion.prepareStatement(INSERT_DIRECCION);
    comando.setString(1, direccion.getNombreCalle());
    comando.setInt(2, direccion.getCodigoPostal());
    comando.setString(3, direccion.getNombreColonia());
    comando.setString(4, direccion.getNombreCiudad());
    comando.execute();
    comando = conexion.prepareStatement(SELECT_ID_DIRECCION);
    comando.setString(1, direccion.getNombreCalle());
    comando.setInt(2, direccion.getCodigoPostal());
    comando.setString(3, direccion.getNombreColonia());
    ResultSet resultado = comando.executeQuery();
    while(resultado.next()){
      direccion.setId(resultado.getInt(1));
    }
    resultado.close();
    comando.close();
  }
  
  private void guardar(Evento evento, ArrayList participantes, Connection conexion)throws SQLException{
    PreparedStatement comando = conexion.prepareStatement(INSERT_PARTICIPANTE);
    for(Object objeto: participantes){
      Participante participante = (Participante)objeto;
      comando.setString(1, participante.getNombre());
      comando.setString(2, participante.getEmail());
      comando.setString(3, participante.getEmpresa());
      comando.setInt(4, participante.getTipo().getId());
      comando.setInt(5, evento.getId());
      comando.execute();
    }
  }
  
  //.:USO DE TRANSACCIONES:.
  public boolean eliminar(Evento evento)throws SQLException{
    boolean isOperacionValida = false;
    Connection conexion = null;
    try{
      conexion = this.getConexion();
      conexion.setAutoCommit(false);
      PreparedStatement comando = conexion.prepareStatement(DELETE_PARTICIPANTES);
      comando.setInt(1, evento.getId());
      comando.execute();
      comando = conexion.prepareStatement(DELETE_EVENTO);
      comando.setInt(1, evento.getId());
      comando.execute();
      comando = conexion.prepareStatement(SELECT_EVENTO_ELIMINAR);
      comando.setInt(1, evento.getId());
      ResultSet resultado = comando.executeQuery();
      Direccion direccion = new Direccion();
      while(resultado.next()){
        direccion.setId(resultado.getInt(7));
        System.out.println("Id Direccion: "+direccion.getId());
      }
      resultado.close();
      comando = conexion.prepareStatement(DELETE_DIRECCION);
      comando.setInt(1, direccion.getId());
      comando.execute();
      conexion.commit();
      isOperacionValida = true;
    }catch(SQLException sqle){
      sqle.printStackTrace(System.err);
      if(conexion != null){
        conexion.rollback();
        isOperacionValida = false;
      }
      throw sqle; //propagar la exception
    }finally{
      this.cerrarConexion(conexion);
    }
    return isOperacionValida;
  }
      
  public Evento getEvento(Evento evento)throws SQLException{
    Connection conexion = null;
    ArrayList participantes = new ArrayList();
    try{
      conexion = this.getConexion();
      PreparedStatement comando = conexion.prepareStatement(SELECT_EVENTO);
      comando.setInt(1, evento.getId());
      ResultSet resultado = comando.executeQuery();
      while(resultado.next()){
        evento.setId(resultado.getInt(1));
        evento.setFecha(resultado.getString(2));
        evento.setNombre(resultado.getString(3));
        evento.setHora(resultado.getString(4));
        evento.setDescripcion(resultado.getString(5));
        Calendario calendario = new Calendario();
        calendario.setId(resultado.getInt(6));
        evento.setCalendario(calendario);
        Direccion direccion = new Direccion();
        direccion.setId(resultado.getInt(7));
        direccion.setNombreCalle(resultado.getString(8));
        direccion.setCodigoPostal(resultado.getInt(9));
        direccion.setNombreColonia(resultado.getString(10));
        direccion.setNombreCiudad(resultado.getString(11));
        evento.setDireccion(direccion);
        evento.setParticipantes(getParticipantes(evento, conexion));        
      }
      resultado.close();
      comando.close();
    }catch(SQLException sqle){
      sqle.printStackTrace(System.err);
      throw sqle;
    }finally{
      this.cerrarConexion(conexion);
    }
    return evento;
  }
  
  private ArrayList getParticipantes(Evento evento, Connection conexion)throws SQLException{
    ArrayList participantes = new ArrayList();
    try{
      PreparedStatement comando = conexion.prepareStatement(SELECT_PARTICIPANTES);
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
      resultado.close();
      comando.close();
    }catch(SQLException sqle){
      sqle.printStackTrace(System.err);
      throw sqle;
    }
    return participantes;
  }
  
  //eventos update
    //participantes borro todo y insert
  
  public void eliminar(Participante participante)throws SQLException{
    
  }
  
  public void actualizar(Evento evento)throws SQLException{
    Connection conexion = null;
    try{
      conexion = this.getConexion();
      conexion.setAutoCommit(false);
      PreparedStatement comando = conexion.prepareStatement(UPDATE_EVENTO);
      comando.setString(1, evento.getFecha());
      comando.setString(2, evento.getNombre());
      comando.setString(3, evento.getHora());
      comando.setString(4, evento.getDescripcion());
      comando.setInt(5, evento.getCalendario().getId());
      comando.setInt(6, evento.getDireccion().getId());
      comando.setInt(7, evento.getId());
      comando.execute();
      comando = conexion.prepareStatement(UPDATE_DIRECCION_EVENTO);
      comando.setString(1, evento.getDireccion().getNombreCalle());
      comando.setInt(2, evento.getDireccion().getCodigoPostal());
      comando.setString(3, evento.getDireccion().getNombreColonia());
      comando.setString(4, evento.getDireccion().getNombreCiudad());
      comando.setInt(5, evento.getDireccion().getId());
      comando.execute();
      comando = conexion.prepareStatement(DELETE_PARTICIPANTES);
      comando.setInt(1, evento.getId());
      comando.execute();
      comando = conexion.prepareStatement(INSERT_PARTICIPANTE);
      for(Object objeto: evento.getParticipantes()){
        Participante participante = (Participante)objeto;
        comando.setString(1, participante.getNombre());
        comando.setString(2, participante.getEmail());
        comando.setString(3, participante.getEmpresa());
        comando.setInt(4, participante.getTipo().getId());
        comando.setInt(5, evento.getId());
        comando.execute();
      }
      conexion.commit();
      comando.close();
    }catch(SQLException sqle){
      if(conexion != null){
        conexion.rollback();
      }
      sqle.printStackTrace();
      System.err.println(sqle.getMessage());
      throw sqle;
    }finally{
      this.cerrarConexion(conexion);
    }
  }
 
}