<%@page info="AdministracionCalendario" contentType="text/html" session="true" import="com.administrdorcalendario.www.utilerias.Utileria, com.administradorcalendario.www.usuarios.Usuario, com.administradorcalendario.www.listados.ListadoTiposParticipante, com.administradorcalendario.www.participantes.TipoParticipante"%>

<%! private final int NUMERO_DIAS=31;%>

<%! private final String[] MESES_ANIO={"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};%>

<%!private final int HORAS=12;%>
<%!private final int SEGUNDOS=59;%>

<%if(session.getAttribute("usuario") == null){%>
  <jsp:forward page="/Vistas/Usuarios/Login.jsp"/>
<%}else{%>
  <html>
    <head>
      <title>Registro</title>
      <script language="javascript" src="/AdministradorCalendario/Vistas/Eventos/Calendarios.js">
      </script>
    </head>
    <body>
      <center>
        <h1>Registro</h1>
      </center>
      <center>
        <table width="40%" border="1">
          <form name="frmRegistrarEvento" method="POST" action="/AdministradorCalendario/eventos/ServicioRegistrarEvento" onSubmit="seleccionarParticipantes(lstParticipantes);return validarFormulario(this);">
            <tr>
              <td colspan="2" align="center">
                <h2>Evento</h2>
              </td>
            </tr>
            <tr>
              <td>Fecha:</td>
              <td>
                <select name="lstDias">
                  <%for(int dia=0; dia<=NUMERO_DIAS; dia++){%>
                    <option value="<%=dia%>"><%=dia%></option>
                  <%}%>
                </select>
                <select name="lstMeses">
                  <%for(int mes=0; mes<MESES_ANIO.length;mes++){%>
                    <option value="<%=mes%>"><%=MESES_ANIO[mes]%></option>
                  <%}%>
                </select>
                <select name="lstAnios">
                  <option name="<%=Utileria.getAnioActual()%>"><%=Utileria.getAnioActual()%></option>
                </select>
              </td>
            </tr>
            <tr>
              <td>Nombre:</td>
              <td>
                <input type="text" name="txtNombre" size="50" maxlength="50"/>
              </td>
            </tr>
            <tr>
              <td>Hora:</td>
              <td>
                <select name="lstHoras">
                  <%for(int hora=0; hora<=HORAS; hora++){%>
                      <option name="<%=hora%>"><%=((hora<10)?"0"+hora:hora)%></option>
                  <%}%>
                </select>
                <select name="lstSegundos">
                  <%for(int segundo=0; segundo<=SEGUNDOS; segundo++){%>
                    <option name="<%=segundo%>"><%=((segundo<10)?"0"+segundo:segundo)%></option>
                  <%}%>
                </select>
              </td>
            </tr>
            <tr>
              <td>Descripcion:</td>
              <td>
                <textarea name="txtAreaDescripcion" rows="5" cols="40"></textarea>
              </td>
            </tr>
            <tr>
              <td colspan="2" align="center">
                <h2>Direccion</h2>
              </td>
            </tr>
            <tr>
              <td>Calle:</td>
              <td>
                <input type="text" name="txtNombreCalle" size="40" maxlength="40"/>
              </td>
            </tr>
            <tr>
              <td>Codigo Postal:</td>
              <td>
                <input type="text" name="txtCodigoPostal" size="10" maxlength="10"/>
              </td>
            </tr>
            <tr>
              <td>Colonia:</td>
              <td>
                <input type="text" name="txtNombreColonia" size="40" maxlength="40"/>
              </td>
            </tr>
            <tr>
              <td>Ciudad:</td>
              <td>
                <input type="text" name="txtNombreCiudad" size="40" maxlength="40"/>
              </td>
            </tr>
            <tr>
              <td colspan="2" align="center">
                <h2>Participantes</h2>
              </td>
            </tr>
            <tr>
              <td>Nombre:</td>
              <td>
                <input type="text" name="txtNombreParticipante" size="40" maxlength="40"/>
              </td>
            </tr>
            <tr>
              <td>Email:</td>
              <td>
                <input type="text" name="txtEmailParticipante" size="40" maxlength="40"/>
              </td>
            </tr>
            <tr>
              <td>Empresa:</td>
              <td>
                <input type="text" name="txtEmpresaParticipante" size="40" maxlength="40"/>
              </td>
            </tr>
            <tr>
              <td>Tipo:</td>
              <td>
                <select name="lstTipoParticipantes">
                  <%ListadoTiposParticipante listadoTiposParticipante = new ListadoTiposParticipante();
                    for(Object objeto: listadoTiposParticipante.getContenido()){
                      TipoParticipante tipoParticipante = (TipoParticipante)objeto;
                      if(tipoParticipante.getId() == TipoParticipante.ORGANIZADOR)continue;%>
                        <option value="<%=tipoParticipante.getId()%>&<%=tipoParticipante.getNombre()%>"><%=tipoParticipante.getNombre()%></option>
                  <%}%>
                </select>
              </td>
            </tr>
            <tr>
              <td>
                <input type="button" onClick="agregarParticipante(txtNombreParticipante,txtEmailParticipante,txtEmpresaParticipante,lstTipoParticipantes,lstParticipantes);" name="btnAgregarParticipante" value="+"/>
              </td>
              <td>
                <input type="button" onClick="eliminarParticipante(lstParticipantes);" name="btnEliminarParticipante" value="-"/>
              </td>
            </tr>
            <tr>
              <td colspan="2">
                <select multiple name="lstParticipantes">
                </select>
              </td>
            </tr>
            <tr>
              <td>
                <input type="button" onClick="confirmarCancelacion();" name="Cancelar" value="Cancelar"/>
              </td>
              <td>
                <input type="submit" name="btnRegistrar" value="Registrar"/>
              </td>
            </tr>
          </form>
        </table>
      </center>
    </body>
  </html>
<%}%>