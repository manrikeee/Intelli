<?php
session_start ();
?>

<!DOCTYPE HTML>
<html>
    <head>
    <style>
    .tabla {
    border-radius: 15px 50px 30px 5px;
    
     background: #CACFCC;
   
    padding: 20px;
    
   
    width: 70% !important;
  font-size:30px;
}
    </style>  
     
        <title>Intellibar Cocina</title>
        <link rel="icon" type="image/png" href="images/logotipo.png" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
      <link href="../css/bootstrap.css" rel='stylesheet' type='text/css' />
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
      
       
         <!-- Custom Theme files -->
       <link href="../css/style.css" rel='stylesheet' type='text/css' />
        <script src="../js/jquery.min.js"></script>
         <!-- Custom Theme files -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
         <script type="text/javascript">
            $(document).ready(function(){
              refreshTable();
            });

    function refreshTable(){
        $('#tableHolder').load('../proyectofinal/cocina_table.php', function(){

           setTimeout(refreshTable, 10000);
        });
    }
</script>
        
    </head>
    <body>
        <div class="bg">
         <div class="titulo">
            <h1 class="tituloprincipal"> IntelliBar Cocina</h1>
            <h1 class="jumbotron"> Pedidos Cocina </h1>
        </div>

        <div id="tableHolder">
        
        </div>
         <div class='error' id="ee" style='display:none'>Producto listo</div>
    </div>
        <script>
    function pedidoListo($id,$r){
       $.get('../proyectofinal/pedido_listo_cocina.php?id_pedido='+$id, function(){
        var i = $r.parentNode.parentNode.rowIndex;
         $('.error').fadeIn(400).delay(3000).fadeOut(400);
       
         document.getElementById('table3').deleteRow(i);
       
    });
     }
</script>


    </body>
</html>
