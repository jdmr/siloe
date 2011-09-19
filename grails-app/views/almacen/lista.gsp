
<%@ page import="general.Almacen" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'almacen.label', default: 'Almacen')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-almacen" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="nuevo"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-almacen" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nombre" title="${message(code: 'almacen.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="direccion" title="${message(code: 'almacen.direccion.label', default: 'Direccion')}" />
					
						<g:sortableColumn property="telefono" title="${message(code: 'almacen.telefono.label', default: 'Telefono')}" />
					
						<th><g:message code="almacen.hospital.label" default="Hospital" /></th>
					
						<g:sortableColumn property="principal" title="${message(code: 'almacen.principal.label', default: 'Principal')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${almacenes}" status="i" var="almacenInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${almacenInstance.id}">${fieldValue(bean: almacenInstance, field: "nombre")}</g:link></td>
					
						<td>${fieldValue(bean: almacenInstance, field: "direccion")}</td>
					
						<td>${fieldValue(bean: almacenInstance, field: "telefono")}</td>
					
						<td>${fieldValue(bean: almacenInstance, field: "hospital")}</td>
					
						<td><g:formatBoolean boolean="${almacenInstance.principal}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${totalDeAlmacenes}" />
			</div>
		</div>
	</body>
</html>
