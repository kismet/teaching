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

;Problema:
;	Scrivere un programma che legge quando viene premuto PB0, genera un onda
;   quadra con Duty Cycle letto da PC2:PC0 fino a quando non si preme nuovamente
;   PB0. Ad ogni periodo, il Duty Cycle del segnale viene aggiornato in base
;   al valore di PC2:PC0. In particolare 7 corrisponde a D.C. = 100%, mentre 0
;	corrisponde a D.C. = 12,5% c.a.
;	Simulare il programma configurando LED, Pulsanti ed interruttori in maniera
;   opportuna
;
;Riflessioni:
;	1. Qual'è il periodo del segnale?
;	2. Il codice usa le interupt? Se non le usa come andrebbe modificato per 
;	   usarele?
; 
; Autore: prof. LENZI Stefano <stefano@lenzi.pro>
*/ 

#include <avr/io.h>

unsigned char acceso = 0;

/*
 Restitusce 1 se PB0 è premuto, 0 altrimenti
 */
int premutoPB0(){
	return PINB & 0x01;
}

//periodo T = 
void setup(){
	TCCR0A = 0x00;
	TCCR0B = 0x05;
	DDRB = 0xFE;
	DDRC = 0x00;
}

void generaSegnale(){
	/*
	 * RESET del timer perchè il tasto viene premuto in maniera asincrona 
	 * rispetto all'avvio del Timer
	 */
	TIFR0 |= (1<<TOV0);
	TCNT0 = 0;
	/* Si calcola il tipo di segnale da generare */
	unsigned char decimal = PINC & 0x07;
	decimal++;
	unsigned char overflow = decimal*3;
	unsigned char PERIOD = 0x08*3;
	unsigned char tick = 0;
	/* 
	 * Si invia HIGH per il tempo necessario a 
	 * generare un segnale con il Duty Cycle letto
	 * dal valore della Porta Digitale C
	 */
	PORTB |= 0x80;
	while(tick < overflow){
		if( (TIFR0 & (1<<TOV0) )  != 0){
			TIFR0 |= (1<<TOV0);
			tick++;
		}
		/*
		 * Questo è il codice per ATTESA ATTIVA del overflow del Timer, ma nel 
		 * mentre dobbiamo verificare se si preme il tasto PB0, in quel caso 
		 * spegnendo la generazione del segnale
		 */
		if(premutoPB0()) {
			PORTB &= 0x7F;
			acceso = 0;
			return;
		}
	}
	/* 
	 * Si invia LOW per il tempo necessario a generare un segnale con il Duty Cycle letto
	 * dal valore della Porta Digitale C
	 */
	PORTB &= 0x7F;
	while(tick < PERIOD){
		if( ( TIFR0 & (1<<TOV0) ) != 0){
			TIFR0 |= (1<<TOV0);
			tick++;
		}
		if(premutoPB0()) {
			PORTB &= 0x7F;	
			acceso = 0;
			return;
		}
	}
}


void loop(){
	if(acceso != 0 ){
		generaSegnale();
	}else{
		acceso = premutoPB0();
	}	
}

int main(void)
{
	setup();
    while (1 == 1) 
    {
		loop();
    }
}

