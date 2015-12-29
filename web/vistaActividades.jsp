<%@page import="Business.Actividad"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Ver Actividades</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.5 -->
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
        <!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
        <link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">
        <!-- iCheck -->
        <link rel="stylesheet" href="plugins/iCheck/flat/blue.css">
        <!-- Morris chart -->
        <link rel="stylesheet" href="plugins/morris/morris.css">
        <!-- jvectormap -->
        <link rel="stylesheet" href="plugins/jvectormap/jquery-jvectormap-1.2.2.css">
        <!-- Date Picker -->
        <link rel="stylesheet" href="plugins/datepicker/datepicker3.css">
        <!-- Daterange picker -->
        <link rel="stylesheet" href="plugins/daterangepicker/daterangepicker-bs3.css">
        <!-- bootstrap wysihtml5 - text editor -->
        <link rel="stylesheet" href="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">

            <%@include file="navBar.html" %>

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Lista de actividades
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li><a href="vistaProyectos.jsp">Proyectos</a></li>
                        <li><a href="vistaFases.jsp">Fases</a></li>
                        <li class="active">Actividades</li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-md-3">
                            <div class="box box-solid">
                                <div class="box-header with-border">
                                    <h3 class="box-title">Lista de actividades</h3>
                                    <div class="pull-right">
                                        <small class="text-red">No realizada.</small>&nbsp;&nbsp;&nbsp;&nbsp;
                                        <small class="text-green">Realizada.</small>
                                    </div>
                                </div>
                                <div class="box-body no-padding">
                                    <%
                                        int idFase = (Integer) session.getAttribute("idFase");
                                        ArrayList<Actividad> actividades = (ArrayList<Actividad>) session.getAttribute("actividades");
                                        String colorClase = "";
                                        for (Actividad a : actividades) {
                                    %>
                                    <ul class="nav nav-pills nav-stacked">
                                        <div class="btn pull-left">
                                            <a href="Actividades?actividad=actualizarUnaActividad&idFase=<%=idFase%>&idActividad=<%=a.getIdentificador()%>">
                                                <% if (a.getEstado()) {
                                                        colorClase = "text-green";
                                                    } else {
                                                        colorClase = "text-red";
                                                    }
                                                %>
                                                <div class="<%=colorClase%>">
                                                    <i class="fa fa-file-text-o"></i> <%= a.getDescripcion()%>
                                                    <small> <%= a.getFechaInicio()%> - <%= a.getFechaFin()%>  </small>
                                                </div>
                                            </a>
                                        </div>
                                    </ul>
                                    <%
                                        }
                                    %>
                                </div><!-- /.box-body -->
                                <div class="box-footer">
                                    <a class="btn btn-app" href="Actividades?actividad=crearNuevaActividad&idFase=<%=idFase%>">
                                        <i class="fa fa-edit"></i> Nuevo Actividad
                                    </a>
                                </div>
                            </div><!-- /. box -->

                        </div><!-- /.row -->
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
        <!-- FastClick -->
        <script src="plugins/fastclick/fastclick.min.js"></script>
        <!-- AdminLTE App -->
        <script src="dist/js/app.min.js"></script>
        <!-- AdminLTE for demo purposes -->
        <script src="dist/js/demo.js"></script>
    </body>
</html>
