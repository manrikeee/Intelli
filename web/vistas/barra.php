<?php
session_start ();
?>

<!DOCTYPE HTML>
<html>
    <head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
        <title>Intellibar Barra</title>
        <link rel="icon" type="image/png" href="images/APPTIVATE-Logo.png" />
      <link href="../css/bootstrap.css" rel='stylesheet' type='text/css' />
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="js/jquery.min.js"></script>
         <!-- Custom Theme files -->
       <link href="../css/style.css" rel='stylesheet' type='text/css' />
       <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <!-- Custom Theme files -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
       <script type="text/javascript">
            $(document).ready(function(){
              refreshTable();
            });

    function refreshTable(){
        $('#tableHolder').load('../proyectofinal/barra_table.php', function(){

           setTimeout(refreshTable, 50000);
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
        
        <div class="bg">
            <div class="row">
                <div class="col-md-8">
                    <div class="titulo">
                        <h1> IntelliBar Barra</h1>
                    </div>
                    <div id="tableHolder"></div>
                </div>
                <div class="col-md-4"></div>
                    <div class="titulo">
                        <h1> Mesas</h1>
                    </div> 
                    <div>
                        <button data-id="1" class="my-button">
                            <h2>Mesa 1</h2>
                            <img src="http://angarmegia.wikispaces.com/file/view/Mesa.png/187753543/377x282/Mesa.png">
                        </button> 
                    </div>
                    <div>
                        <button data-id="2" class="my-button">
                            <h2>Mesa2</h2>
                            <img src="http://angarmegia.wikispaces.com/file/view/Mesa.png/187753543/377x282/Mesa.png">
                        </button> 
                    </div>
                    <div>
                        <button data-id="3" class="my-button">
                            <h2>Mesa 3</h2>
                            <img src="http://angarmegia.wikispaces.com/file/view/Mesa.png/187753543/377x282/Mesa.png">
                        </button> 
                    </div>
                    <div>
                        <button data-id="4" class="my-button">
                            <h2>Mesa 4 </h2>
                            <img src="http://angarmegia.wikispaces.com/file/view/Mesa.png/187753543/377x282/Mesa.png">
                        </button> 
                    </div>
                    <div>
                        <button data-id="5" class="my-button">
                            <h2>Mesa 5</h2>
                            <img src="http://angarmegia.wikispaces.com/file/view/Mesa.png/187753543/377x282/Mesa.png">
                        </button> 
                    </div>
                    <div>
                        <button  data-id="6" class="my-button">
                            <h2>Mesa 6</h2>
                            <img src="http://angarmegia.wikispaces.com/file/view/Mesa.png/187753543/377x282/Mesa.png">
                        </button> 
                    </div>
                    
                </div> 
                    

                    

<!-- The Modal -->
    <div id="myModal" class="modal">

  <!-- Modal content -->
  <div class="modal-content">
    <div class="modal-header">
      <span class="close">×</span>
      <h2 id="numeromesa">Cuenta Mesa: </h2>
    </div>
    <div class="modal-body" id="vista">
      
     
    </div>
    <div class="modal-footer">
      <h3>Cobrar</h3>
    </div>
  </div>
  </div>
<script>
    $(document).ready(function() {
          

        var modal = document.getElementById('myModal');

        

         $('body').on('click', '.my-button', function() {
                 var myBookId = $(this).data('id');
                 alert("HOLA");
                 
                 document.getElementById("numeromesa").innerHTML="Mesa "+myBookId;
               
                 $("#vista").load("../proyectofinal/obtener_cuenta_web.php?mesa=1"); 

                 
                
                
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
         $(document).ready(function() {

        

         $('body').on('click', '.pedido', function() {
                 var id = $(this).data('id');
                 alert("EEEEE");
                               
                 $("#vista").load("../proyectofinal/pedido_listo.php?id_pedido=1"); 
            
                                
                


         });
    });
</script>
 <script>
    function hola(){
        alert("EEEEEEEEE");

    }
    </script>

  <!-- <script>


   
   /
   
/ Get the modal
var modal = document.getElementById('myModal');

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal 
btn.onclick = function() {
   

    modal.style.display = "block";
   
    $("#vista").load("../proyectofinal/obtener_cuenta_web.php?mesa=1");    
}



// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

</script> -->

</div>


    </div>




                    </button> </div>


                </div>
            </div>
        </div>
    </body>
</html>
