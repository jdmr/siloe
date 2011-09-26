<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta name="layout" content="main"/>
    <title>Doctores Pendientes de Autorizar</title>
  </head>
  <body>
    <h1>Doctores Pendientes de Autorizar</h1>

    <div id="list-hospital" class="content scaffold-list" role="main">
      <h1><g:message code="default.list.label" args="[entityName]" /></h1>
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>
      <table>
        <thead>
          <tr>

        <g:sortableColumn property="doctor" title="${message(code: 'doctorhospital.doctor.label', default: 'Doctor')}" />

        <g:sortableColumn property="hospital" title="${message(code: 'doctorhospital.hospital.label', default: 'Hospital')}" />

        <g:sortableColumn property="autorizado" title="${message(code: 'doctorhospital.autorizado.label', default: 'Autorizado')}" />
        <th>Accion</th>

        </tr>
        </thead>
        <tbody>
        <g:each in="${pendientes}" status="i" var="pendiente">
          <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

          <td>${pendiente.doctor.username}</td>

          <td>${fieldValue(bean: pendiente, field: "hospital")}</td>

          <td>${fieldValue(bean: pendiente, field: "autorizado")}</td>
          <td><g:link action="autorizar" id="${pendiente.id}">Autorizar</g:link> | <g:link action="rechazar" id="${pendiente.id}">Rechazar</g:link></td>

          </tr>
        </g:each>
        </tbody>
      </table>
      <div class="pagination">
        <g:paginate total="${totalDePendientes}" />
      </div>
    </div>
  </body>
</html>
