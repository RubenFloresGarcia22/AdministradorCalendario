package com.administradorcalendario.www.tiposParticipante.basedatos;

import  com.administradorcalendario.www.basedatos.Conector;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.administradorcalendario.www.participantes.TipoParticipante;
public class ConectorTiposParticipante extends Conector{
  
  private final String SELECT_TIPOS_PARTICIPANTE = "SELECT Id, Nombre FROM TiposParticipantes ORDER BY Id";
  
  public ConectorTiposParticipante(){
    super();
  }
  
  public ArrayList getTiposParticipante()throws SQLException{
    ArrayList tiposParticipantes = new ArrayList();
    Connection conexion = null;
    try{
      conexion = this.getConexion();
      PreparedStatement comando = conexion.prepareStatement(SELECT_TIPOS_PARTICIPANTE);
      ResultSet resultado = comando.executeQuery();
      while(resultado.next()){
        TipoParticipante tipoParticipante = new TipoParticipante();
        tipoParticipante.setId(resultado.getInt(1));
        tipoParticipante.setNombre(resultado.getString(2));
        tiposParticipantes.add(tipoParticipante);
      }
      resultado.close();
      comando.close();
    }catch(SQLException sqle){
      System.err.println(sqle.getMessage());
      sqle.printStackTrace();
      throw sqle;
    }finally{
      this.cerrarConexion(conexion);
    }
    return tiposParticipantes;
  }
  
}