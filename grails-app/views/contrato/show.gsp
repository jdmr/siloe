
<%@ page import="general.Contrato" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'contrato.label', default: 'Contrato')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-contrato" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-contrato" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list contrato">
			
				<g:if test="${contratoInstance?.folio}">
				<li class="fieldcontain">
					<span id="folio-label" class="property-label"><g:message code="contrato.folio.label" default="Folio" /></span>
					
						<span class="property-value" aria-labelledby="folio-label"><g:fieldValue bean="${contratoInstance}" field="folio"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${contratoInstance?.seguro}">
				<li class="fieldcontain">
					<span id="seguro-label" class="property-label"><g:message code="contrato.seguro.label" default="Seguro" /></span>
					
						<span class="property-value" aria-labelledby="seguro-label"><g:link controller="seguro" action="show" id="${contratoInstance?.seguro?.id}">${contratoInstance?.seguro?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${contratoInstance?.id}" />
					<g:link class="edit" action="edit" id="${contratoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
