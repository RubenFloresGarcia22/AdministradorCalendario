package com.administradorcalendario.www.participantes;

import com.administradorcalendario.www.personas.Persona;
import com.administradorcalendario.www.empresas.Empresa;
import com.administradorcalendario.www.participantes.TipoParticipante;

public class Participante{
  
  private int id = 0;
  private String nombre = null;
  private String email = null;
  private String empresa = null;
  private TipoParticipante tipo = null;
  
  public Participante(){
  }
  
  public Participante(int id, String nombre, String email, String empresa, TipoParticipante tipo){
    this.id = id;
    this.nombre = nombre;
    this.email = email;
    this.empresa = empresa;
    this.tipo = tipo;
  }
  
  public int getId(){
    return id;
  }
  
  public void setId(int id){
    this.id = id;
  }
  
  public String getNombre(){
    return nombre;
  }
  
  public void setNombre(String nombre){
    this.nombre = nombre;
  }
  
  public String getEmail(){
    return email;
  }
  
  public void setEmail(String email){
    this.email = email;
  }
  
  public String getEmpresa(){
    return empresa;
  }
  
  public void setEmpresa(String empresa){
    this.empresa = empresa;
  }
  
  public TipoParticipante getTipo(){
    return tipo;
  }
  
  public void setTipo(TipoParticipante tipo){
    this.tipo = tipo;
  }
  
}