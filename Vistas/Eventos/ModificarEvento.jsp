<%@page info="AdministradorCalendario" contentType="text/html" session="true" import="com.administradorcalendario.www.eventos.Evento, com.administrdorcalendario.www.utilerias.Utileria, com.administradorcalendario.www.participantes.Participante, com.administradorcalendario.www.listados.ListadoTiposParticipante,  com.administradorcalendario.www.participantes.TipoParticipante"%>

<%! private final int NUMERO_DIAS= 31;%>

<%!private final String[] MESES_ANIO = {"","Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};%>

<%!private final int HORAS = 12;%>

<%!private final int SEGUNDOS = 59;%>

<%if(session.getAttribute("usuario")== null){%>
  <jsp:forward page="/Vistas/Usuarios/Login.jsp"/>
<%}else{%>

<%Evento evento = null;
  if(request.getAttribute("evento") != null){
    evento = (Evento)request.getAttribute("evento");
  }%>
    
  <html>
    <head>
      <title>Modificar Evento</title>
      <script language="javascript" src="/AdministradorCalendario/Vistas/Eventos/Calendarios.js">
      </script>
    </head>
    <body>
      <center>
        <h1>Modificar</h1>
      </center>
      <center>
        <table width="40%" border="1">
          <form name="frmModificarEvento" method="POST" action="/AdministradorCalendario/eventos/ServicioModificarEvento" onSubmit="seleccionarParticipantes(lstParticipantes); return validarFormulario(this);">
            <tr>
              <td colspan="4" align="center">
                <h2>Evento</h2>
                <input type="hidden" name="hdnIdEvento" value="<%=evento.getId()%>"/>
              </td>
            </tr>
            <tr>
              <td>
                Fecha:
              </td>
              <td>
                <%String[] valoresFecha = evento.getFecha().split("-");%>
                <select name="lstDias">
                  <%for(int dia=0; dia<=NUMERO_DIAS; dia++){%>
                    <option value="<%=dia%>" <%if(dia == Integer.parseInt(valoresFecha[0])){%>selected<%}%>> <%=(dia<10)?"0"+dia:dia%> </option>
                  <%}%>
                </select>
                <select name="lstMeses">
                  <%for(int mes=0; mes<MESES_ANIO.length; mes++){%>
                    <option value="<%=mes%>" <%if(MESES_ANIO[mes] == MESES_ANIO[Integer.parseInt(valoresFecha[1])]){%>selected<%}%>> <%=MESES_ANIO[mes]%> </option>
                    <%System.out.println("option value: "+mes+" if("+mes+"== "+valoresFecha[1]);%>  
                  <%}%>
                </select>
                <%String anio = Utileria.getAnioActual();
                int anioConvertido = Integer.parseInt(anio);%>
                <select name="lstAnios">
                  <%for(int a=(anioConvertido-1);a<(anioConvertido+1);a++){%>
                    <option value="<%=a%>" <%if(a == anioConvertido){%> selected <%}%>><%=a%></option>
                  <%}%>
                </select>
              </td>
            </tr>
            <tr>
              <td>
                Nombre:
              </td>
              <td>
                <input type="text" name="txtNombre" size="50" maxlength="50" value="<%=evento.getNombre()%>"/>
              </td>
            </tr>
            <tr>
              <td>
                Hora:
              </td>
              <td>
                <select name="lstHoras">
                  <%String horaEvento = evento.getHora();
                  String[] horaFormateada = horaEvento.split(":");
                  for(int horas=0; horas<=HORAS; horas++){%>
                    <option value="<%=horas%>"><%=((horas<10)?"0"+horas:horas)%></option>
                  <%}%>
                </select>
                <select name="lstSegundos">
                  <%for(int segundos=0; segundos<=SEGUNDOS; segundos++){%>
                    <option value="<%=segundos%>"><%=((segundos<10)?"0"+segundos:segundos)%></option>
                  <%}%>
                </select>
              </td>
            </tr>
            <tr>
              <td>
                Descripcion:
              </td>
              <td>
                <textarea name="txtAreaDescripcion" rows="5" cols="40"><%=evento.getDescripcion()%></textarea>
              </td>
            </tr>
            <tr>
              <td colspan="4" align="center">
                <h2>Direccion</h2>
                <input type="hidden" name="hdnIdDireccion" value="<%=evento.getDireccion().getId()%>"/>
              </td>
            </tr>
            <tr>
              <td>
                Calle:
              </td>
              <td>
                <input type="text" name="txtNombreCalle" size="40" maxlength="40" value="<%=evento.getDireccion().getNombreCalle()%>"/>
              </td>
            </tr>
            <tr>
              <td>
                Codigo Postal:
              </td>
              <td>
                <input type="text" name="txtCodigoPostal" size="10" maxlength="10" value="<%=evento.getDireccion().getCodigoPostal()%>"/>
              </td>
            </tr>
            <tr>
              <td>
                Colonia:
              </td>
              <td>
                <input type="text" name="txtNombreColonia" size="40" maxlength="40" value="<%=evento.getDireccion().getNombreColonia()%>"/>
              </td>
            </tr>
            <tr>
              <td>
                Ciudad:
              </td>
              <td>
                <input type="text" name="txtNombreCiudad" size="40" maxlength="40" value="<%=evento.getDireccion().getNombreCiudad()%>"/>
              </td>
            </tr>
            <tr>
              <td colspan="4" align="center">
                <h2>Participantes</h2>
              </td>
            </tr>
            <tr>
              <td>
                Nombre:
              </td>
              <td>
                <input type="text" name="txtNombreParticipante" size="40" maxlength="40"/>
              </td>
            </tr>
            <tr>
              <td>
                Email:
              </td>
              <td>
                <input type="text" name="txtEmailParticipante" size="40" maxlength="40"/>
              </td>
            </tr>
            <tr>
              <td>
                Empresa:
              </td>
              <td>
                <input type="text" name="txtEmpresaParticipante" size="40" maxlength="40"/>
              </td>
            </tr>
            <tr>
              <td>Tipo:</td>
              <td>
                <select name="lstTipoParticipantes">
                  <%ListadoTiposParticipante listadoTiposParticipante = new ListadoTiposParticipante();
                  for(Object objeto :listadoTiposParticipante.getContenido()){
                    TipoParticipante tipoParticipante = (TipoParticipante)objeto;
                    if(tipoParticipante.getId() == TipoParticipante.ORGANIZADOR)continue;%>
                    <option value="<%=tipoParticipante.getId()%>&<%=tipoParticipante.getNombre()%>"><%=tipoParticipante.getNombre()%></option>
                  <%}%>
                </select>
              </td>
            </tr>
            <tr>
              <td>
                <input type="button" onClick="eliminarParticipante(lstParticipantes);" name="btnEliminarParticipante" value="-"/>
              </td>
              <td>
                <input type="button" onClick="agregarParticipante(txtNombreParticipante,txtEmailParticipante,txtEmpresaParticipante,lstTipoParticipantes,lstParticipantes);" name="btnAgregarParticipante" value="+"/>
              </td>
            </tr>
            <tr>
              <td colspan="2">
                <select multiple name="lstParticipantes">
                  <%int indice = 0;
                  for(Object objeto: evento.getParticipantes()){
                    indice ++;
                    Participante participante = (Participante)objeto;%>
                      <option value="<%=participante.getNombre()%>&<%=participante.getEmail()%>&<%=participante.getEmpresa()%>&<%=participante.getTipo().getId()%>">
                  
                       <%=participante.getNombre()+"  ("+participante.getEmail()+")  "+participante.getEmpresa()%>
                  
                      </option>
                  <%}%>
                </select>
              </td>
            </tr>
            <tr>
              <td>
                <input type="button" onClick="confirmarCancelacion();" name="btnCancelar" value="Cancelar"/>
              </td>
              <td>
                <input type="submit" name="btnFinalizarModificacion" value="Finalizar Modificacion"/>
              </td>
            </tr>
          </form>
        </table>
      </center>
    </body>
  </html>
<%}%>