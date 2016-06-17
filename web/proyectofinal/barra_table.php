<?php
                        
                        echo '<table class="tabla table-striped">';

                            $mysqli = new mysqli("mysql.hostinger.es", "u308223679_mk", "poliejid0", "u308223679_bd");

                            /* verificar la conexión */
                            if (mysqli_connect_errno()) {
                                printf("Falló la conexión failed: %s\n", $mysqli->connect_error);
                                exit();
                            }

                            $query = "SELECT pedido_producto.* ,precio FROM pedido_producto,producto where pedido_producto.id_producto=producto.id and pedido_producto.estado=1 and producto.tipo='Bebida'ORDER BY pedido_producto.id ASC" ;
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
                                //$newFecha = date_format($fecha,'d/m/y');
                                $id = $row["id"];
                                
                                
                                   
                                   
                                        echo '<td>'.$row["nombre"].'</td>';  //nombre
                                        echo '<td>'.$row["cantidad"].'</td>';  //descripcion                              
                                        echo '<td>'.$row["mesa"].'</td>';  //precio
                                         echo '<td><input type="button" value="Listo" class="btnForm" onClick="pedidoListo('.$id.')" /></td>'; 
                                         echo '</tr> '; 
                                   
                                /*If que determina los botones segun si has creado ono la actividad y tambien para controlar las inscripciones*/
                               
                                
                            }
                            echo '</table>';
                    ?>
