<?php
/**
 * Obtiene todas las alumnos de la base de datos
 */

require 'Funciones.php';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {
     if (isset($_GET['id_pedido'])) {

        // Obtener parámetro idalumno
        $id_pedido = $_GET['id_pedido'];
        
        // Tratar retorno
      
    // Manejar petición GET
    $cuenta = Funciones::getCuenta($id_pedido);

    if ($cuenta) {

        print json_encode($cuenta);
    } else {
        echo "No hay cuenta sin pedir";
       
    }
}
}
