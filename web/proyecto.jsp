<%@page import="Business.Proyecto"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Nuevo proyecto</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.5 -->
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
        <!-- daterange picker -->
        <link rel="stylesheet" href="plugins/daterangepicker/daterangepicker-bs3.css">
        <!-- iCheck for checkboxes and radio inputs -->
        <link rel="stylesheet" href="plugins/iCheck/all.css">
        <!-- Bootstrap Color Picker -->
        <link rel="stylesheet" href="plugins/colorpicker/bootstrap-colorpicker.min.css">
        <!-- Bootstrap time Picker -->
        <link rel="stylesheet" href="plugins/timepicker/bootstrap-timepicker.min.css">
        <!-- Select2 -->
        <link rel="stylesheet" href="plugins/select2/select2.min.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
        <!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
        <link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
        <script type="text/javascript">
            function validar() {
                if (validarNombre() && validarFecha())
                    return true;
                else {
                    return false;
                }
            }

            function validarNombre() {
                var n = document.proyecto.nombre.value;
                if (n === "" || n === " ") {
                    window.alert("Error en el nombre del proyecto")
                    return false;
                } else {
                    return true;
                }
            }

            function validarFecha() {
                var fecha = document.proyecto.fechaInicioyFin.value;
                if (fecha === "" || fecha === " " || fecha.length < 23) {
                    window.alert("Fecha err�nea");
                    return false;
                } else {
                    return true;
                }
            }
        </script>
    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">

            <%@include file="navBar.html" %>
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Proyecto
                        <small>Crear un nuevo proyecto</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li><a href="#">Proyectos</a></li>
                        <li class="active">Nuevo Proyecto</li>
                    </ol>
                </section>

                <!-- Main project content -->
                <section class="content">

                    <!-- SELECT2 EXAMPLE -->
                    <div class="box box-default">
                        <div class="box-header with-border">
                            <h3 class="box-title">Nuevo proyecto</h3>
                        </div><!-- /.box-header -->
                        <div class="box-body">
                            <div class="row">
                                <div class="col-md-6">
                                    <%
                                        String usuario = (String) session.getAttribute("usuario");
                                        boolean actualizar = (Boolean) session.getAttribute("actualizar");
                                        int idProyecto;
                                        if (!actualizar) {
                                    %>
                                    <form role="form" action="Proyectos?proyecto=crearProyecto&usuario=<%= usuario%>" name="proyecto" value="crearProyecto" method="post">
                                        <div class="box-body">
                                            <div class="form-group">
                                                <label for="nombreProyecto">Nombre del proyecto</label>
                                                <input type="text" class="form-control" id="nombreProyecto" placeholder="Introduzca el nombre del proyecto" name="nombre">
                                            </div>
                                            <!-- Date range -->
                                            <div class="form-group">
                                                <label>Fecha del proyecto:</label>
                                                <div class="input-group">
                                                    <div class="input-group-addon">
                                                        <i class="fa fa-calendar"></i>
                                                    </div>
                                                    <input type="text" class="form-control pull-right" id="reservation" placeholder="Pulse para introducir la fecha de inicio y la fecha de fin" name="fechaInicioyFin">
                                                </div><!-- /.input group -->
                                            </div><!-- /.form group -->
                                            <div class="form-group">
                                                <label>Estado</label>
                                                <select class="form-control select2" style="width: 100%;" name="estado">
                                                    <option selected="selected" value="S">Sin comenzar</option>
                                                    <option disabled="disabled" value="E">En curso</option>
                                                    <option disabled="disabled" value="F">Finalizado</option>
                                                    <option disabled="disabled" value="C">Cerrado</option>
                                                </select>
                                            </div><!-- /.form-group -->
                                        </div><!-- /.box-body -->
                                        <div class="box-footer">
                                            <button type="submit" class="btn btn-primary" name="crearProyecto" value="crearProyecto" onclick="return validar()">Crear Proyecto</button>
                                            <a href="vistaProyectos.jsp"><button type="button" class="btn btn-default" name="proyecto" value="cancelar">Cancelar</button></a>
                                        </div>

                                    </form>
                                    <% } else {
                                        idProyecto = (Integer) session.getAttribute("idProyecto");
                                        Proyecto p = (Proyecto) session.getAttribute("proyecto");
                                    %> 
                                    <form role="form" action="Proyectos?proyecto=actualizarProyecto&usuario=<%= usuario%>&idProyecto=<%= idProyecto%>" name="proyecto" value="actualizarProyecto" method="post">
                                        <div class="box-body">
                                            <div class="form-group">
                                                <label for="nombreProyecto">Nombre del proyecto</label>
                                                <input type="text" class="form-control" id="nombreProyecto" name="nombre" value="<%= p.getNombre()%>">
                                            </div>
                                            <!-- Date range -->
                                            <div class="form-group">
                                                <label>Fecha del proyecto:</label>
                                                <div class="input-group">
                                                    <div class="input-group-addon">
                                                        <i class="fa fa-calendar"></i>
                                                    </div>
                                                    <input type="text" class="form-control pull-right" id="reservation" name="fechaInicioyFin" value="<%= p.getFechaInicio()%> - <%= p.getFechaFin()%>">
                                                </div><!-- /.input group -->
                                            </div><!-- /.form group -->
                                            <div class="form-group">
                                                <label>Estado</label>
                                                <select class="form-control select2" style="width: 100%;" name="estado">
                                                    <option 
                                                        <% if (p.getEstado() == 'S') {%>
                                                        selected="selected"
                                                        <%}%>
                                                        value="S">Sin comenzar</option>
                                                    <option 
                                                        <% if (p.getEstado() == 'E') {%>
                                                        selected="selected"
                                                        <%}%>
                                                        value="E">En curso</option>
                                                    <option 
                                                        <% if (p.getEstado() == 'F') {%>
                                                        selected="selected"
                                                        <%}%>
                                                        value="F">Finalizado</option>
                                                    <option 
                                                        <% if (p.getEstado() == 'C') {%>
                                                        selected="selected"
                                                        <%}%>
                                                        value="C">Cerrado</option>
                                                </select>
                                            </div><!-- /.form-group -->
                                        </div><!-- /.box-body -->
                                        <div class="box-footer">
                                            <button type="submit" class="btn btn-primary" name="actualizarProyecto" value="actualizarProyecto" onclick="return validar()">Actualizar Proyecto</button>
                                            <a href="vistaProyectos.jsp"><button type="button" class="btn btn-default" name="proyecto" value="cancelar">Cancelar</button></a>
                                        </div>
                                    </form>
                                    <% }%>
                                </div><!-- /.col -->
                            </div><!-- /.row -->
                        </div><!-- /.box-body -->
                    </div><!-- /.box -->

                </section><!-- /.content -->
            </div><!-- /.content-wrapper -->

            <%@include file="footer.html" %>
            <%@include file="settings.html" %>
        </div><!-- ./wrapper -->

        <!-- jQuery 2.1.4 -->
        <script src="plugins/jQuery/jQuery-2.1.4.min.js"></script>
        <!-- Bootstrap 3.3.5 -->
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <!-- Slimscroll -->
        <script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>
        <!-- Select2 -->
        <script src="plugins/select2/select2.full.min.js"></script>
        <!-- date-range-picker -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.2/moment.min.js"></script>
        <script src="plugins/daterangepicker/daterangepicker.js"></script>
        <!-- FastClick -->
        <script src="plugins/fastclick/fastclick.min.js"></script>
        <!-- AdminLTE App -->
        <script src="dist/js/app.min.js"></script>
        <!-- AdminLTE for demo purposes -->
        <script src="dist/js/demo.js"></script>

        <!-- Page script -->
        <script>
                                                $(function () {
                                                    //Initialize Select2 Elements
                                                    $(".select2").select2();

                                                    //Date range picker
                                                    $('#reservation').daterangepicker();
                                                });
        </script>
    </body>
</html>
