<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
        <title><g:message code='registroPaciente.bienvenida.titulo' /></title>
        <r:require module="jquery"/>
	</head>
	<body>
		<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="body">
            <h1><g:message code='registroPaciente.bienvenida.titulo' /></h1>
            <p>hola</p>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${usuario}">
            <ul class="errors" role="alert">
                <g:eachError bean="${usuario}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form action="crea" >
                <fieldset class="form">
                    <div class="fieldcontain ${hasErrors(bean: usuario, field: 'username', 'error')} required">
                        <label for="username">
                            <g:message code="usuario.username.label" default="Username" />
                            <span class="required-indicator">*</span>
                        </label>
                        <g:textField name="username" required="" value="${usuario?.username}"/>
                    </div>

                    <div class="fieldcontain ${hasErrors(bean: usuario, field: 'password', 'error')} required">
                        <label for="password">
                            <g:message code="usuario.password.label" default="Password" />
                            <span class="required-indicator">*</span>
                        </label>
                        <g:passwordField name="password" required="" value="${usuario?.password}"/>
                    </div>

                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
		</div>
        <r:script>
            $(document).ready(function(){
                $("input#username").focus();
            });
        </r:script>
	</body>
</html>
