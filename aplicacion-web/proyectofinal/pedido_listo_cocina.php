<?php
/**
 * Actualiza un alumno especificado por su identificador
 */

require 'Funciones.php';



if ($_SERVER['REQUEST_METHOD'] == 'GET') {
      

        // Obtener parámetro i
        
        $pedido = $_GET['id_pedido'];
       

    // Decodificando formato Json
    $body = json_decode(file_get_contents("php://input"), true);
    

    // Actualizar alumno
    $retorno = Funciones::pedidoListoCocina( $pedido
        );
    if ($retorno) {
        $json_string = json_encode(1);
        //echo $json_string;
        $var1=$retorno;
        echo $json_string;
        
    } else {
       // $json_string = json_encode(array("estado" => 2,"mensaje" => "No se actualizo el registro"));
        echo false;
       
    }
    

        
    }
