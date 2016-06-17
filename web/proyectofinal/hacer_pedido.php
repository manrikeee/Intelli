<?php
/**
 * Insertar un nuevo alumno en la base de datos
 */

require 'Funciones.php';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {
     $id_producto = $_GET['id_producto'];
      $cantidad = $_GET['cantidad'];
      $mesa = $_GET['mesa'];
      $id_pedido = $_GET['id_pedido'];
      $nombre = $_GET['nombre'];

    // Decodificando formato Json
    $body = json_decode(file_get_contents("php://input"), true);

    // Insertar Alumno
    $retorno = Funciones::hacer_pedido(
        $id_producto,
        $cantidad,
        $mesa,
        $id_pedido,
        $nombre
       );

    if ($retorno) {
        
		echo 1;
    } else {
        
		echo 0;
    }
}

?>