<%@ page import="general.Almacen" %>



<div class="fieldcontain ${hasErrors(bean: almacenInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="almacen.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" maxlength="128" value="${almacenInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: almacenInstance, field: 'direccion', 'error')} required">
	<label for="direccion">
		<g:message code="almacen.direccion.label" default="Direccion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="direccion" required="" value="${almacenInstance?.direccion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: almacenInstance, field: 'telefono', 'error')} required">
	<label for="telefono">
		<g:message code="almacen.telefono.label" default="Telefono" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="telefono" required="" value="${almacenInstance?.telefono}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: almacenInstance, field: 'hospital', 'error')} required">
	<label for="hospital">
		<g:message code="almacen.hospital.label" default="Hospital" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="hospital" name="hospital.id" from="${general.Hospital.list()}" optionKey="id" required="" value="${almacenInstance?.hospital?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: almacenInstance, field: 'principal', 'error')} ">
	<label for="principal">
		<g:message code="almacen.principal.label" default="Principal" />
		
	</label>
	<g:checkBox name="principal" value="${almacenInstance?.principal}" />
</div>

