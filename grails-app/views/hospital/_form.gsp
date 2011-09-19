<%@ page import="general.Hospital" %>



<div class="fieldcontain ${hasErrors(bean: hospitalInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="hospital.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" maxlength="128" required="" value="${hospitalInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: hospitalInstance, field: 'direccion', 'error')} required">
	<label for="direccion">
		<g:message code="hospital.direccion.label" default="Direccion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="direccion" required="" value="${hospitalInstance?.direccion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: hospitalInstance, field: 'telefono', 'error')} required">
	<label for="telefono">
		<g:message code="hospital.telefono.label" default="Telefono" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="telefono" required="" value="${hospitalInstance?.telefono}"/>
</div>

