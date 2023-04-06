package com.administradorcalendario.www.usuarios;

import java.lang.Object;
import java.lang.String;
import com.administradorcalendario.www.personas.Persona;
import com.administradorcalendario.www.empresas.Empresa;
import com.administradorcalendario.www.calendarios.Calendario;

public class Usuario extends Object{
  
  private int id = -1;
  private Persona persona = null;
  private String alias = null;
  private String password = null;
  private String email = null;
  private Empresa empresa = null;
  private Calendario calendario = null;
  
  public Usuario(){
  }
  
  public Usuario(int id, Persona persona, String alias, String password, String email, Empresa empresa, Calendario calendario){
    this.id = id;
    this.persona = persona;
    this.alias = alias;
    this.password = password;
    this.email = email;
    this.empresa = empresa;
    this.calendario = calendario;
  }
  
  public int getId(){
    return id;
  }
  
  public void setId(int id){
    this.id = id;
  }
  
  public Persona getPersona(){
    return persona;
  }
  
  public void setPersona(Persona persona){
    this.persona = persona;
  }
  
  public String getAlias(){
    return alias;
  }
  
  public void setAlias(String alias){
    this.alias = alias;
  }
  
  public String getPassword(){
    return password;
  }
  
  public void setPassword(String password){
    this.password = password;
  }
  
  public String getEmail(){
    return email;
  }
  
  public void setEmail(String email){
    this.email = email;
  }
  
  public Empresa getEmpresa(){
    return empresa;
  }
  
  public void setEmpresa(Empresa empresa){
    this.empresa = empresa;
  }
  
  public Calendario getCalendario(){
    return calendario;
  }
  
  public void setCalendario(Calendario calendario){
    this.calendario = calendario;
  }
  
}