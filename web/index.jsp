<%-- 
    Document   : index
    Created on : 21-dic-2015, 15:33:55
    Author     : Jennifer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Acceder</title>
        <link rel="stylesheet" type="text/css" href="login.css" media="screen" />
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <SCRIPT language="Javascript" type="text/JavaScript">
             function validar(user,pass) {
                var usuario = user.value;
                var cont = pass.value; 
                if (alfanumerico(usuario)==0) {
                    alert ("Introduzca su login");
                    return false;
                }else 
                if (alfanumerico(cont)==0) {
                    alert ("Introduzca su clave");
                    return false;
                }else{
                    return true;
                }
            }

            function alfanumerico(txt) {
                // Devuelve 0 si la cadena esta vacia, 1 si es numerica 
                //o 2 si es alfanumerica
                var i;
                if (txt.length!=0) {
                    for (i=0;i<txt.length;i++){
                        if (txt.substr(i,1)<"0" || txt.substr(i,1)>"9") {
                            return 2;
                        }
                    }
                    return 1;
                }
                else
                    return 0;
            }
            
        </script>
        
    </head>
    <body>
        <div class="login">
  <div class="heading">
    <h2>Sign in</h2>
    <form action="Acceder" method="post" onsubmit="return validar(user,pass)">

      <div class="input-group input-group-lg">
        <span class="input-group-addon"><i class="fa fa-user"></i></span>
        <input type="text" class="form-control" name="user" placeholder="Username or email">
          </div>

        <div class="input-group input-group-lg">
          <span class="input-group-addon"><i class="fa fa-lock"></i></span>
          <input type="password" class="form-control" name="pass" placeholder="Password">
        </div>
        <br>
        <div class="input-group input-group-lg"> 
            <span class="input-group-addon"/>
             <select name="tipo" class="form-control" >
                <option class="option-control" value="Desarrollador"> Desarrollador </option>
                <option class="option-control" value="Administrador"> Administrador </option>
                <option class="option-control" value="Jefe de Proyecto"> Jefe Proyecto </option>
                
            </select>
        </div>
        <div> <% if(request.getParameter("msg")!=null) {
            request.getParameter("msg");
        }%> </div>
        <button type="submit" class="float">Login</button>
       </form>
 		
 </div>
    </body>
</html>
