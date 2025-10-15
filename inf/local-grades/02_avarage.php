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
 */
    $somma = 0;
    $conta = 0;
    $lines = file("random-grades.csv");
    // Salto la prima riga (intestazioni)
    for($i = 1; $i < count($lines); $i++){
        $row = explode(",", $lines[$i]);
//        var_dump($row);
        $somma += $row[5];
        $conta++;
    }
    echo "La media è: " . ($somma / $conta);
?>