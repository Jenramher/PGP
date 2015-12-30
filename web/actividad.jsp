<%@page import="Business.Actividad"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Nueva actividad</title>
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
                if (validarDescripcion() && validarDE() && validarFecha()) {
                    return true;
                } else {
                    return false;

                }
            }

            function validarFecha() {
                var fecha = document.actividad.fechaInicioyFin.value;
                if (fecha === "" || fecha === " " || fecha.length < 23) {
                    window.alert("Fecha errónea");
                    return false;
                } else {
                    return true;
                }
            }

            function validarDescripcion() {
                var d = document.actividad.descripcion.value;
                if (d === "" || d === " ") {
                    window.alert("Error en la descripción");
                    return false;
                } else {
                    return true;
                }
            }

            function validarDE() {
                var dE = document.actividad.duracionEstimada.value;
                if (dE === "") {
                    window.alert("Duración estimada no puede estar vacío");
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
                        Actividades
                        <small>Crear una nueva actividad</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li><a href="vistaActividades.jsp">Actividades</a></li>
                        <li class="active">Nueva Actividad</li>
                    </ol>
                </section>

                <!-- Main project content -->
                <section class="content">

                    <!-- SELECT2 EXAMPLE -->
                    <div class="box box-default">
                        <div class="box-header with-border">
                            <h3 class="box-title">Nueva actividad</h3>
                        </div><!-- /.box-header -->
                        <div class="box-body">
                            <div class="row">
                                <div class="col-md-6">
                                    <%
                                        int idFase = (Integer) session.getAttribute("idFase");
                                        String usuario = (String) session.getAttribute("usuario");
                                        boolean actualizar = (Boolean) session.getAttribute("actualizar");
                                        int idActividad;
                                        if (!actualizar) {
                                    %>
                                    <form role="form" action="Actividades?actividad=crearActividad&idFase=<%=idFase%>" name="actividad" value="crearActividad" method="post">
                                        <div class="box-body">
                                            <!-- textarea -->
                                            <div class="form-group">
                                                <label>Descripción de la actividad</label>
                                                <textarea class="form-control" rows="3" placeholder="Introduzca una descripción de la actividad" name="descripcion"></textarea>
                                            </div>
                                            <div class="form-group">
                                                <label>Rol necesario</label>
                                                <select class="form-control select2" style="width: 100%;" name="rol">
                                                    <option selected="selected" value="JP">Jefe de proyecto</option>
                                                    <option value="AN">Analista</option>
                                                    <option value="DI">Diseñador</option>
                                                    <option value="AP">Analista-programador</option>
                                                    <option value="RP">Responsable equipo de pruebas</option>
                                                    <option value="PG">Programador</option>
                                                    <option value="PR">Probador</option>
                                                </select>
                                            </div><!-- /.form-group -->
                                            <div class="form-group">
                                                <label for="duracionEstimadaActividad">Duración estimada de la actividad</label>
                                                <input type="number" class="form-control" id="duracionEstimadaActividad" placeholder="Introduzca la duración estimada de esta actividad" name="duracionEstimada" value="duracionE">
                                            </div>
                                            <!-- Date range -->
                                            <div class="form-group">
                                                <label>Fecha de la actividad:</label>
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
                                                    <option selected="selected" value="false">No realizada</option>
                                                    <option value="true" disabled="disabled">Realizada</option>
                                                </select>
                                            </div><!-- /.form-group -->
                                            <div class="form-group">
                                                <label for="duracionRealActividad">Duración real de la actividad</label>
                                                <input type="number" class="form-control" id="duracionRealActividad" placeholder="Introduzca la duración real de esta actividad" name="duracionReal" value="duracionR">
                                            </div>
                                        </div><!-- /.box-body -->
                                        <div class="box-footer">
                                            <button type="submit" class="btn btn-primary" name="crearActividad" value="crearActividad" onclick="return validar()">Crear Actividad</button>
                                            <a href=vistaActividades.jsp><button type="button" class="btn btn-default" name="cancelar" value="cancelar">Cancelar</button></a>
                                        </div>

                                    </form>
                                    <% } else {
                                        idActividad = (Integer) session.getAttribute("idActividad");
                                        Actividad a = (Actividad) session.getAttribute("actividad");
                                    %> 
                                    <form role="form" action="Actividades?actividad=actualizarActividad&idFase=<%=idFase%>&idActividad=<%= idActividad%>" name="actividad" value="actualizarActividad" method="post">
                                        <div class="box-body">
                                            <!-- textarea -->
                                            <div class="form-group">
                                                <label>Descripción de la actividad</label>
                                                <textarea class="form-control" rows="3" name="descripcion"><%= a.getDescripcion()%></textarea>
                                            </div>
                                            <div class="form-group">
                                                <label>Rol necesario</label>
                                                <select class="form-control select2" style="width: 100%;" name="rol">
                                                    <option 
                                                        <% if (a.getRolNecesario().equals("JP")) { %>
                                                        selected="selected" 
                                                        <% } %>
                                                        value="JP">Jefe de proyecto</option>
                                                    <option 
                                                        <% if (a.getRolNecesario().equals("AN")) { %>
                                                        selected="selected" 
                                                        <% } %>
                                                        value="AN">Analista</option>
                                                    <option 
                                                        <% if (a.getRolNecesario().equals("DI")) { %>
                                                        selected="selected" 
                                                        <% } %>
                                                        value="DI">Diseñador</option>
                                                    <option 
                                                        <% if (a.getRolNecesario().equals("AP")) { %>
                                                        selected="selected" 
                                                        <% }%>
                                                        value="AP">Analista-programador</option>
                                                    <option 
                                                        <% if (a.getRolNecesario().equals("PR")) { %>
                                                        selected="selected" 
                                                        <% } %>
                                                        value="RP">Responsable equipo de pruebas</option>
                                                    <option 
                                                        <% if (a.getRolNecesario().equals("PG")) { %>
                                                        selected="selected" 
                                                        <% } %>
                                                        value="PG">Programador</option>
                                                    <option 
                                                        <% if (a.getRolNecesario().equals("PR")) { %>
                                                        selected="selected" 
                                                        <% }%>
                                                        value="PR">Probador</option>
                                                </select>
                                            </div><!-- /.form-group -->
                                            <div class="form-group">
                                                <label for="duracionEstimadaActividad">Duración estimada de la actividad</label>
                                                <input type="number" class="form-control" id="duracionEstimadaActividad" name="duracionEstimada" value="<%= a.getDuracionEstimada()%>">
                                            </div>
                                            <!-- Date range -->
                                            <div class="form-group">
                                                <label>Fecha de la actividad:</label>
                                                <div class="input-group">
                                                    <div class="input-group-addon">
                                                        <i class="fa fa-calendar"></i>
                                                    </div>
                                                    <input type="text" class="form-control pull-right" id="reservation" name="fechaInicioyFin" value="<%= a.getFechaInicio()%> - <%= a.getFechaFin()%>">
                                                </div><!-- /.input group -->
                                            </div><!-- /.form group -->
                                            <div class="form-group">
                                                <label>Estado</label>
                                                <select class="form-control select2" style="width: 100%;" name="estado">
                                                    <option 
                                                        <% if (a.getEstado() == false) { %>
                                                        selected="selected" 
                                                        <% } %>
                                                        value="false">No realizada</option>
                                                    <option 
                                                        <% if (a.getEstado() == true) { %>
                                                        selected="selected" 
                                                        <% }%>
                                                        value="true">Realizada</option>
                                                </select>
                                            </div><!-- /.form-group -->
                                            <div class="form-group">
                                                <label for="duracionRealActividad">Duración real de la actividad</label>
                                                <input type="number" class="form-control" id="duracionRealActividad" name="duracionReal" value="<%= a.getDuracionReal()%>">
                                            </div>
                                        </div><!-- /.box-body -->
                                        <div class="box-footer">
                                            <button type="submit" class="btn btn-primary" name="actualizarActividad" value="actualizarActividad" onclick="return validar()">Actualizar Actividad</button>
                                            <a href=vistaActividades.jsp><button type="button" class="btn btn-default" name="cancelar" value="cancelar">Cancelar</button></a>
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
        <!-- Select2 -->
        <script src="plugins/select2/select2.full.min.js"></script>
        <!-- InputMask -->
        <script src="plugins/input-mask/jquery.inputmask.js"></script>
        <script src="plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
        <script src="plugins/input-mask/jquery.inputmask.extensions.js"></script>
        <!-- date-range-picker -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.2/moment.min.js"></script>
        <script src="plugins/daterangepicker/daterangepicker.js"></script>
        <!-- bootstrap color picker -->
        <script src="plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
        <!-- bootstrap time picker -->
        <script src="plugins/timepicker/bootstrap-timepicker.min.js"></script>
        <!-- SlimScroll 1.3.0 -->
        <script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>
        <!-- iCheck 1.0.1 -->
        <script src="plugins/iCheck/icheck.min.js"></script>
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
