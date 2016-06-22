<?php
/**
 * Obtiene todas las alumnos de la base de datos
 */

require 'Funciones.php';
echo '<table class="table table-striped table8" >';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {
     if (isset($_GET['mesa'])) {

        // Obtener parámetro idalumno
        $mesa = $_GET['mesa'];
        
        // Tratar retorno
      

    // Manejar petición GET
    $num_pedido= Funciones::recibir_pedido_web($mesa);

    if ($num_pedido) {

     
        $idd = $num_pedido["id"];
      

         

                            //$mysqli = new mysqli("mysql.hostinger.es", "u308223679_mk", "poliejid0", "u308223679_bd");
                            $mysqli = new mysqli("localhost", "mk", "", "proyecto_final");

                            /* verificar la conexión */
                            if (mysqli_connect_errno()) {
                                printf("Falló la conexión failed: %s\n", $mysqli->connect_error);
                                exit();
                            }

                             $query = "SELECT pedido_producto.*
                             FROM pedido_producto,pedido
                             WHERE pedido_producto.id_pedido=$idd and pedido_producto.id_pedido=pedido.id and pedido.estado=1";
                            $result = $mysqli->query($query);
                    
                           
                             
                            echo '<th id="pedido222" data-id='.$mesa.'>Nombre</th>'; 
                            echo '<th>Unidades</th>'; 
                            
                            echo '<th>Precio</th>'; 
                            echo '</tr> ';  

                              echo '<h2 id="pedido22" data-id='.$idd.'>Numero factura: '.$idd.'<h2>'; 
                            $total=0;
                            /* array asociativo */
                            while($row = $result->fetch_array(MYSQLI_ASSOC)){
                                
                               // $fecha = date_create($row["fecha"]); 
                                //$newFecha = date_format($fecha,'d/m/y');d
                                $id = $row["id"];
                                 $precio= Funciones::dimeprecio($row["nombre"]);
                                 $precio2 = $precio["precio"];
                                 $precio2=$precio2*$row["cantidad"];
                                 $total=$total+$precio2;
                               
                                
                                   
                                         
                                        echo '<td >'.$row["nombre"].'</td>';  //nombre
                                        echo '<td>'.$row["cantidad"].'</td>';  //descripcion                              
                                        echo '<td>'.$precio2.' €</td>';  //precio
                                       
                                         echo '</tr> '; 
                                   
                                /*If que determina los botones segun si has creado ono la actividad y tambien para controlar las inscripciones*/
                               
                                
                            }

                            echo '</table>';
                            echo '<h3 class="text-right negrita" style="margin-right: 20%;"> Total: '.$total.' € </h3>'; 
                           
}else{

    echo "No hay cuenta para mostrar";
}

       
    } else {
        echo "No hay pedido pendiente";
    }

}
      

 ?>
