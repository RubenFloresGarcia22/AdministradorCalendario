package com.administradorcalendario.www.basedatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import com.mysql.jdbc.Driver;

public class Conector{
  
  private String url = null;
  private String usuario = null;
  private String password = null;
  
  public Conector(){
    this.url="jdbc:mysql://127.0.0.1:3306/AdministradorCalendario";
    this.usuario="root";
    this.password="Rub3nF10r35_MySQL";
  }
  
  public Conector(String url, String usuario, String password){
    this.url = url;
    this.usuario = usuario;
    this.password = password;
  }
  
  /*public Conector(){
    
  }*/
  
  public String getUrl(){
    return url;
  }
  
  public void setUrl(String url){
    this.url = url;
  }
  
  public String getUsuario(){
    return usuario;
  }
  
  public void setUsuario(String usuario){
    this.usuario = usuario;
  }
  
  public String getPassword(){
    return password;
  }
  
  public void setPassword(String password){
    this.password = password;
  }
  
  public Connection getConexion()throws SQLException{
    Connection conexion = null;
    try{
      DriverManager.registerDriver(new Driver());
      conexion = DriverManager.getConnection(this.url, this.usuario, this.password);
    }catch(SQLException sqle){
      sqle.printStackTrace();
      System.err.println(sqle.getMessage());
    }
    return conexion;
  }
  
  public void cerrarConexion(Connection conexion)throws SQLException{
    try{
      conexion.close();
    }catch(SQLException sqle){
      sqle.printStackTrace();
      System.err.println(sqle.getMessage());
    }
  }
  
}