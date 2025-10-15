<?php
/*
 * Ho guardato il file e ho visto che la prima riga sono le intestazioni
 * e le colonne dei miei dati sono:
 * 0: Cognome
 * 1: Nome
 * 2: Classe
 * 3: Materia
 * 4: Data del voto
 * 5: Voto
 * 6: Tipo di verifica
 */    $somma = 0;
    $conta = 0;
    $lines = file("random-grades.csv");
    //Sono i parametri su cui filtrare
    $cognome = "Stefanelli";
    $classe = "4BIT";
    $disciplina = "Lingua Inglese";
    // Salto la prima riga (intestazioni)
    for($i = 1; $i < count($lines); $i++){
        $row = explode(",", $lines[$i]);
        
//        if($row[0] != $cognome) continue; //Salto i voti vuoti

        //Se è settato il filtro e non corrisponde, salto la riga
        if( isset($cognome) && $row[0] != $cognome ) continue;
        if( isset($classe) && $row[2] != $classe ) continue;
        if( isset($disciplina) && $row[3] != $disciplina ) continue;

        $somma += $row[5];
        $conta++;
    }
    //Preparo il testo di output basandomi sui filtri
    $testoNome = isset($cognome) ? "di $cognome" : "";
    $testoClasse = isset($classe) ? "della classe $classe" : "";
    $testoDisciplina = isset($disciplina) ? "di $disciplina" : "";
    echo "La media $testoNome $testoClasse $testoDisciplina è: " . ($somma / $conta);
?>