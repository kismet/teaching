<?php    

    //print_r($_REQUEST);

    // Funzione per ottenere il valore di un input in modo sicuro
    function getInput($name) {
        return isset($_REQUEST[$name]) ? $_REQUEST[$name] : null;
    }   

    // Funzione che calcola la media con filtri opzionali
    // I filtri sono nulli di default quindi le variabili valgono sempre ma nel caso sono null
    function calcolaMedia($cognome = null, $classe = null, $disciplina = null){
        $somma = 0;
        $conta = 0;
        $lines = file("random-grades.csv");

        // var_dump($cognome, $classe, $disciplina);
        // Salto la prima riga (intestazioni)
        for($i = 1; $i < count($lines); $i++){
            $row = explode(",", $lines[$i]);
            
            //Se è settato il filtro e non corrisponde, salto la riga
            if( $cognome !=null && $row[0] != $cognome ) continue;
            if( $classe != null && $row[2] != $classe ) continue;
            if( $disciplina != null && $row[3] != $disciplina ) continue;
            //echo "Riga considera: ".implode(", ", $row) . "<br>\n";

            $somma += $row[5];
            $conta++;
        }
        if($conta == 0) {
            return null; // Evito divisione per zero
        }
        return ($somma / $conta);    
    }

    function elaboraInput() {
        if( count($_REQUEST) == 0) {
            //Non sono stati passati parametri, esco
            return;
        }
        //Sono i parametri su cui filtrare
        if( isset($_REQUEST['cognome']) ){
            $cognome = $_REQUEST['cognome'] ;
        } 
        if( isset($_REQUEST['classe']) ){
            $classe = $_REQUEST['classe'] ;
        } 
        if( isset($_REQUEST['disciplina']) ){
            $disciplina = $_REQUEST['disciplina'] ;
        } 

        $media = calcolaMedia($cognome, $classe, $disciplina);

        //var_dump($media);

        if($media == null) {
            //Preparo il testo di output basandomi sui filtri
            $testoNome = isset($cognome) ? "l'alunno cognome" : "";
            $testoClasse = isset($classe) ? "della classe $classe" : "";
            $testoDisciplina = isset($disciplina) ? "di $disciplina" : "";
           return "Nessun voto trovato per $testoNome $testoClasse $testoDisciplina, provare a cambiare i filtri";
        }else{
            $media = round($media, 2);
        }
        //Preparo il testo di output basandomi sui filtri
        $testoNome = isset($cognome) ? "di $cognome" : "";
        $testoClasse = isset($classe) ? "della classe $classe" : "";
        $testoDisciplina = isset($disciplina) ? "di $disciplina" : "";

        return "La media $testoNome $testoClasse $testoDisciplina è: " . $media;
    }
?>
<html>
    <head>
        <title>Calcolo Media Voti</title>
        <link rel="stylesheet" href="node_modules/bootstrap/dist/css/bootstrap.min.css">
        <script src="node_modules/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
    </head> 
    <body>
        <h1>Calcolo Media Voti</h1>
        <p><?php echo elaboraInput(); ?></p>
        <h2>Inserisci i filtri</h2>
        <form action="04_form.php" method="post">
            <label for="cognome">Cognome:</label>
            <input type="text" id="cognome" name="cognome" 
                value="<?php echo getInput('cognome'); ?>"><br><br>
            <label for="classe">Classe:</label>
            <input type="text" id="classe" name="classe" 
                value="<?php echo getInput('classe'); ?>"><br><br>
            <label for="disciplina">Disciplina:</label>
            <input type="text" id="disciplina" name="disciplina" 
                value="<?php echo getInput('disciplina'); ?>"><br><br>
            <input type="submit" value="Calcola Media">
        </form>
    </body>
</html>