package com.administradorcalendario.www.usuarios.basedatos;

import com.administradorcalendario.www.basedatos.Conector;
import com.administradorcalendario.www.usuarios.Usuario;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.administradorcalendario.www.calendarios.Calendario;
import com.administradorcalendario.www.empresas.Empresa;

public class ConectorUsuarios extends Conector{
  
  private final String INSERT_USUARIO="INSERT INTO Usuarios(Alias, Password, Email, IdCalendario, IdEmpresa)VALUES(?,?,?,?,?)";
  
  private final String SELECT_USUARIO="SELECT Id, Alias, Password, Email, IdCalendario, IdEmpresa FROM Usuarios WHERE Alias=? AND Password=?";
  
  private final String SELECT_USUARIO_VALIDO="SELECT u.Id, u.Alias, u.Password, u.Email, u.IdCalendario, u.IdEmpresa, e.Id, e.Nombre FROM Usuarios u, Empresas e WHERE Alias=? AND Password=?";
    
  private final String SELECT_USUARIOS="SELECT Id, Alias, Password, Email, IdCalendario, IdEmpresa FROM Usuarios ORDER BY Id";
  
  private final String UPDATE_USUARIO="UPDATE Usuarios SET Alias=?, Password=?, Email=?, IdCalendario=?, IdEmpresa=? WHERE Alias=? AND Password=?";
  
  private final String DELETE_USUARIO="DELETE FROM Usuarios WHERE Alias=? AND Password=?";
  
  public void guardar(Usuario usuario)throws SQLException{
    Connection conexion = null;
    try{
      conexion = this.getConexion();
      PreparedStatement comando = conexion.prepareStatement(INSERT_USUARIO);
      comando.setString(1, usuario.getAlias());
      comando.setString(2, usuario.getPassword());
      comando.setString(3, usuario.getEmail());
      comando.setInt(4, usuario.getCalendario().getId());
      comando.setInt(5, usuario.getEmpresa().getId());
      comando.execute();
      comando.close();
    }catch(SQLException sqle){
      sqle.printStackTrace();
      System.err.println(sqle.getMessage());
    }finally{
      this.cerrarConexion(conexion);
    }
  }
    
  public Usuario getUsuario(Usuario usuario)throws SQLException{
    Connection conexion = null;
    try{
      conexion = this.getConexion();
      PreparedStatement comando = conexion.prepareStatement(SELECT_USUARIO);
      comando.setString(1, usuario.getAlias());
      comando.setString(2, usuario.getPassword());
      ResultSet resultado = comando.executeQuery();
      while(resultado.next()){
        usuario.setId(resultado.getInt(1));
        usuario.setAlias(resultado.getString(2));
        usuario.setPassword(resultado.getString(3));
        usuario.setEmail(resultado.getString(4));
        Calendario calendario = new Calendario();
        calendario.setId(resultado.getInt(5));
        usuario.setCalendario(calendario);
        Empresa empresa = new Empresa();
        empresa.setId(resultado.getInt(6));
        usuario.setEmpresa(empresa);
      }
      resultado.close();
      comando.close();
    }catch(SQLException sqle){
      sqle.printStackTrace();
      System.err.println(sqle.getMessage());
    }finally{
      this.cerrarConexion(conexion);
    }
    return usuario;
  }
  
  public boolean isValido(Usuario usuario)throws SQLException{
    boolean isUsuarioValido = false;
    Connection conexion = null;
    try{
      conexion = this.getConexion();
      PreparedStatement comando = conexion.prepareStatement(SELECT_USUARIO_VALIDO);
      comando.setString(1, usuario.getAlias());
      comando.setString(2, usuario.getPassword());
      ResultSet resultado = comando.executeQuery();
      while(resultado.next()){
        usuario.setId(resultado.getInt(1));
        usuario.setAlias(resultado.getString(2));
        usuario.setPassword(resultado.getString(3));
        usuario.setEmail(resultado.getString(4));
        Calendario calendario = new Calendario();
        calendario.setId(resultado.getInt(5));
        usuario.setCalendario(calendario);
        Empresa empresa = new Empresa();
        empresa.setId(resultado.getInt(6));
        empresa.setNombre(resultado.getString(8));
        usuario.setEmpresa(empresa);
        isUsuarioValido = true;
      }
      resultado.close();
      comando.close();
    }catch(SQLException sqle){
      sqle.printStackTrace();
      System.err.println(sqle.getMessage());
    }finally{
      this.cerrarConexion(conexion);
    }
    return isUsuarioValido;
  }
  
  public ArrayList getUsuarios()throws SQLException{
    Connection conexion = null;
    ArrayList usuarios = new ArrayList();
    try{
      conexion = this.getConexion();
      PreparedStatement comando = conexion.prepareStatement(SELECT_USUARIOS);
      ResultSet resultado = comando.executeQuery();
      while(resultado.next()){
        Usuario usuario = new Usuario();
        usuario.setId(resultado.getInt(1));
        usuario.setAlias(resultado.getString(2));
        usuario.setPassword(resultado.getString(3));
        usuario.setEmail(resultado.getString(4));
        Calendario calendario = new Calendario();
        calendario.setId(resultado.getInt(5));
        usuario.setCalendario(calendario);
        Empresa empresa = new Empresa();
        empresa.setId(resultado.getInt(6));
        usuario.setEmpresa(empresa);
        usuarios.add(usuario);
      }
      resultado.close();
      comando.close();
    }catch(SQLException sqle){
      sqle.printStackTrace();
      System.err.println(sqle.getMessage());
    }finally{
      this.cerrarConexion(conexion);
    }
    return usuarios;
  }
  
  public void actualizar(Usuario usuario)throws SQLException{
    Connection conexion = null;
    try{
      conexion = this.getConexion();
      PreparedStatement comando = conexion.prepareStatement(UPDATE_USUARIO);
      comando.setString(1, usuario.getAlias());
      comando.setString(2, usuario.getPassword());
      comando.setString(3, usuario.getEmail());
      comando.setInt(4, usuario.getCalendario().getId());
      comando.setInt(5, usuario.getEmpresa().getId());
      comando.setString(6, usuario.getAlias());
      comando.setString(7, usuario.getPassword());
      comando.execute();
      comando.close();
    }catch(SQLException sqle){
      sqle.printStackTrace();
      System.err.println(sqle.getMessage());
    }finally{
      this.cerrarConexion(conexion);
    }
  }
  
  public void eliminar(Usuario usuario)throws SQLException{
    Connection conexion = null;
    try{
      conexion = this.getConexion();
      PreparedStatement comando = conexion.prepareStatement(DELETE_USUARIO);
      comando.setString(1, usuario.getAlias());
      comando.setString(2, usuario.getPassword());
      comando.execute();
      comando.close();
    }catch(SQLException sqle){
      sqle.printStackTrace();
      System.err.println(sqle.getMessage());
    }finally{
      this.cerrarConexion(conexion);
    }
  }
}