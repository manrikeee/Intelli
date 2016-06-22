<?php
/**
 * Obtiene todas las alumnos de la base de datos
 */

require 'Funciones.php';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {

    // Manejar petición GET
    $usuarios = Funciones::getAll();

    if ($usuarios) {


        print json_encode($usuarios);
    } else {
        print json_encode(array(
            "estado" => 2,
            "mensaje" => "Ha ocurrido un error"
        ));
    }
}

