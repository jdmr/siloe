<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta name="layout" content="main">
    <title>Hospital - Registro de Doctor</title>
    <r:require module="jquery-ui" />
  </head>
  <body>
    <h1>Registro de Doctor</h1>

    <div id="create-doctor" class="content scaffold-create" role="main">
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>
      <g:hasErrors bean="${doctor}">
        <ul class="errors" role="alert">
          <g:eachError bean="${doctor}" var="error">
            <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
          </g:eachError>
        </ul>
      </g:hasErrors>
      <p><a id="nuevoLink" href="#nuevo">Nuevo</a> | <a id="existenteLink" href="#existente">Existente</a></p>
      <div id="doctorNuevoDiv">
        <g:form action="guardaDoctor" >
          <fieldset class="form">
            <div class="fieldcontain ${hasErrors(bean: doctor, field: 'username', 'error')} required">
              <label for="username">
                <g:message code="usuario.username.label" default="Username" />
                <span class="required-indicator">*</span>
              </label>
              <g:textField name="username" required="" value="${doctor?.username}"/>
            </div>

            <div class="fieldcontain ${hasErrors(bean: doctor, field: 'password', 'error')} required">
              <label for="password">
                <g:message code="usuario.password.label" default="Password" />
                <span class="required-indicator">*</span>
              </label>
              <g:passwordField name="password" required="" value="${doctor?.password}"/>
            </div>
            <div class="fieldcontain ${hasErrors(bean: doctor, field: 'hospital', 'error')} required">
              <label for="hospital">
                <g:message code="hospital.label" default="Hospital" />
                <span class="required-indicator">*</span>
              </label>
              <g:select name="hospital.id"
                        from="${general.Hospital.list()}"
                        value="${doctor?.hospital?.id}"
                        optionKey="id" />
            </div>
          </fieldset>
          <fieldset class="buttons">
            <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
          </fieldset>
        </g:form>
      </div>
      <div id="doctorExistenteDiv" style="display:none">
        <g:form action="asignaDoctor" >
          <g:hiddenField name="id" value="" />
          <fieldset class="form">
            <div class="fieldcontain ${hasErrors(bean: doctor, field: 'username', 'error')} required">
              <label for="username2">
                <g:message code="usuario.username.label" default="Username" />
              </label>
              <g:textField name="username2" value=""/>
            </div>
          </fieldset>
          <fieldset class="buttons">
            <g:submitButton name="asignaDoctorBtn" class="save" value="${message(code: 'hospital.asignaDoctor', default: 'Asigna Doctor')}" />
          </fieldset>
        </g:form>
      </div>
    </div>
    <r:script>
      $(document).ready(function() {
        $("a#nuevoLink").click(function(e) {
          e.preventDefault();
          
          $("#doctorExistenteDiv").slideUp();
          $("#doctorNuevoDiv").slideDown();
          $("input#username").focus();
        });
        $("a#existenteLink").click(function(e) {
          e.preventDefault();
          
          $("#doctorNuevoDiv").slideUp();
          $("#doctorExistenteDiv").slideDown();
          $("input#username2").focus();
        });
        $("input#username2").autocomplete({
          source: '${createLink(action:'buscarDoctores')}',
          select: function(event, ui) {
            $("input#id").val(ui.item.id);
          }
        });

        $("input#username").focus();
      });
    </r:script>
  </body>
</html>
