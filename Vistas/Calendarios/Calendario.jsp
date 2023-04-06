<%@page info="AplicacionCalendario" contentType="text/html" session="true" import="com.administradorcalendario.www.calendarios.Calendario, com.administradorcalendario.www.eventos.Evento, com.administradorcalendario.www.usuarios.Usuario, com.administradorcalendario.www.participantes.Participante"%>
  
<%if(session.getAttribute("usuario") == null){%>
    <jsp:forward page="/Vistas/Usuarios/Login.jsp"/>
<%}else{%>
  
  <%String mensajeEventoAgregado = null;
    if(request.getAttribute("lblEventoAgregado")!= null){
      mensajeEventoAgregado = (String)request.getAttribute("lblEventoAgregado");
    }%>
  
  <%int idEvento = -1;
    if(request.getAttribute("idEvento") != null){
      idEvento = (Integer)request.getAttribute("idEvento");
    }%>
  
  <%String mensajeEventoEliminado = null;
    if(request.getAttribute("lblEventoEliminado") != null){
      mensajeEventoEliminado = (String)request.getAttribute("lblEventoEliminado");
    }%>
  
  <%String mensajeEventoModificado = null;
    if(request.getAttribute("lblEventoModificado") != null){
      mensajeEventoModificado = (String)request.getAttribute("lblEventoModificado");
    }%>
  
    <!--%int idEventoModificado = -1;
    if(request.getAttribute("idEventoModificado") != null){
      idEventoModificado = (Integer)request.getAttribute("idEventoModificado");
    }%-->
  
  <html>
    <head>
      <title>Calendario</title>
      <script language="javascript" src="/AdministradorCalendario/Vistas/Eventos/Calendarios.js">
      </script>
    </head>
    <body>
      <table width="30%" align="right">
        <form name="frmLogout" method="POST" action="/AdministradorCalendario/usuarios/Logout">
          <tr>
            <td colspan="3" align="right">
              <input type="submit" name="btnLogout" value="Logout"/>
            </td>
          </tr>
        </form>
        <tr>
          <td colspan="2">
            <font color="green">
              <h3>Bienvenido:</h3>
            </font>
          </td>
          <td>
            <%Usuario usuarioSesion = (Usuario)session.getAttribute("usuario");%>
            <font color="green">
              <h3><%=usuarioSesion.getAlias()%></h3>
            </font>
          </td>
        </tr>
      </table>
      <br/><br/><br/><br/>
      <center>
        <h1>Calendario</h1>
      </center>
      <center>
        <h2>Eventos</h2>
      </center>
      <center>
      <%if(mensajeEventoAgregado != null){%>
          <tr>
            <td colspan="8" align="center">
              <font color="green">
                <h3><%=mensajeEventoAgregado%></h3>
              </font>
            </td>
          </tr>
      <%}else{
          if(mensajeEventoEliminado != null){%>
            <tr>
              <td colspan="8" align="center">
                <font color="green">
                  <h3><%=mensajeEventoEliminado%></h3>
                </font>
              </td>
            </tr>
          <%}else{
              if(mensajeEventoModificado != null){%>
                <tr>
                  <td colspan="8" align="center">
                    <font color="green">
                      <h3><%=mensajeEventoModificado%></h3>
                    </font>
                  </td>
                </tr>
              <%}%>
          <%}%>
      <%}%>
      </center>
      <center>
        <table width="100%" border="1">
          <tr>
            <td colspan="9" align="right">
              <form name="frmAgregar" method="POST" action="/AdministradorCalendario/eventos/ServicioRegistrarEvento">
                <input type="submit" name="btnAgregar" value="Agregar Nuevo"/>
              </form>
            </td>
          </tr>
          <tr bgcolor="0465ad">
            <th>
              <font color="white">Fecha</font>
            </th>
            <th>
              <font color="white">Hora</font>
            </th>
            <th>
              <font color="white">Descripcion</font>
            </th>
            <th>
              <font color="white">Participantes</font>
            </th>
            <th>
              <font color="white">Direccion</font>
            </th>
            <th colspan="9">
              <font color="white">Acciones</font>
            </th>
          </tr>
          <%Usuario usuario = (Usuario)session.getAttribute("usuario");
          Calendario calendario = Calendario.getCalendario(usuario);
          System.out.println("Calendario: "+calendario.getId()+" "+calendario.getFechaCreacion()+" "+calendario.getNombre());
          String colorRenglonNuevoEvento = "#FFFF00";
          for(int i=0; i<calendario.getEventos().size(); i++){
            System.out.println("Eventos calendario: "+calendario.getEventos().size());
            Evento evento = (Evento)calendario.getEventos().get(i);%>
            <%if(evento.getId() == idEvento){%>
              <tr bgcolor="<%=colorRenglonNuevoEvento%>">
            <%}else{%>
              <%if((i % 2)==0){%>
                  <tr bgcolor="#299df7">
              <%}else{%>
                  <tr bgcolor="a8b1b8">
              <%}%>
            <%}%>
              <td><%=evento.getFecha()%></td>
              <td><%=evento.getHora()%></td>
              <td><%=evento.getDescripcion()%></td>
              <td>
                <%for(Object object: evento.getParticipantes()){
                    System.out.println("Participantes: "+evento.getParticipantes().size());
                    Participante participante = (Participante)object;%>
                    <ul>
                      <li>Nombre:&nbsp<%=participante.getNombre()%></li>
                      <li>Email:&nbsp<%=participante.getEmail()%></li>
                      <li>Empresa:&nbsp<%=participante.getEmpresa()%></li>
                      <li>Tipo:&nbsp<%=participante.getTipo().getNombre()%></li>
                    </ul>
                <%}%>
              </td>
              <td>
                <ul>
                  <li>Calle:&nbsp<%=evento.getDireccion().getNombreCalle()%></li>
                  <li>Codigo Postal:&nbsp<%=new Integer(evento.getDireccion().getCodigoPostal()).toString()%></li>
                  <li>Colonia:&nbsp<%=evento.getDireccion().getNombreColonia()%></li>
                  <li>Ciudad:&nbsp<%=evento.getDireccion().getNombreCiudad()%></li>
                </ul>
                <%=evento.getDireccion().toString()%>
              </td>
              <td>
                <form name="frmEliminar" method="POST" action="/AdministradorCalendario/eventos/ServicioEliminarEvento" onSubmit="return confirmarEliminacion();">
                  <input type="hidden" name="hdnIdEvento" value="<%=evento.getId()%>"/>
                  <input type="submit" name="btnEliminar" value="Eliminar"/>
                </form>
              </td>
              <td>
                <!--form name="frmConsultar" method="GET" action="/AdministradorCalendario/eventos/ServicioConsultarEvento"-->
                  <!--input type="hidden" name="hdnIdEvento" value="<%=evento.getId()%>"-->
                  <!--input type="submit" name="btnConsultar" value="Consultar"/-->
                  <input type="button" name="btnConsultar" value="Consultar" onClick="javascript:window.open('/AdministradorCalendario/eventos/ServicioConsultarEvento?hdnIdEvento=<%=evento.getId()%>&btnConsultar=Consultar','Informacion General Evento','location=0,toolbar=0,menubar=0,resizable=0,scrollbars=1,width=400,height=400,left='+((screen.width - 400)/2)+',top='+((screen.height - 400)/2));"/>
                <!--/form-->
              </td>
               <td>
                <form name="frmModificar" method="POST" action="/AdministradorCalendario/eventos/ServicioModificarEvento">
                  <input type="hidden" name="hdnIdEvento" value="<%=evento.getId()%>"/>
                  <input type="submit" name="btnModificar" value="Modificar"/>
                </form>
              </td>
            </tr>
          <%}%>
        </table>
      </center>
    </body>
  </html>
<%}%>