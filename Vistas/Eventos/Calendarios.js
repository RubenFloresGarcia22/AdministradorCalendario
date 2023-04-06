function agregarParticipante(txtNombreParticipante,txtEmailParticipante,txtEmpresaParticipante,lstTipoParticipantes,lstParticipantes){
  //alert(lstTipoParticipantes.options[lstTipoParticipantes.selectedIndex].value);
  if((isVacio(txtNombreParticipante) || isSoloEspacios(txtNombreParticipante))){
    alert('Debe ingresar un nombre valido!');
    txtNombreParticipante.focus();
    return false;  //stop
  }else{
    //var expresionRegular = /^[a-zA-Z]+((\. | )[a-zA-Z]+)*$/;
    var expresionRegular = /^[a-zA-Z]+((\. | )[a-zA-Z]+)(( )[a-zA-Z]+)*$/;
    if(!expresionRegular.test(txtNombreParticipante.value)){
      alert('Nombre Incorrecto');
      txtNombreParticipante.focus();
      return false; //stop
    }
  }
  if((isVacio(txtEmailParticipante) || isSoloEspacios(txtEmailParticipante))){
    alert('Debe ingresar un email valido!');
    txtEmailParticipante.focus();
    return false;
  }else{
    var expresionRegular = /^[a-zA-Z0-9_]([a-zA-Z0-9_.$]*)@[a-zA-Z0-9]((.)[a-zA-Z-0-9]+)*$/;
    //var expresionRegular = /^[a-zA-Z0-9_]([a-zA-Z0-9_.$]*)@[a-zA-Z0-9]+(\.[a-zA-Z-0-9]+)+$/
    if(!expresionRegular.test(txtEmailParticipante.value)){
      alert('Email incorrecto!');
      txtEmailParticipante.focus();
      return false;
    }
  }
  if((isVacio(txtEmpresaParticipante) || isSoloEspacios(txtEmpresaParticipante))){
    alert('Debe ingresar una empresa valida!');
    txtEmpresaParticipante.focus();
    return false;
  }else{
    var expresionRegular = /^[a-zA-Z0-9@]+(( )[a-zA-Z0-9#@$]+)*$/;
    if(!expresionRegular.test(txtEmpresaParticipante.value)){
      alert('Empresa incorrecta!');
      txtEmpresaParticipante.focus();
      return false;
    }
  }
  var valoresTipoParticipantes = lstTipoParticipantes.options[lstTipoParticipantes.selectedIndex].value;
  var infoSeparada = valoresTipoParticipantes.split('&');
  lstParticipantes.options[lstParticipantes.length] = new Option(txtNombreParticipante.value+'  '+'('+txtEmailParticipante.value+')   '+txtEmpresaParticipante.value+' ('+infoSeparada[1]+')', txtNombreParticipante.value+'&'+txtEmailParticipante.value+'&'+txtEmpresaParticipante.value+'&'+infoSeparada[0]);
  
  txtNombreParticipante.value = '';
  txtEmailParticipante.value = '';
  txtEmpresaParticipante.value = '';
  
}

function eliminarParticipante(lstParticipantes){
  var indiceParticipanteSeleccionado = lstParticipantes.selectedIndex;
  if(indiceParticipanteSeleccionado != -1){
    for(var pe=(lstParticipantes.length-1); pe>=0; pe--){
      if(lstParticipantes[pe].selected){//== true
        lstParticipantes.options[pe] = null;
      }
    }
  }
  if(lstParticipantes.length >0){
    lstParticipantes.selectedIndex =(((indiceParticipanteSeleccionado -1)<=0)?0:(indiceParticipanteSeleccionado-1));
  }
}

function seleccionarParticipantes(lstParticipantes){
  for(var indice=0; indice<lstParticipantes.length; indice++){
    lstParticipantes[indice].selected = true;
  }
}

function validarFormulario(frmRegistrarEvento){
  if(!validarCamposVacios(frmRegistrarEvento)){
    return false;
  }
  var expresionRegular= /^[a-zA-Z0-9@][a-zA-Z0-9@_ \-]*[a-zA-Z0-9]$/;
  if(!expresionRegular.test(frmRegistrarEvento.txtNombre.value)){
    alert('Debe ingresar un nombre de evento valido.');
    frmRegistrarEvento.txtNombre.focus();
    return false;
  }
  
  var anio = parseInt(frmRegistrarEvento.lstAnios.options[frmRegistrarEvento.lstAnios.selectedIndex].value);
  var mes = parseInt(frmRegistrarEvento.lstMeses.options[frmRegistrarEvento.lstMeses.selectedIndex].value);
  var dia = parseInt(frmRegistrarEvento.lstDias.options[frmRegistrarEvento.lstDias.selectedIndex].value);
  
  alert('Datos: '+dia+' '+mes+' '+anio);
  
  if((anio % 4 == 0) && (anio % 100 != 0) || (anio % 400 == 0)){
    if(mes == 2){
      if(dia > 29){
        alert('Fecha incorrecta (anio bisiesto)');
        frmRegistrarEvento.lstDias.focus();
        return false;
      }
    }
  }else{
    if(mes == 2){
      if(dia > 28){
        alert('Fecha incorrecta! (Febrero tiene solo 28 dias)');
        frmRegistrarEvento.lstDias.focus();
        return false;
      }
    }
    if(mes == 4 || mes ==6 || mes == 9 || mes == 10){
      if(dia >30){
        alert('Fecha incorrecta! (El mes seleccionado solo tiene 30 dias.)');
        frmRegistrarEvento.lstDias.focus();
        return false;
      }
    }
  }
  
  if(!expresionRegular.test(frmRegistrarEvento.txtAreaDescripcion.value)){
    alert('Debe ingresar una descripcion de evento valida.');
    frmRegistrarEvento.txtAreaDescripcion.focus();
    return false;
  }
  if(!expresionRegular.test(frmRegistrarEvento.txtNombreCalle.value)){
    alert('Debe ingresar un nombre de calle valido.');
    frmRegistrarEvento.txtNombreCalle.focus();
    return false;
  }
  var expresionRegularCodigoPostal = /^[0-9][0-9][0-9][0-9][0-9][0-9]$/;
  if(!expresionRegularCodigoPostal.test(frmRegistrarEvento.txtCodigoPostal.value)){
    alert('Debe ingresar un codigo postal valido.');
    frmRegistrarEvento.txtCodigoPostal.focus();
    return false;
  }
  if(!expresionRegular.test(frmRegistrarEvento.txtNombreColonia.value)){
    alert('Debe ingresar un nombre de colonia valido.');
    frmRegistrarEvento.txtNombreColonia.focus();
    return false;
  }
  if(!expresionRegular.test(frmRegistrarEvento.txtNombreCiudad.value)){
    alert('Debe ingresar un nombre de ciudad valido.');
    frmRegistrarEvento.txtNombreCiudad.focus();
    return false;
  }
  /*if(!expresionRegular.test(frmRegistrarEvento.txtNombreParticipante.value)){
    alert('Debe ingresar un nombre de participante valido.');
    frmRegistrarEvento.txtNombreParticipante.focus();
    return false;
  }
  if(!expresionRegular.test(frmRegistrarEvento.txtEmailParticipante.value)){
    alert('Debe ingresar un email valido.');
    frmRegistrarEvento.txtEmailParticipante.focus();
    return false;
  }
  if(!expresionRegular.test(frmRegistrarEvento.txtEmpresaParticipante.value)){
    alert('Debe ingresar un nombre de empresa valido.');
    frmRegistrarEvento.txtEmailParticipante.focus();
    return false;
  }*/
  
  /*if(!expresionRegular.test(frmRegistrarEvento.lstParticipantes.value)){
    alert('Debe ingresar participantes validos.');
    frmRegistrarEvento.lstParticipantes.focus();
    return false;
  }*/
  return true;
}

function validarCamposVacios(frmRegistrarEvento){  
  if(isVacio(frmRegistrarEvento.txtNombre)|| isSoloEspacios(frmRegistrarEvento.txtNombre)){
    alert('Debe ingresar un nombre para el evento.'); 
    frmRegistrarEvento.txtNombre.focus();
    return false;
  }
  if(isVacio(frmRegistrarEvento.txtAreaDescripcion)|| isSoloEspacios(frmRegistrarEvento.txtAreaDescripcion)){
    alert('Debe ingresar una descripcion para el evento.');
    frmRegistrarEvento.txtAreaDescripcion.focus();
    return false;
  }
  if(isVacio(frmRegistrarEvento.txtNombreCalle) || isSoloEspacios(frmRegistrarEvento.txtNombreCalle)){
    alert('Debe ingresar el nombre de la calle.');
    frmRegistrarEvento.txtNombreCalle.focus();
    return false;
  }
  if(isVacio(frmRegistrarEvento.txtCodigoPostal) || isSoloEspacios(frmRegistrarEvento.txtCodigoPostal)){
    alert('Debe ingresar un codigo postal.');
    frmRegistrarEvento.txtCodigoPostal.focus();
    return false;
  }
  if(isVacio(frmRegistrarEvento.txtNombreColonia) || isSoloEspacios(frmRegistrarEvento.txtNombreColonia)){
    alert('Debe ingresar un nombre de colonia.');
    frmRegistrarEvento.txtNombreColonia.focus();
    return false;
  }
  if(isVacio(frmRegistrarEvento.txtNombreCiudad) || isSoloEspacios(frmRegistrarEvento.txtNombreCiudad)){
    alert('Debe ingresar un nombre de ciudad.');
    frmRegistrarEvento.txtNombreCiudad.focus();
    return false;
  }
  /*if(isVacio(frmRegistrarEvento.txtNombreParticipante) || isSoloEspacios(frmRegistrarEvento.txtNombreParticipante)){
    alert('Debe ingresar un nombre de participante.');
    frmRegistrarEvento.txtNombreParticipante.focus();
    return false;
  }
  if(isVacio(frmRegistrarEvento.txtEmailParticipante) || isSoloEspacios(frmRegistrarEvento.txtEmailParticipante)){
    alert('Debe ingresar un email.');
    frmRegistrarEvento.txtEmailParticipante.focus();
    return false;
  }
  if(isVacio(frmRegistrarEvento.txtEmpresaParticipante) || isSoloEspacios(frmRegistrarEvento.txtEmpresaParticipante)){
    alert('Debe ingresar un nombre de empresa.');
    frmRegistrarEvento.txtEmpresaParticipante.focus();
    return false;
  }*/
  if(isVacio(frmRegistrarEvento.lstParticipantes) || isSoloEspacios(frmRegistrarEvento.lstParticipantes)){
    alert('Debe ingresar participantes a la lista.');
    frmRegistrarEvento.txtNombreParticipante.focus();
    frmRegistrarEvento.txtEmailParticipante.focus();
    frmRegistrarEvento.txtEmpresaParticipante.focus();
    return false;
  }
  return true;
}

function isVacio(campo){
  if(campo.value == ""){
    return true;
  }else{
    return false;
  }
}

function isSoloEspacios(campo){
  if(campo.value.trim() == ""){
    return true;
  }else{
    return false;
  }
}

function confirmarEliminacion(){
  var respuesta = false;
  var mensaje = 'Estas seguro que desea eliminar este evento?';
  respuesta = confirm(mensaje);
  if(respuesta){
    alert('Evento eliminado con exito.'); 
    return respuesta;
  }else{
    return respueta;
  }
}

function confirmarCancelacion(){
  var respuesta = false;
  var mensaje = 'Estas seguro que deseas cancelar la modificacion?';
  respuesta = confirm(mensaje);
  if(respuesta){
    window.location.href="/AdministradorCalendario/Vistas/Calendarios/Calendario.jsp";
    return respuesta;
  }else{
    return respuesta;
  }
}