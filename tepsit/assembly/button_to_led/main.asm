;
;MIT License
;
;Copyright (c) 2026 Stefano "Kismet" Lenzi
;
;Permission is hereby granted, free of charge, to any person obtaining a copy
;of this software and associated documentation files (the "Software"), to deal
;in the Software without restriction, including without limitation the rights
;to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
;copies of the Software, and to permit persons to whom the Software is
;furnished to do so, subject to the following conditions:
;
;The above copyright notice and this permission notice shall be included in all
;copies or substantial portions of the Software.
;
;THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
;IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
;FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
;AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
;LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
;OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
;SOFTWARE.
;
;

;Problema S2:
;	Scrivere un programma che legge da PD0 e accende 
;	il LED su PD1 quando legge 1 e lo spegne quando legge 0

; Autore: prof. LENZI Stefano <stefano@lenzi.pro>
; NOTA: Nei commenti ci sono spunti di riflessione
start:
    
	LDI R16, 0xFE; Equivalente usare (0x02) ?
	OUT DDRD, R16
	;Le seguenti operazioni sostituiscono le prime due, cosa c'è di diverso?
	CBI DDRD, 0  ; Clear Bit <SFR>, Digit (la posizione del bit)
	SBI DDRD, 1  ; Set Bit <SFR>, Digit (la posizione del bit)

	LDI R31, 0x01
	Forever:
		IN	R0, PIND	
		;RJMP VersioneSmart (valutare se il codice a questo punto)
		;					(è una soluzione valida al problema dato)
		AND R0, R31		;Maschera per evitare ingressi spuri
		DEC R0
		BREQ Accendi
		CLR R1
		OUT PORTD, R1
		RJMP Forever
	Accendi:
		LDI R16, 0x02
		OUT PORTD, R16
		RJMP Forever
	VersioneSmart:
		LSL	R0
		OUT PORTD, R0
		RJMP Forever
