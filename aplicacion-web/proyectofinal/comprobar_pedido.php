<?php
/**
 * Insertar un nuevo alumno en la base de datos
 */

require 'Funciones.php';
   $consulta = "SELECT mesa FROM pedido where  estado=1 and estado_cuenta=0 ";
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