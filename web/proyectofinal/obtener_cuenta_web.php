<?php
/**
 * Obtiene todas las alumnos de la base de datos
 */

require 'Funciones.php';
echo '<table class="tabla table-striped">';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {
     if (isset($_GET['mesa'])) {

        // Obtener parámetro idalumno
        $mesa = $_GET['mesa'];
        
        // Tratar retorno
      

    // Manejar petición GET
    $num_pedido= Funciones::recibir_pedido_web(1);

    if ($num_pedido) {

      echo "NUM PEDIDO";
        print json_encode($num_pedido);
        $id = $num_pedido["id"];

          echo '<table class="tabla table-striped">';

                            $mysqli = new mysqli("mysql.hostinger.es", "u308223679_mk", "poliejid0", "u308223679_bd");

                            /* verificar la conexión */
                            if (mysqli_connect_errno()) {
                                printf("Falló la conexión failed: %s\n", $mysqli->connect_error);
                                exit();
                            }

                            $query = "SELECT pedido_producto.*
                             FROM pedido_producto,pedido
                             WHERE  pedido.estado=1 and pedido_producto.id_pedido=294 and pedido_producto.id_pedido=pedido.id " ;
                            $result = $mysqli->query($query);
                    
                            echo '<tr> ';  
                            echo '<th>Nombre</th>'; 
                            echo '<th>Unidades</th>'; 
                            echo '<th>Mesa</th>'; 
                            echo '</tr> ';  


                            /* array asociativo */
                            while($row = $result->fetch_array(MYSQLI_ASSOC)){
                                echo '<tr> '; 
                               // $fecha = date_create($row["fecha"]); 
                                //$newFecha = date_format($fecha,'d/m/y');d
                                $id = $row["id"];
                                
                                
                                   
                                   
                                        echo '<td>'.$row["nombre"].'</td>';  //nombre
                                        echo '<td>'.$row["cantidad"].'</td>';  //descripcion                              
                                        echo '<td>'.$row["mesa"].'</td>';  //precio
                                         echo '<td><button  data-id="6" class="pedido" title="Eliminar"></td>'; 
                                         echo '</tr> '; 
                                   
                                /*If que determina los botones segun si has creado ono la actividad y tambien para controlar las inscripciones*/
                               
                                
                            }
                            echo '</table>';
                           
}else{

    echo "No hay cuenta para mostrar";
}

       
    } else {
        echo "No hay pedido pendiente";
    }

}
      

 ?>
