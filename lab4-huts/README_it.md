# OOP Lab 4 - Rifugi di montagna

Sviluppare un'applicazione che consenta di gestire le informazioni sui rifugi di montagna di una regione.

L'applicazione deve permettere di fornire le informazioni su intervalli di altitudine, comuni e rifugi di montagna.

Tutte le classi devono essere nel package `mountainhuts`.

(*L'obiettivo di questo lab è l'uso delle Stream API*)

## R1 - Intervalli di altitudine

L'interazione con il sistema avviene tramite la classe `Region`. Il metodo `getName()` della classe `Region` 
restituisce il nome della regione specificato nel suo costruttore.

I rifugi sono classificati in base a delle fasce di altezza, che possono essere definite secondo le necessità. 
Per definire gli intervalli di altitudine disponibili si utilizza il metodo `setAltitudeRanges()` che riceve come parametro un vettore di stringhe. Ogni stringa rappresenta un intervallo di altitudine utilizzando il formato `"[minValue]-[maxValue]"`. Per esempio, l'intervallo `"0-1000"` rappresenta le altitudini da 0 a 1000 metri sul livello del mare, estremo superiore incluso ed estremo inferiore escluso. 
Si supponga che gli intervalli siano tra loro disgiunti.

Il metodo `getAltitudeRange()` riceve come parametro il valore di un'altitudine e ritorna la rappresentazione testuale dell'intervallo (tra quelli predefiniti) in cui tale altitudine è presente, estremo superiore incluso. 
Se non è disponibile alcun intervallo per tale altitudine, il metodo ritorna l'intervallo di default `"0-INF"`.


## R2 - Comuni e rifugi di montagna

I comuni sono definiti utilizzando il metodo `createOrGetMunicipality()` che accetta come parametri la denominazione univoca del comune, la provincia e l'altitudine. Il metodo restituisce un oggetto di classe `Municipality`. 
Se un comune con la stessa denominazione è stato creato in precedenza, il metodo ritorna il comune già disponibile, ignorando gli altri parametri.

I rifugi di montagna sono definiti utilizzando il metodo `createOrGetMountainHut()` che accetta come parametri la denominazione univoca del rifugio, la categoria, il numero di posti letto e il comune in cui è situato. 
Il metodo `createOrGetMountainHut()` accetta opzionalmente il parametro relativo all'altitudine del rifugio. 
Il metodo restituisce un oggetto di classe `MountainHut`. Se un rifugio con la stessa denominazione è stato creato in precedenza, il metodo ritorna il rifugio già disponibile, ignorando gli altri parametri.

Le classi `Municipality` e `MountainHut` offrono gli ovvi metodi getter. Il metodo `getAltitude()` della classe `MountainHut` restituisce un `Optional` che è vuoto (empty) se l'altitudine del rifugio non è stata specificata.

È possibile ottenere le collezioni dei comuni e rifugi disponibili utilizzando i metodi `getMunicipalities()` e `getMountainHuts()`.


Suggerimenti:

* La classe `Optional` viene utilizzata per segnalare esplicitamente che un valore può essere `null`. 
  Il metodo `isPresent()` indica se è presente un valore nell' `Optional`.
* Per creare un `Optional` a partire da una variable che potrebbe essere null si può usare `Optional.ofNullable()` che restituisce un `Optional` contenente la variabile oppure un `Optional` vuoto se la variabile è `null`.


## R3 - Lettura da CSV

È possibile, tramite il metodo statico `fromFile()`, creare un oggetto Region a partire dal nome e dalle informazioni contenute in un file, passati come parametri. Le informazioni sono relative alle classi illustrate sopra. 
Il metodo deve popolare la classe regione creando gli oggetti comune e rifugio descritti nel file.

Le informazioni relative ai rifugi della regione sono memorizzati in un file con codifica CSV che è strutturato in base alle seguenti colonne che contengono informazioni relative a comuni e rifugi di montagna:

| N | Columns				       | `Municipality`| `MountainHut`|
|---|----------------------|:-------------:|:------------:|
| 0	| Province				     |	✓			       |			        |
| 1	| Municipality			   |	✓			       |			        |
| 2	| MunicipalityAltitude |	✓			       |			        |
| 3	| Name					       |				       |		✓         |
| 4	| Altitude				     |				       |		✓	        |
| 5	| Category				     |				       |		✓	        |
| 6	| BedsNumber				   |				       |		✓	        |


Attenzione: il file contiene una riga per ogni rifugio, quindi le informazioni relative ai comuni possono essere replicate più volte.

I campi del file CSV sono separati da un punto e virgola (`;`). La colonna relativa all'altitudine del rifugio può essere vuota se tale informazione non è disponibile.

I dati relativi ai rifugi della regione Piemonte sono disponibili nel file: `data/mountain_huts.csv`(*)


Suggerimenti

* Per la lettura da file, si utilizzi il metodo `readData()`, che legge le linee di un file di testo e restituisce una lista contenete le righe. 
  La prima riga del file contiene le intestazioni, mentre quelle successive contengono i dati veri e propri.


## R4 - Interrogazioni

Il metodo `countMunicipalitiesPerProvince()` restituisce una mappa contenente come chiave il nome di una provincia e come valore il numero dei comuni in tale provincia.

Il metodo `countMountainHutsPerMunicipalityPerProvince()` restituisce una mappa contenente come chiave il nome di una provincia e come valore un'altra mappa che fa corrispondere al nome di un comune il numero dei rifugi in esso presenti.

Il metodo `countMountainHutsPerAltitudeRange()` restituisce una mappa contenente come chiave gli intervalli di altitudine restituiti dal metodo `getAltitudeRanges()` e come valore il numero dei rifugi con un'altitudine inclusa in tale intervallo. Se l'altitudine del rifugio non è disponibile, si consideri l'altitudine del comune corrispondente.

Il metodo `totalBedsNumberPerProvince()` restituisce una mappa contenente come chiave il nome di una provincia e come valore il numero totale dei posti letto disponibili nei rifugi situati in tale provincia.

Il metodo `maximumBedsNumberPerAltitudeRange()` restituisce una mappa contenente come chiave gli intervalli di altitudine restituiti dal metodo getAltitudeRange() e come valore il numero massimo dei posti letto disponibili nei rifugi con un'altitudine inclusa in tale intervallo. Se l'altitudine del rifugio non è disponibile, si consideri l'altitudine del comune corrispondente.

Il metodo `municipalityNamesPerCountOfMountainHuts()` restituisce una mappa contenente come chiave il numero dei rifugi per comune e come valore una lista contenente i nomi dei comuni aventi tale numero di rifugi ordinata alfabeticamente.

Suggerimento:
* per svolgere le interrogazioni si consiglia di utilizzare gli Stream API che permettono di scrivere codice più compatto e più comprensibile, rispetto a esplicite iterazioni su collezioni e mappe.

---

(*): il file contiene una versione semplificata dei dati disponibili sul portale open data della regione, in particolare <https://www.dati.piemonte.it/#/catalogodetail/regpie_ckan_ckan2_yucca_sdp_smartdatanet.it_RifugiOpenDa_2296>
