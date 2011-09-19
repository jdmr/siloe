
<%@ page import="general.Almacen" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'almacen.label', default: 'Almacen')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-almacen" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="nuevo"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-almacen" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list almacen">
			
				<g:if test="${almacen?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="almacen.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${almacen}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${almacen?.direccion}">
				<li class="fieldcontain">
					<span id="direccion-label" class="property-label"><g:message code="almacen.direccion.label" default="Direccion" /></span>
					
						<span class="property-value" aria-labelledby="direccion-label"><g:fieldValue bean="${almacen}" field="direccion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${almacen?.telefono}">
				<li class="fieldcontain">
					<span id="telefono-label" class="property-label"><g:message code="almacen.telefono.label" default="Telefono" /></span>
					
						<span class="property-value" aria-labelledby="telefono-label"><g:fieldValue bean="${almacen}" field="telefono"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${almacen?.hospital}">
				<li class="fieldcontain">
					<span id="hospital-label" class="property-label"><g:message code="almacen.hospital.label" default="Hospital" /></span>
					
						<span class="property-value" aria-labelledby="hospital-label"><g:link controller="hospital" action="ver" id="${almacen?.hospital?.id}">${almacen?.hospital?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${almacen?.principal}">
				<li class="fieldcontain">
					<span id="principal-label" class="property-label"><g:message code="almacen.principal.label" default="Principal" /></span>
					
						<span class="property-value" aria-labelledby="principal-label"><g:formatBoolean boolean="${almacen?.principal}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${almacen?.id}" />
					<g:link class="edit" action="edita" id="${almacen?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="elimina" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
