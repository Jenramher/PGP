<%-- 
    Document   : calendarioD
    Created on : 29-dic-2015, 10:19:44
    Author     : gil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="CalendarioD" method="post">
            <label>Login del usuario</label>
            <input type="text" name="login">
            </br>
            <label>Tipo de evento</label>
            <select name="tipo">
                <option value="V"> Vacaciones </option>
                <option value="T"> Tarea personal </option>
            </select>
            </br>
            <label>Tipo de tarea</label>
            <select name="tipoT">
                <option value="TU"> Trato con Usuarios </option>
                <option value="RE"> Reuniones Externas </option>
                <option value="RI"> Reuniones Internas </option>
                <option value="LD"> Lectura de Documentación </option>
                <option value="RV"> Revisión de documentación </option>
                <option value="ED"> Elaboración de documentación </option>
                <option value="DP"> Desarrollo de Programas </option>
                <option value="VP"> Verificación de Programas </option>
                <option value="FU"> Formación de Usuarios </option>
                <option value="FA"> Formación de Otras Actividades </option>
            </select>
            </br>
            <!--Añadir comprobador de que la fecha de Inicio va antes que la del Fin-->
            <label>Fecha de inicio</label>
            <input type="text" name="fechaI" data-mask>
            </br>
            <label>Fecha de fin</label>
            <input type="text" name="fechaF" data-mask>
            </br>
            <button type="submit" >Añadir</button>
        </form>
    </body>
</html>
