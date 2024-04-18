# OOP Lab 2 - Simulazione Idraulica

(La versione inglese è disponibile nel file [README.md](README.md)).

Realizzare il sistema software per la descrizione e la simulazione di un sistema idraulico.

Tutte le classi si trovano nel package `hydraulic`.


## R1: Elementi e Tubi

Un sistema idraulico è composto da elementi di vario tipo connessi tra loro (tramite tubi che però non sono modellati esplicitamente con questo software).

Un sistema idraulico è rappresentato da un oggetto di classe `HSystem`; questa classe permette di aggiungere nuovi elementi tramite il metodo `addElement()`, il quale riceve come parametro un `Element` e lo aggiunge ai componenti che formano il sistema idraulico.

Tramite il metodo `getElements()` è possibile ottenere un array contenente tutti e soli gli elementi presenti nel sistema, questo metodo restituisce un array di `Element`.

Tutti gli elementi hanno un nome che può essere letto tramite il metodo `getName()`.

Suggerimenti:

- 👉 si assuma che il numero massimo di elementi di un sistema idraulico sia 100. 


## R2: Elementi semplici

Sono definiti tre tipi di elementi semplici: sorgente, rubinetto e scarico, che sono rispettivamente rappresentati dalle classi `Source`, `Tap` e `Sink`.

È possibile connettere l'uscita di un elemento all'ingresso di un altro tramite il metodo `connect()`; il metodo riceve come parametro l'elemento al cui ingresso deve essere connessa l'uscita dell'elemento sui cui è invocato: ad esempio, `a.connect(b);` connette l'uscita di `a` all'ingresso di `b`. Il metodo `connect()` se invocato su un oggetto `Sink` non ha nessun effetto.

Dato un elemento semplice qualunque, è possibile sapere a quale altro elemento è connessa la sua uscita, tramite il metodo `getOutput()` che restituisce un riferimento di tipo `Element`.


## R3: Elementi complessi

Oltre agli elementi semplici, sopra descritti, è possibile utilizzare degli elementi complessi. L'elemento a *T*, rappresentato dalla classe `Split`, permette di suddividere l'ingresso in due flussi in uscita uguali tra loro. Per tale classe il metodo `connect()` riceve un ulteriore parametro, di tipo intero, che indica il numero dell'uscita a cui connettere l'elemento. Tale intero ha valore 0 per la prima uscita e 1 per la seconda.

Per sapere quali elementi sono connessi in uscita a un elemento a *T*, è possibile utilizzare il metodo `getOutputs()` che restituisce un array con i due elementi connessi.


## R4: Simulazione

Dato un sistema corretto, ovvero un albero che ha come radice una sorgente e in cui ogni percorso termina con uno scarico, è possibile fare un calcolo delle portate e di come vengono ripartite nei vari elementi.

Il calcolo prevede due fasi: una prima fase (setup) i cui si definiscono i parametri dei diversi elementi del sistema e una seconda fase (simulazione) in cui si avvia la simulazione.

Durante la fase si setup è possibile:

- definire la portata per una sorgente (`Source`) con il metodo `setFlow()`, che riceve come parametro un numero reale che rappresenta i metri cubi al secondo erogati dalla sorgente
- impostare l'apertura dei rubinetti (`Tap`), tramite il metodo `setOpen(`), che riceve come parametro un boolean. Se un rubinetto è aperto la portata in uscita è uguale a quell in ingresso, altrimenti la portata in uscita è nulla (`0.0`).

Per i raccordi a *T* la portata in ingresso viene ripartita equamente tra le due uscite.

Il metodo `simulate()` della classe `HSystem`, effettua i calcoli di portata a partire da ogni sorgente e notifica, per ogni elemento: il nome e le portate in ingresso e in uscita. Questo metodo richiede come parametro un oggetto che implementa l'interfaccia `SimulationObserver`, che presenta un unico metodo.

Quando, durante la simulazione, risultano noti i flussi in entrata e in uscita per un elemento, deve essere invocato il metodo `notifyFlow()` dell'*observer* passando il tipo di elemento (nome della classe), il nome dell'elemento, e i flussi in ingresso e uscita; se un flusso non è definito (ad es. per ingresso per `Source` e uscita per `Sink`) si usa la costante `NO_FLOW` definita nell'interfaccia.

Suggerimenti:

- 👉 Suggerimento: dato un oggetto, per sapere se è un'istanza di una classe si può usare l'operatore `instanceof`. 
	Es. `if(element instanceof Source)` verifica se `element` è di classe `Source`

- **Attenzione**: non è richiesto di implementare l'interfaccia `SimulationObserver` ma solamente usarla; per scopi di verifica viene fornito un esempio di implementazione (la classe `PrintingObserver`) che semplicemente stampa su console le notifiche.


## R5. Multi-split

La classe `Multisplit` rappresenta un'estensione della classe `Split` che prevede più uscite. Il costruttore accetta, oltre al nome, il numero di uscite.

Il metodo `connect()` riceve due parametri: l'elemento da collegare ed il numero dell'uscita a cui collegarlo. Le uscite sono numerate a partire da 0.

Per sapere quali elementi sono connessi in uscita a un multisplit, è possibile utilizzare il metodo `getOutputs()` che restituisce un array con gli elementi connessi. Se nessun elemento è connesso ad una certa uscita, la cella corrispondente dell'array è `null`.

In preparazione alla simulazione è possibile utilizzare il metodo `setProportions()` che accetta una serie di `double` che definiscono la proporzione con cui ripartire il flusso in ingresso sulle uscite.

Suggerimenti:

- 👉 Si assuma che il numero di proporzioni passate al metodo sia pari al numero di uscite e che la loro somma sia pari a `1.0`.



## R6. Visualizzazione

Il metodo `layout()` della classe `HSystem` restituisce una stringa che contiene la disposizione degli element del sistema utilizzando caratteri ASCII e opportune spaziature.

Ogni elemento viene stampato nella forma `"[name]Type"` dove *name* è il nome dell'elemento e *Type* è la classe dell'elemento. La connessione tra uscita di un elemento e ingresso di un altro è rappresentata dal simbolo `"->"` mentre l'assenza di un elemento connesso in uscita è rappresentata da `"*"`. Nel caso di più elementi connessi a valle di un elemento si usa il carattere `"+"` e si riporta la prima connessione sulla stessa riga, e in righe successive si ripete `"+"` (allineato verticalmente al primo) e poi si riportano le altre connessioni. Le righe che separano le connessioni multiple (almeno una, ma potrebbero essere più di una se a valle ci sono altri elementi con uscite multiple) si riporta il carattere `"|"` allineato verticalmente a `"+"`.

Per esempio, un sistema composto da una `Source` connessa a un `Tap`, che è connesso a uno `Split` le cui uscite sono connesse a due `Sink`, avrebbe un layout simile al seguente:

```
[Src]Source -> [R]Tap -> [T]Split +-> [sink A]Sink
                                  |
                                  +-> [sink B]Sink 
```

Suggerimenti:

- 👉 Si tenga presente che il sistema potrebbe non essere completo, ovvero alcune uscite di elementi potrebbero non essere connesse ad alcun elemento.


## R7. Eliminazione elementi

Il metodo `deleteElement()` della classe `HSystem` permette di eliminare un elemento precedentemente aggiunto al sistema; il metodo accetta come parametro il nome dell'elemento da eliminare.

Se l'elemento è uno `Split` o un `Multisplit` con più di una uscita connessa, non viene effettuata alcuna operazione e il metodo restituisce `false`.

Altrimenti (`Split` o `Multisplit` con al più una uscita connessa oppure un altro tipo di elemento) l'elemento viene rimosso dal sistema e, se l'elemento da eliminare è connesso ad altri elementi in ingresso e/o in uscita, occorre modificare le connessioni di conseguenza in modo che l'elemento a monte sia connesso a quello a valle. In questo secondo caso il metodo restituisce `true`. 

Riprendendo l'esempio in [R6](#r6-visualizzazione), il layout dopo l'eliminazione di `R` deve essere il seguente:

```
[Src]Source -> [T]Split +-> [sink A]Sink
                        |
                        +-> [sink B]Sink
```


## R8. Portata massima elementi e allarmi

La classe Element offre il metodo `setMaxFlow()`, che accetta come parametro un numero reale che rappresenta la portata massima di un elemento. Se un elemento riceve una portata in ingresso maggiore della portata massima, l'elemento rischia di rompersi. Per gli oggetti di classe `Source`, non avendo questi ultimi alcun ingresso, le chiamate al metodo `setMaxFlow()` non devono sortire alcun effetto.

La classe `HSystem` contiene un overload del metodo `simulate()` che accetta come parametro il valore booleano `enableMaxFlowCheck`: se quest'ultimo è settato a vero, occorre effettuare i controlli sulla portata massima. Il metodo accetta come primo parametro un oggetto che implementa l'interfaccia `SimulationObserver`; quando l'input flow di un elemento è maggiore del suo flusso massimo consentito, occorre notificare l'errore invocando il metodo `notifyFlowError()` dell'osservatore, passando il tipo di elemento (nome della classe), il nome dell'elemento, il flusso in ingresso e la sua portata massima.


## R9. Fluent builder

La classe `HBuilder` implementa un *builder* che usa la concatenazione di metodi per fornire una [Fluent API](https://www.martinfowler.com/bliki/FluentInterface.html).

Il *builder* è creato con il metodo `build()` nella classe `HSystem`.

Per quanto riguarda la struttura del systema, i seguenti metodi sono disponibili:


- una nuova sorgente è aggiunta con il metodo `addSource()` che accetta il nome della stessa.

- un nuovo rubinetto è aggiunto e collegato all'elemento precedente con il metodo  `linkToTap()` che ne accetta il nome.

- un nuovo scarico è aggiunto e collegato all'elemento precedente con il metodo  `linkToSink()` che ne accetta il nome.

- un nuovo split è aggiunto e collegato all'elemento precedente con il metodo  `linkToSplit()` che ne accetta il nome.

- un nuovo multi split è aggiunto e collegato all'elemento precedente con il metodo `linkToMultisplit()` che ne accetta il nome ed il numero di uscite.

- quando uno split o un multisplit viene creato, le differenti uscite possono essere specificate con il metodo `withOutputs()` che introduce tutti gli elementi in uscita:
    - gli elementi collgati alle uscite sono definiti con i metodi `linkTo..` descritti sopra

- per indicare l'elemento per la prossima uscita di un (multi)split is usa il metodo `then()`

- per indicare che tutte le uscite di un (multi)split sono state collegate si usa il metodo `done()`

- alla fine il metodo `complete()` può essere usato per ottenere l'oggetto `HSystem` costruito.

### Esempio

Il codice seguente:

```
HSystem s = HSystem.build().
        addSource("Src")
        linkToMultisplit("MS",3).withOutputs().
            linkToSplit("T").withOutputs().
                linkToSink("S1").
                then().linkToSink("S2").
                done().
            then().linkToSink("S3").
            then().linkToSink("S4").
        complete();
```

produce il seguente sistema:

```
[Src]Source -> [MS]Split +-> [T]Split +-> [S1]Sink
                         |            |
                         |            +-> [S2]Sink
                         |
                         +-> [S3]Sink
                         |
                         +-> [S4]Sink
```

Per quanto riguarda i parametri di simulazione, si possono utilizzare i seguenti metodi subito dopo aver definito gli elementi corrispondenti:

- `open()` e `closed()` per definire l'apertura di un rubinetto,
- `withFlow()` per definire il flusso di una sorgente,
- `withProportions()` per definire le proporzioni di ripartizione di uno split.

Infine, per il controllo dei flussi massimi, il metodo `maxFlow()` può essere usato dopo un elemento per definire il flusso massimo consentito.

---

Versione 1.1.0 - 2024-03-18
