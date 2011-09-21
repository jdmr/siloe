<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta name="layout" content="main">
    <title>Doctor</title>
  </head>
  <body>
    <h1>Registro</h1>

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
      <g:form action="guardarRegistro" >
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

  </body>
</html>
