<?php
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
            if( $cognome != null && $row[0] != $cognome ) continue;
            if( $classe != null && $row[2] != $classe ) continue;
            if( $disciplina != null && $row[3] != $disciplina ) continue;

            $somma += $row[5];
            $conta++;
        }
        if($conta == 0) return 0; //Evito divisione per zero
        return ($somma / $conta);    
    }
    //Sono i parametri su cui filtrare
//    $cognome = "Stefanelli";
    $classe = "4BIT";
//    $disciplina = "Lingua Inglese";

    $cognome = "";
    $classe = "4BIT";
    $disciplina = null;


    $media = calcolaMedia($cognome, $classe, $disciplina);
//    $media = calcolaMedia(null, $classe, null); 
//    $media = calcolaMedia($classe); 

    //Preparo il testo di output basandomi sui filtri
    $testoNome = isset($cognome) ? "di $cognome" : "";
    $testoClasse = isset($classe) ? "della classe $classe" : "";
    $testoDisciplina = isset($disciplina) ? "di $disciplina" : "";

    echo "La media $testoNome $testoClasse $testoDisciplina è: " . $media;
?>
