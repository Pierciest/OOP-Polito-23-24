# OOP Lab 5 - Ambulatorio Medico

Verisone inglese dei requisiti qui: [README.md](README.md).

Realizzare un sistema per gestire i pazienti di un ambulatorio medico. Le classi sono contenute nel package `clinic`.


## R1: Pazienti

La classe principale del programma è `Clinic`.

I pazienti sono caratterizzati da nome, cognome, e dal codice fiscale (SSN). Nuovi pazienti possono essere aggiunti tramite il metodo `addPatient()`.

Le informazioni su un paziente possono essere recuperate tramite il metodo `getPatient()` che, dato un codice fiscale restituisce una stringa con il seguente formato `"<Last> <First> (<SSN>)"`.

Se il paziente non esiste viene generata una eccezione `NoSuchPatient`.


## R2: Dottori

I dottori sono caratterizzati da nome, cognome, SSN, numero di badge e dalla specializzazione (es. `"cardiologo"`, `"dentista"`). È possibile aggiungere un nuovo dottore tramite il metodo `addDoctor()`.
Il metodo `getDoctor()`, dato il numero di badge restituisce una stringa con il seguente formato `"<Last> <First> (<SSN>) [<ID>]: <Specialization>"`.

Se il dottore non esiste viene generata una eccezione `NoSuchDoctor`.

Si tenga presente che un dottore potrebbe essere paziente dello stesso ambulatorio.


## R3: Registrazione dei pazienti

Quando sono accettati, i pazienti vengono assegnati a uno dei dottori dell'ambulatorio. Per questo si usa il metodo `assignPatientToDoctor()`, che accetta come parametri il codice fiscale del paziente e il numero di badge del dottore. Se il dottore non esiste, viene generata una eccezione `NoSuchDoctor`, mentre se il paziente non esiste viene generata una eccezione `NoSuchPatient`.
Se il metodo `assignPatientToDoctor()` viene chiamato più volte per lo stesso paziente, viene considerato solo l'ultimo dottore assegnato.

Tramite il metodo `getAssignedDoctor()` che accetta il codice fiscale di un paziente è possibile ottenere il codice del dottore assegnato al paziente. Se il paziente non esiste viene lanciata una eccezione `NoSuchPatient`; se il paziente non è stato assegnato ad alcun dottore viene lanciata una eccezione `NoSuchDoctor`.

Il metodo `getAssignedPatients()` accetta il codice di un dottore e restituisce la collezione dei codici fiscali dei pazienti assegnati al dottore. Se il codice non corrisponde ad alcun dottore, viene lanciata una eccezione `NoSuchDoctor`.


## R4: Lettura

Il metodo `loadData()` della classe `Clinic` accetta un `Reader` da cui legge le informazioni su pazienti e dottori.

Il file è organizzato per righe; ogni riga contiene informazioni relative a pazienti o a dottori.

Le righe contenenti informazioni sulle persone iniziano con la lettera `"P"` seguita da nome, cognome, e SSN. Le righe che descrivono i dottori iniziano con la lettera `"M"`, seguita da numero di badge, nome, cognome, SSN e specialità.

Tutti gli elementi su una linea sono separati dal carattere `';'`, eventualmente circondato da spazi che devono essere ignorati.

In caso di errore nei contenuti del file, il metodo ignora la linea e passa a quella successiva.

In caso di errori di I/O le eccezioni devono essere propagate al chiamante.

Il metodo restituisce il numero di righe correttamente lette.

Una seconda versione del metodo `loadData()` accetta un secondo parametro di tipo ErrorListener. Questa interfaccia definisce il metodo `offendingLine()` che accetta un intero ed una stringa. Durante la lettura, ogni volta che viene incontrata una linea errata (che deve essere scartate) il metodo `loadData()` deve invocare `offendingLine()` passando il numero d'ordine (la prima linea è 1) contenuto della linea.


## R5: Statistiche

Il metodo `idleDoctors()` restituisce una collezione con tutti i dottori che non hanno pazienti, ordinata in ordine alfabetico (prima per cognome e poi per nome).

Il metodo `busyDoctors()` restituisce la collezione dei dottori che hanno un numero di pazienti superiore alla media.

Il metodo `doctorsByNumPatients()` restituisce una collezione di stringhe contenenti il nome del dottore e il relativo numero di pazienti ordinati in maniera decrescente di numero.
Le stringhe devono essere formattate come `"### : ID SURNAME NAME"` dove ### rappresenta il numero di pazienti (stampato su tre caratteri).

Il metodo `countPatientsPerSpecialization()` conta il numero di pazienti per specializzazione (dei dottori). Gli elementi della lista di stringhe sono ordinati prima per numero decrescente di pazienti e poi in ordine alfabetico di specializzazione.
Le stringhe sono strutturate come `"### - SPECIALITY"` dove ### rappresenta il numero di pazienti (stampato su tre caratteri). Le specializzazioni senza pazienti devono essere omesse.

Suggerimento:

* Per formattare il numero di pazienti si può utilizzare il metodo `String.format()`. 
  Ad esempio `String.format("%2d", i)` stampa una variabile intera `i` con una larghezza di due caratteri.