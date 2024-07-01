Biblioteca
==============

Scrivere un programma per supportare il noleggio e l'archiviazione dei libri in una biblioteca pubblica.
Le classi si trovano nel package `it.polito.library`; la classe principale è `LibraryManager`.  La classe `TestExample` nel package `example` mostra esempi di utilizzo per i metodi principali ed esempi dei controlli richiesti.  È necessario implementare solo i controlli richiesti. Le eccezioni nei metodi descritti di seguito sono di tipo `LibException`.

La [documentazione JDK](https://oop.polito.it/api/) si trova sul server locale.

La versione inglese di questi requisiti è disponibile in [README.md](README.md).


R1: Lettori e libri
-------------------

La biblioteca ha in archivio diversi libri, ciascuno con un determinato titolo. È possibile archiviare più copie dello stesso libro, ognuna delle quali ha un ID libro univoco. Ogni copia di un libro è collegata all'elenco dei suoi noleggi passati e al suo stato di noleggio attuale.

Il metodo `addBook()` consente di aggiungere un nuovo libro all'archivio dei libri disponibili per il noleggio. Può essere invocato più volte e, nel caso in cui il libro aggiunto sia un duplicato (cioè abbia lo stesso titolo di un libro già presente nell'archivio), il metodo aggiorna il numero di copie disponibili per il libro. Ogni copia del libro ha un ID libro univoco. Gli ID libro sono codici assegnati in modo incrementale a partire da `1000`. Il metodo restituisce, come valore `String`, l'ID libro univoco assegnato al libro aggiunto. 

Il metodo `getTitles()` restituisce i titoli dei libri disponibili nella biblioteca, ordinati alfabeticamente, ciascuno collegato al numero di copie disponibili per quel titolo. Restituisce la `SortedMap<String, Integer>` dei titoli in relazione al numero di copie disponibili.

Il metodo `getBooks()` restituisce l'insieme (`Set<String>`) degli ID univoci delle copie dei libri disponibili nella libreria.

Il metodo `addReader()` permette alla libreria di aggiungere un nuovo lettore. È necessario fornire il nome e cognome del lettore (come due valori `Stringa`). Gli ID dei lettori sono codici univoci assegnati in modo incrementale a partire da `1000` a ogni nuovo lettore. L'archivio della biblioteca accetta lettori con lo stesso nome e cognome e assegna loro semplicemente ID diversi. 

Il metodo `getReaderName()` ottiene il nome di un lettore dato il suo ID lettore. Prende in ingresso l'ID del lettore e restituisce un valore `String` che rappresenta il suo nome e cognome nel formato `"Nome Cognome"`. Se l'ID non esiste nell'archivio, viene lanciata una `LibException`.

R2: Gestione dei noleggi
-----------

I lettori possono noleggiare un solo libro alla volta. 


Il metodo `getAvailableBook()` ottiene una copia del libro disponibile per il noleggio, se disponibile. Il metodo prende in ingresso il titolo del libro e restituisce, come valore `String`, l'ID libro univoco della prima copia disponibile per un libro, dove le copie del libro sono ordinate per ID. Se tutte le copie sono a noleggio, viene restituito il valore `"Non disponibile"`. Se il titolo non è presente nell'archivio della biblioteca, viene lanciata una `LibException`.

Il metodo `startRental()` attiva il noleggio di un libro per un lettore. Come ingresso riceve l'ID univoco del libro, l'ID univoco del lettore e la data d'inizio. Se lo stesso lettore noleggia due volte la stessa copia unica del libro, il secondo noleggio sovrascrive il primo. Se il libro o il lettore non sono registrati nell'archivio della biblioteca o se uno dei due è attualmente coinvolto in un noleggio, viene lanciata una `LibException`.

Il metodo `endRental()` termina il noleggio di un libro specifico per un lettore specifico. I noleggi vengono terminati definendo la loro data di fine. Questo metodo aggiorna l'elenco dei noleggi del libro e lo stato di noleggio del lettore. Come ingresso riceve l'ID univoco del libro, l'ID univoco del lettore e la data di fine. Se il libro o il lettore non sono registrati nell'archivio della biblioteca, viene lanciata una `LibException`.

Il metodo `getRentals()` ottiene l'elenco dei lettori che hanno noleggiato un libro specifico. Prende in ingresso l'ID univoco del libro e restituisce la mappa (come `SortedMap<String, String>`) degli ID univoci dei lettori collegati ai noleggi (che sono valori `String` nel formato `"1000 GG-MM-AAAA GG-MM-AAAA"`). Se un noleggio è in corso, viene riportata solo la data d'inizio e la parola `"ONGOING"` al posto della data di fine (nel formato `"1000 GG-MM-AAAA ONGOING"`).


R3: Donazioni di libri
----------------

Il metodo `receiveDonation()` raccoglie i libri donati alla biblioteca. Prende in ingresso un elenco di titoli di libri (come un unico valore `Stringa` nel formato `"Primo titolo,Secondo titolo"`; i titoli contengono parole separate solo da `" "`). Questo metodo aggiunge i titoli ricevuti all'archivio della biblioteca. 


R4: Gestione dell'archivio
----------------

Il metodo `getOngoingRentals()` ottiene tutti i noleggi attivi come una `Map<String, String>` che collega gli ID dei lettori con un noleggio in corso e gli ID delle copie libri che stanno noleggiando. 

Il metodo `removeBooks()` rinnova la collezione di libri rimuovendo dagli archivi tutte le copie di libri, indipendentemente dal titolo, che non sono mai state noleggiate.


R5: Statistiche
---------

Il metodo `findBookWorm()` trova il lettore con il maggior numero totale di noleggi e ne restituisce l'ID univoco come valore `String` nel formato `"Nome Cognome"`.

Il metodo `rentalCounts()` restituisce il numero totale di di noleggi (both terminati che in corso) per ciascun titolo presente nella biblioteca, sotto forma di una mappa che associa il titolo come chiave al conteggio.
