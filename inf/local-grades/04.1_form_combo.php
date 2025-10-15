<?php    

    //print_r($_REQUEST);

    // Funzione per ottenere il valore di un input in modo sicuro
    function getInput($name) {
        return isset($_REQUEST[$name]) ? $_REQUEST[$name] : null;
    }   


    function getElencoClassi() {
        $elencoClassi = array();
        $lines = file("random-grades.csv");
        // Salto la prima riga (intestazioni)
        for($i = 1; $i < count($lines); $i++){
            $row = explode(",", $lines[$i]);
            $elencoClassi[$row[2]] = 1;
        }
        $chiavi = array_keys($elencoClassi); 
        sort($chiavi);
        return $chiavi;
    }   

    $elencoClassi = getElencoClassi();


    function getElencoMaterie() {
        $elencoMaterie = array();
        $lines = file("random-grades.csv");
        // Salto la prima riga (intestazioni)
        for($i = 1; $i < count($lines); $i++){
            $row = explode(",", $lines[$i]);
            $elencoMaterie[$row[3]] = 1;
        }
        ksort($elencoMaterie);
        return array_keys($elencoMaterie);
    }   

    $elencoMaterie = getElencoMaterie();


    // Funzione che calcola la media con filtri opzionali
    // I filtri sono nulli di default quindi le variabili valgono sempre ma nel caso sono null
    function calcolaMedia($cognome = null, $classe = null, $disciplina = null){
        $somma = 0;
        $conta = 0;
        $lines = file("random-grades.csv");
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
        <form action="04.1_form_combo.php" method="post">
            <label for="cognome">Cognome:</label>
            <input type="text" id="cognome" name="cognome" value="<?php echo getInput('cognome'); ?>"><br><br>
            <label for="classe">Classe:</label>
            <select id="classe" name="classe">
                <option value="">--Tutte le classi--</option>
                <?php 
                    foreach($elencoClassi as $classe) {
                        $selected = (getInput('classe') == $classe) ? "selected" : "";
                        echo "<option value=\"$classe\" $selected>$classe</option>\n";
                    }
                ?>
            </select><br><br>
            <label for="disciplina">Disciplina:</label>
            <select id="disciplina" name="disciplina">
                <option value="">--Tutte le discipline--</option>
                <?php 
                /*  Traduzione del foreach delle righe #138-#141 in for. 
                    IMPORTNANTE: in questo caso non posso basarmi su l'ordinamento dei dati nell'array (devo leggere la documentazione)
                    for($i=0;$i<count($elencoMaterie);$i++) {
                        $selected = (getInput('disciplina') == $elencoMaterie[$i]]) ? "selected" : "";
                        echo "<option value=\"$materia\" $selected>$elencoMaterie[$i]</option>\n";
                    }
                */
                    foreach($elencoMaterie as $materia) {
                        $selected = (getInput('disciplina') == $materia) ? "selected" : "";
                        echo "<option value=\"$materia\" $selected>$materia</option>\n";
                    }
                ?>  
            </select><br><br>
            <input type="submit" value="Calcola Media">
        </form>
    </body>
</html>