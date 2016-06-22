<?php
/**
 * Insertar un nuevo alumno en la base de datos
 */

require 'Funciones.php';
   $consulta = "SELECT * FROM pedido ";
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute();
            $mesa= $comando->fetchAll(PDO::FETCH_ASSOC);
            //$array = json_decode( $mesa, true );
                //$id=$mesa['mesa'];
         if ($mesa){ 
        print json_encode($mesa);
    }else { print 0;}

            //return $comando->fetch(PDO::FETCH_ASSOC);

        } catch (PDOException $e) {
            echo  0;
        }

    


?>