<%@page info="AdministradorCalendario" contentType="text/html" session="true"%>

<%session.removeAttribute("usuario");%>
<html>
  <head>
    <title>Login</title>
  </head>
  <body>
    <center>
      <h1>Login</h1>
    </center>
    
    <center>
      <table width="30%" border="1">
        <form name="frmLogin" method="POST" action="/AdministradorCalendario/usuarios/ServicioAutenticacion">
          <tr>
            <td>
              Alias:
            </td>
            <td>
              <input type="text" name="txtAlias" size="40" maxlength="40"/> <!--required="true"/-->
            </td>
          </tr>
          <tr>
            <td>
              Password:
            </td>
            <td>
              <input type="password" name="txtPassword" size="40" maxlength="40"/> <!--required="true"/-->
            </td>
          </tr>
          <tr>
            <td colspan="2" align="center">
              <input type="submit" name="btnAutenticar" value="Autenticar"/>
            </td>
          </tr>
          <tr>
            <%if(request.getAttribute("lblUsuarioInvalido") != null){%>
              <h3>
                <font color="red">
                  <%=request.getAttribute("lblUsuarioInvalido")%>
                </font>
              </h3>
            <%}else{
              if(request.getAttribute("lblErrorAcceso") != null){%>
                <h3>
                  <font color="red">
                    <%=request.getAttribute("lblErrorAcceso")%>
                  </font>
                </h3>
              <%}%>
            <%}%>
          </tr>
        </form>
      </table>
    </center>
  </body>
</html>