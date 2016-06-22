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
    <style>
   .col-md-4 button{
  margin: 30px;
  }
    </style>

    
       <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
       <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
       <title>Intellibar Barra</title>
       <link rel="icon" type="image/png" href="images/logotipo.png" />
       <link href="../css/bootstrap.css" rel='stylesheet' type='text/css' />
       <link href="../css/style.css" rel='stylesheet' type='text/css' />
       <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <!-- Custom Theme files -->
       <meta name="viewport" content="width=device-width, initial-scale=1">

       <script type="text/javascript">
        $(document).ready(function(){
           //refreshMesasPedidas();
              refreshTable();
              refreshMesas();
              refreshTableCocina();
             
            });

        function refreshTable(){
            $('#tableHolder').load('../proyectofinal/barra_table.php', function(){
              

               setTimeout(refreshTable, 10000);
            });
        }
         function refreshTableCocina(){
            $('#tableHolder2').load('../proyectofinal/barra_table_listos.php', function(){
              var rows = $('#tabla2');
             
    

               setTimeout(refreshTableCocina, 10000);
            });
        }

           
        function refreshMesas(){          
          $.get('../proyectofinal/comprobar_mesa.php', function(respuestaSolicitud){
             //var item = $.parseJSON(respuestaSolicitud);

            var data= eval("(function(){return " + respuestaSolicitud + ";})()");
             
            if (respuestaSolicitud!=0){
             
            
              $.each(data, function(i, item) {
              
                 
                  ////// meterlo todo aqui en el bucle
                if (item['estado_cuenta']==1 && item['estado']==1){
                
                  mesaActiva(item['mesa']);
                }
                 if (item['estado_cuenta']==0 && item['estado']==1){

                mesaPideCuenta(item['mesa']);
              }
               if (item['estado']==0 ){
                mesaNoactiva(item['mesa']);
              }
            

              });

            }else{
              mesaNoactiva2();
            }
           
             

            
            
           setTimeout(refreshMesas, 5000);
            });

        
         
          }

       function refreshMesasPedidas(){          
          $.get('../proyectofinal/comprobar_pedido.php', function(respuestaSolicitud){
             data = $.parseJSON(respuestaSolicitud);
            if (respuestaSolicitud!=0){
               
              $.each(data, function(i, item) {
                  //alert(item['mesa']);
                  mesaPideCuenta(item['mesa']);
              });
            }
          setTimeout(refreshMesasPedidas, 5000);
            });
         
         
          }
      </script>
     
    <script type="text/javascript">
         
      function mesaActiva(respuestaSolicitud){
          $("#"+respuestaSolicitud).attr('src','../images/mesaocupada.png', function(){
      
           //setTimeout(refreshTable, 5000);
        });
       }
      </script>
    <script type="text/javascript">

       function  mesaNoactiva(respuestaSolicitud){


          $("#"+respuestaSolicitud).attr('src','../images/mesalibre.png', function(){
      
          // setTimeout(refreshTable, 5000);
        });
       }
     </script>
     <script type="text/javascript">

       function  mesaNoactiva2(){


          $('.img-rounded').attr('src','../images/mesalibre.png', function(){
      
          // setTimeout(refreshTable, 5000);
        });
       }
     </script>
     <script type="text/javascript">

       function  mesaPideCuenta(respuestaSolicitud){


          $("#"+respuestaSolicitud).attr('src','../images/mesacuenta.png', function(){
      
          // setTimeout(refreshTable, 5000);
        });
       }
     </script>
    <script type="text/javascript">
      function mostrarcuenta(mesa){
         location.href="mostrar_cuentaweb.php?mesa="+mesa;               
            }  
    </script>      
    </head>
    <body>
     <div class='error' id="eeee" style='display:none'>Mesa cobrada</div>
    
    </div>
        <div class="container-fluid  jumbotron">
        <div class="titulo">
                        <h1 class="tituloprincipal">IntelliBar Barra</h1>
                        
                    </div>
            <div class="row , jumbotron" style="height:100% width:100%">
                <div class="col-md-8">
                    <div class="titulo">
                        <h1>Pedidos</h1>
                        
                    </div>
                    <div id="tableHolder"></div>
                     <div class='error' id="eee" style='display:none'>Producto listo</div>
            
                <div class="row">

                 <div class="titulo"  >

                        <h1> Pedidos Cocina Listos</h1>
                    
                    <div id="tableHolder2" ></div>
                     <div class='error' id="ee" style='display:none'>Producto listo</div>
                    </div>
                     </div>
                     </div>


                 <div id="container">      
                <div class="col-md-4 titulo2" >
                   
                        <h1 align="center">Mesas</h1>

                   
                        <button data-id="1" class="my-button">
                            <h2>Mesa 1</h2>
                            <img id="1" class='img-rounded' src="../images/mesalibre.png">
                        </button> 
                  
                 
                        <button data-id="2" class="my-button">
                            <h2>Mesa2</h2>
                            <img id="2" class='img-rounded'  src="../images/mesalibre.png">
                        </button> 
                
                    <div >
                        <button data-id="3" class="my-button">
                            <h2>Mesa 3</h2>
                            <img id="3" class='img-rounded'  src="../images/mesalibre.png">
                        </button> 
                   
                    
                        <button data-id="4" class="my-button">
                            <h2>Mesa 4 </h2>
                            <img id="4" class='img-rounded' src="../images/mesalibre.png" >
                        </button> 
                    </div>
                    <div>
                        <button data-id="5" class="my-button">
                            <h2>Mesa 5</h2>
                            <img id="5" class='img-rounded' src="../images/mesalibre.png" >
                        </button> 
                  
                    
                        <button  data-id="6" class="my-button">
                            <h2>Mesa 6</h2>
                            <img id="6" class='img-rounded' src="../images/mesalibre.png">
                        </button> 
                        </div>
                         </div>
                         </div>

                        
      
   
   <div>                 
    <div id="myModal" class="modal">
      <div>
  <!-- Modal content -->
        <div class="modal-content">
         <div class="modal-header">
          <span class="close">×</span>
          <h2 id="numeromesa">Cuenta Mesa: </h2>
         </div>
        <div class="modal-body" id="vista">          
      </div>
    <div class="modal-footer">
      <button class="botonn"  id='cobrar' onclick="pedidoCobrado()"><h3>Cobrar</h3>
    </div>
    </div>
   
  </div>
  

  
<script>
    $(document).ready(function() {          
        var modal = document.getElementById('myModal');
         $('body').on('click', '.my-button', function() {
              var myBookId = $(this).data('id');                 
              document.getElementById("numeromesa").innerHTML="Mesa "+myBookId;              
              $("#vista").load("../proyectofinal/obtener_cuenta_web.php?mesa="+myBookId);    
                modal.style.display = "block";
         });
    });
</script>
<script>
    var btn = document.getElementById("myBtn");
    var modal = document.getElementById('myModal');
    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];
    span.onclick = function() {
        modal.style.display = "none";
    }
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    } 
   </script>  
  
 <script>
    function pedidoListo($id,$r){
      $('#eee').fadeIn(400).delay(3000).fadeOut(400);
      var i = $r.parentNode.parentNode.rowIndex;
       $('#1').load('../proyectofinal/pedido_listo.php?id_pedido='+$id, function(){
       document.getElementById('tabla2').deleteRow(i);
       
    });
     }
 </script>
  <script>
    function pedidoListo2($id,$r){
      $('#ee').fadeIn(400).delay(3000).fadeOut(400);
       var i = $r.parentNode.parentNode.rowIndex;
       $('#1').load('../proyectofinal/pedido_listo.php?id_pedido='+$id, function(){
       document.getElementById('tabla22').deleteRow(i);
       
    });
     }
 </script>
 <script type="text/javascript" >
    function pedidoCobrado(){
       $('#eeee').fadeIn(400).delay(3000).fadeOut(400);
       var a= $('#pedido222').data('id');
         $("#"+a).attr('src','../images/mesalibre.png');
         var idd= $('#pedido22').data('id');
         $.get('../proyectofinal/pedido_cobrado.php?id_pedido='+idd);

        
        var modal = document.getElementById('myModal');
          modal.style.display = "none";
          // setTimeout(refreshTable, 5000);
        
       
      
      
       
       //$("#"+idd).attr('src',"https://upload.wikimedia.org/wikipedia/commons/thumb/a/a5/Table.png/200px-Table.png", function(){
      
          // setTimeout(refreshTable, 5000);
       

       
  //  });
     }
 </script>

</div>

    </div>

                    </button> </div>


                </div>
            </div>
        </div>
       
    </body>
</html>
