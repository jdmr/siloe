<%@ page import="general.Usuario" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<title><g:message code="registroPaciente.aviso.registrado.titulo" /></title>
	</head>
	<body>
		<a href="#show-usuario" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="show-usuario" class="content scaffold-show" role="main">
			<h1><g:message code="registroPaciente.aviso.registrado.titulo" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
            <p><g:message code="registroPaciente.aviso.registrado.mensaje" /></p>
		</div>
	</body>
</html>
