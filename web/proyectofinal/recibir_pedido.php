<?php
/**
 * Insertar un nuevo alumno en la base de datos
 */

require 'Funciones.php';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {
     
      $mesa = $_GET['mesa'];
     
    // Decodificando formato Json
    //$body = json_decode(file_get_contents("php://input"), true);

    // Insertar Alumno
    $retorno = Funciones::recibir_pedido(    
        $mesa    
       );
    $id=$retorno['id'];

    if ($retorno) {
       
		echo $id;
    } else {
        
		echo 0;
    }
}

?>