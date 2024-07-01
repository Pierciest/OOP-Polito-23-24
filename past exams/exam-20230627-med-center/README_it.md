Centro Medico
=============

Scrivi un programma per supportare la prenotazione e l'esecuzione di controlli medici in un centro medico.
Le classi si trovano nel pacchetto `it.polito.med`; la classe principale è `MedManager`. La classe `TestApp` nel pacchetto `example` mostra esempi di utilizzo dei metodi principali ed esempi dei controlli richiesti. 
Implementa solo i controlli richiesti. 
Le eccezioni nei metodi descritti di seguito sono del tipo `MedException`.

La [documentazione JDK](https://oop.polito.it/api/) si trova sul server locale.

La versione Inglese di questi requisiti è disponibile in [README.md](README.md).


R1: Clienti e Medici
--------------------

Il centro medico gestisce il lavoro di diversi medici, ognuno con una determinata specialità medica.

Il metodo `addSpecialities()` consente al centro medico di aggiungere un insieme di specialità mediche all'elenco delle specialità offerte. Può essere chiamato più volte, e i duplicati vengono ignorati.

Il metodo `getSpecialities()` recupera l'elenco delle specialità offerte dal centro medico. Restituisce una collezione di stringhe che rappresentano le specialità.

Il metodo `addDoctor()` consente al centro medico di aggiungere un nuovo medico insieme alla sua specialità. Richiede di fornire l'ID univoco del medico, il nome, il cognome e la specialità. Se l'ID è duplicato o la specialità non esiste, viene generata un'eccezione `MedException`.


Il metodo `getSpecialists()` recupera l'elenco dei medici specializzati in una determinata specialità. Richiede la specialità richiesta come parametro e restituisce una collezione di stringhe che rappresentano gli ID dei medici con quella specialità.

Il metodo `getDocName()` recupera il nome di un medico dato il suo ID. Richiede l'ID del medico come parametro e restituisce una stringa che rappresenta il suo nome.

Il metodo `getDocSurame()` recupera il cognome di un medico dato il suo ID. Richiede l'ID del medico come parametro e restituisce una stringa che rappresenta il suo cognome.

R2: Orario
-----------

I medici possono definire il loro orario per gli appuntamenti su base giornaliera.

Il metodo `addDailySchedule()` consente al centro medico di definire un orario per un medico in un giorno specifico. Vengono creati slot tra l'ora d'inizio e l'ora di fine fornite, con la durata di ogni slot espressa in minuti. Il metodo richiede l'ID del medico, la data, l'ora d'inizio, l'ora di fine e la durata come parametro. Restituisce il numero di slot definiti.


Il metodo `findSlots()` recupera gli slot disponibili in una determinata data per una specialità specifica. Il metodo restituisce una mappa che contiene una voce per ogni medico -- per la specialità specificata -- che ha slot pianificati nella data. Ogni voce nella mappa contiene una lista di slot descritti come stringhe nel formato `"hh:mm-hh:mm"`. Ad esempio, la stringa `"14:00-14:30"` rappresenta uno slot che inizia alle 14:00 e dura 30 minuti, fino alle 14:30. Il metodo richiede la data e la specialità richiesta come parametri e restituisce una mappa con gli ID dei medici come chiavi e le corrispondenti liste di stringhe di slot come valori.


R3: Appuntamenti
----------------

È possibile fissare appuntamenti con i pazienti negli slot pianificati.

Il metodo `setAppointment()` consente al sistema di definire un appuntamento per un paziente in uno slot esistente nell'orario di un medico. Richiede di fornire il codice fiscale del paziente, il nome, il cognome, l'ID del medico, la data dell'appuntamento e lo slot da prenotare. Il metodo restituisce un ID univoco per l'appuntamento. Se uno dei valori di codice, data o slot forniti non è valido, viene generata un'eccezione `MedException`.


I metodi `getAppointmentDoctor()`, `getAppointmentPatient()`, `getAppointmentTime()`, `getAppointmentDate()`, recuperano l'ID del medico, il codice fiscale del paziente, l'ora iniziale e la data per un determinato appuntamento. Richiedono l'ID dell'appuntamento come parametro.

Il metodo `listAppointments()` recupera l'elenco degli appuntamenti per un medico specifico in un determinato giorno. Gli appuntamenti vengono riportati come stringhe nel formato `"hh:mm=SSN"`, dove `"hh:mm"` rappresenta l'orario dell'appuntamento e `"SSN"` rappresenta il codice fiscale del paziente. Richiede l'ID del medico e la data richiesta come parametri e restituisce una collezione di stringhe di appuntamenti.


R4: Gestione dei Pazienti
-------------------------

Il metodo `setCurrentDate()` consente al centro medico di definire la data corrente per le operazioni di gestione dei pazienti. Questa data sarà utilizzata per accettare i pazienti che arrivano al centro, chiamare il paziente successivo e completare un appuntamento. Il metodo richiede la data come parametro e restituisce il numero totale di appuntamenti programmati per il giorno come intero.

Il metodo `accept()` segna un paziente come accettato dalla reception del centro medico. Richiede il codice fiscale del paziente come parametro.

Il metodo `nextAppointment()` restituisce il prossimo appuntamento per un paziente che è stato accettato dalla reception. Recupera l'ID del primo appuntamento in cui il paziente è stato accettato e l'appuntamento non è ancora completato. Se non è disponibile un appuntamento del genere, restituisce `null`. Il metodo richiede l'ID del medico come parametro e restituisce l'ID dell'appuntamento come stringa.

Il metodo `completeAppointment()` segna un appuntamento come completato. Accetta l'ID del medico e l'ID dell'appuntamento come parametri. Genera un'eccezione nel caso in cui il codice o il codice dell'appuntamento non siano validi, o l'appuntamento sia con un altro medico o un paziente non accettato o l'appuntamento non sia per il giorno corrente.


R5: Statistiche
---------------

Il metodo `showRate()` calcola il tasso di presenza per gli appuntamenti di un medico in una determinata data. Il tasso viene calcolato come rapporto tra i pazienti accettati e il numero totale di appuntamenti. Richiede l'ID del medico e la data di riferimento come parametri e restituisce il tasso di mancata presenza come numero decimale.

Il metodo `scheduleCompleteness()` calcola la completezza dell'orario per tutti i medici del centro medico. La completezza per un medico viene calcolata come rapporto tra il numero di appuntamenti pianificati e il numero totale di slot nel loro orario. Il metodo restituisce una mappa che associa l'ID del codice di ogni medico alla loro completezza relativa.