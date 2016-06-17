<!-- AUTOR !-->
<?php
session_start ();
?>

<!DOCTYPE HTML>
<html>
    <head>
        <title>IntelliBar</title>
        <link rel="icon" type="image/png" href="images/APPTIVATE-Logo.png" />
        <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="js/jquery.min.js"></script>
         <!-- Custom Theme files -->
        <link href="css/style.css" rel='stylesheet' type='text/css' />
         <!-- Custom Theme files -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
    </head>
    <body>
        <div class="bg">
        <!----- start-header---->
            <div class="container">
               
            </div>
            <!----- //End-header---->
            <!---- banner-info ---->
            <div class="banner-info">
                <div class="container">
                    <div class="formLogin">
                        <form action="#" method="post">
                            <fieldset>
                                <legend>Introduce tus datos y entra:</legend>
                                <input class="inputForm1" type="text" name="username" placeholder="Nombre de Usuario..." required>
                                <input class="inputForm1" type="password" name="password" placeholder="Contrase&ntilde;a..." required>
                                <input type="submit" value="ENTRAR" class="btnForm"/> 
                            </fieldset>
                        </form>
                    </div>
                    <div class="top-banner-grids wow bounceInUp" data-wow-delay="0.4s">
                        <div class="clearfix"> </div>
                    </div>
                </div>
            </div>
            <div class="clearfix"> </div>
        </div>
    </body>
</html>
<?php
require 'proyectofinal/Usuarios.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    if (isset($_POST['username'])) {

        // Obtener parámetro idalumno
        $nombre = $_POST['username'];
        $password = $_POST['password'];

        // Tratar retorno
        $retorno = Usuarios::getById($nombre,$password);


        if ($retorno) {

            $_SESSION["id"] = $retorno["id"];
            $_SESSION["nombre"] = $retorno["usuario"];
            $_SESSION["tipo"] = $retorno["tipo"];
              if($_SESSION["tipo"]=="barra"){
                header('Location: vistas/barra.php');

              }
              if($_SESSION["tipo"]=="cocina"){
                header('Location: vistas/cocina.php');

              }
            

            //header('Location: actividades.php');
        } else {
            // Enviar respuesta de error general/**CREAR UN ALERT PARA QUE EL USUARIO VEA QUE NO LO HA INSERTADO CORRECTAMENTE******/
            print json_encode(
                array(
                    'estado' => '2',
                    'mensaje' => 'No se obtuvo el registro'
                )
            );
        }

    } else {
        // Enviar respuesta de error
        print json_encode(
            array(
                'estado' => '3',
                'mensaje' => 'Se necesita un identificador'
            )
        );
    }
}
?>

