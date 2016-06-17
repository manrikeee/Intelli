<?php
session_start ();
?>

<!DOCTYPE HTML>
<html>
    <head>
        <title>Intellibar Cocina</title>
        <link rel="icon" type="image/png" href="images/APPTIVATE-Logo.png" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
      <link href="../css/bootstrap.css" rel='stylesheet' type='text/css' />
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="js/jquery.min.js"></script>
         <!-- Custom Theme files -->
       <link href="../css/style.css" rel='stylesheet' type='text/css' />
         <!-- Custom Theme files -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
         <script type="text/javascript">
            $(document).ready(function(){
              refreshTable();
            });

    function refreshTable(){
        $('#tableHolder').load('../proyectofinal/cocina_table.php', function(){

           setTimeout(refreshTable, 1000);
        });
    }
</script>
        
    </head>
    <body>
        <div class="bg">
         <div class="titulo">
            <h1> IntelliBar Cocina</h1>
        </div>
        <div id="tableHolder">
         <?php
                        
                        
                    ?>

        </div>
    </body>
</html>
