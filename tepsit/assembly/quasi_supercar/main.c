/*
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

;Problema - Intermedio 4:
;	Accendere in sequenza dal primo all’ultimo i LED e poi dall’ultimo al 
;	primo (facendo in modo che un led sia sempre acceso) collegati sulla Porta C
;	ogni 0,25s usando il Timer0. 
;	OPZIONALE:Usare una Macro per accendere e spegnere i LED in maniera corretta.
;
;Riflessioni: Trovate dei commenti come riflessioni al problema
;
; Autore: prof. LENZI Stefano <stefano@lenzi.pro>
*/


#include <avr/io.h>
//Che cosa rappresenta la variabile LEDS? Che tipo di variabile è?
unsigned char LEDS = 0x00;

void setup(){
	DDRC = 0xFF;
	TCCR0A = 0x00;
	TCCR0B = 0x01;
	LEDS = 0x01;
	PORTC = LEDS;
}

void waitOverflow(){
	while( ( TIFR0 & (1<<TOV0) ) == 0){
		//do nothing;
	}
	TIFR0 |= (1<<TOV0);
}

void wait2seconds(){
	const long TWO_SECONDS = 125000L / 2;
	long couting = 0;
	while(couting < TWO_SECONDS){
		waitOverflow();
		couting++;
	}	
}
void supercarSXtoDX(){
	wait2seconds();
	LEDS = ( LEDS << 1 );
	if (LEDS == 0x80 ) //Perchè si fa il confronto con 0x80 e non con 0x00 ?
	{
		//Perchè qui si inizializza ad UNO?
		LEDS = 0x01;
	}
	PORTC = LEDS;
}

int main(void)
{
	setup();
    while (1 == 1) 
    {
		/*
		 * Varianti:
		 *	1. Come si può modificare il codice per far andare i LED da DX a SX?
		 *	2. Come si può modificare il codice per far rimbalzare i LED tra SX e DX?
		 */
		supercarSXtoDX();
    }
}

