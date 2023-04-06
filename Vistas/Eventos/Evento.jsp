<%@page info="AplicacionCalendario" contentType="text/html" session="true" import="com.administradorcalendario.www.eventos.Evento, com.administradorcalendario.www.direcciones.Direccion, com.administradorcalendario.www.participantes.Participante, com.administradorcalendario.www.participantes.TipoParticipante"%>


<!--%ConectorEventos conectorEventos = new ConectorEventos();
Evento evento = new Evento();
evento.setId(28);
evento = conectorEventos.getEvento(evento);%-->  <!--Esto es una mala practica-->

<%if(session.getAttribute("usuario")== null){%>
  <jsp:forward page="/Vistas/Usuarios/Login.jsp"/>
<%}else{%>
  <%Evento evento = null;
  if(request.getAttribute("evento")!= null){
    evento = (Evento)request.getAttribute("evento");
  }%>

  <html>
    <head>
      <title>Informacion General</title>
    </head>
    <body>
      <center>
        <h1>Informacion General.</h1>
      </center>
      <center>
        <table width="30%" border="1">
          <tr>
            <td colspan="2" align="center">
              <h2>Evento.</h2>
            </td>
          </tr>
          <tr>
            <th>Fecha:</th>
            <td><%=evento.getFecha()%></td>
          </tr>
          <tr>
            <th>Nombre:</th>
            <td><%=evento.getNombre()%></td>
          </tr>
          <tr>
            <th>Hora:</th>
            <td><%=evento.getHora()%></td>
          </tr>
          <tr>
            <th>Descripcion:</th>
            <td><%=evento.getDescripcion()%></td>
          </tr>
          <tr>
            <td colspan="2" align="center">
              <h2>Direccion.</h2>
            </td>
          </tr>
          <tr>
            <th>Calle:</th>
            <td><%=evento.getDireccion().getNombreCalle()%></td>
          </tr>
          <tr>
            <th>Codigo Postal.</th>
            <td><%=evento.getDireccion().getCodigoPostal()%></td>
          </tr>
          <tr>
            <th>Colonia.</th>
            <td><%=evento.getDireccion().getNombreColonia()%></td>
          </tr>
          <tr>
            <th>Ciudad</th>
            <td><%=evento.getDireccion().getNombreCiudad()%></td>
          </tr>
          <tr>
            <td colspan="2" align="center">
              <h2>Participantes.</h2>
            </td>
          </tr>
          <%for(Object objeto: evento.getParticipantes()){
            Participante participante = (Participante)objeto;%>
            <tr>
              <th>Nombre:</th>
              <td><%=participante.getNombre()%></td>
            </tr>
            <tr>
              <th>Email:</th>
              <td><%=participante.getEmail()%></td>
            </tr>
            <tr>
              <th>Empresa:</th>
              <td><%=participante.getEmpresa()%></td>
            </tr>
            <tr>
              <th>Tipo:</th>
              <td><%=participante.getTipo().getNombre()%></td>
            </tr>
            <tr bgcolor="0465ad">
              <td colspan="2"></td>
            </tr>
          <%}%>
        </table>
      </center>
    </body>
  </html>
<%}%>  