<%@ page import="general.Seguro" %>



<div class="fieldcontain ${hasErrors(bean: seguroInstance, field: 'contratos', 'error')} ">
	<label for="contratos">
		<g:message code="seguro.contratos.label" default="Contratos" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${seguroInstance?.contratos?}" var="c">
    <li><g:link controller="contrato" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="contrato" action="create" params="['seguro.id': seguroInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'contrato.label', default: 'Contrato')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: seguroInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="seguro.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${seguroInstance?.nombre}"/>
</div>

