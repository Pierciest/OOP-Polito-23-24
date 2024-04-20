# OOP Lab 1 - Università

(the English version is available in file [README.md](README.md)).

Progettare e realizzare un programma per la gestione di insegnamenti universitari, docenti e studenti.

Tutte le classi devono appartenere al package `university`.

## R1. Università

L'interfaccia utente (non parte di questo esercizio) interagisce attraverso la classe `University` il cui costruttore accetta come argomento il nome dell'università.

Il nome dell'università può essere recuperato tramite il metodo `getName()`.

È possibile assegnare il nome del rettore tramite il metodo  `setRector()` che accetta come parametri il nome e cognome del rettore.

Il metodo `getRector()` restituisce un'unica stringa con nome e cognome del rettore separati da uno spazio (`" "`).

## R2. Students

È possibile inserire le informazioni relative ad un nuovo studente tramite il metodo  `enroll()` della classe `University`, che accetta come parametri il nome e cognme dello studente; il metodo restituisce l'identificatore numerico assegnato allo studente.
Gli ID numerici sono assegnati progressivamente in ciascuna università a partire dal numero 10000.

Per ottenere le informazioni relative ad uno studente è possibile usare il metodo `student()` che accetta come parametri l'ID e restituisce una stringa composta da ID, nome e cognome separati da spazi, es. `"10000 Donald Duck"`.

Suggerimenti:

- &#9758; Possiamo assumere che ogni università abbia al più 100 studenti iscritti.

## R3. Corsi

Per definire un nuovo corso possiamo utilizzare il metodo `activate()` che accetta come argomenti il titolo del corso e il nome del docente responsabile. Il metodo restituisce un intero che corrisponde al codice del corso. 
I codici dei corsi sono assegnati in modo progressivo a partire dal numero 10.

Per ottenere le informazioni su un corso possiamo utilizzare il metodo `course()` che accetta il codice del corso e restituisce una stringa contenente il codice, il titolo e il nome del docente separati da virgola, ad esempio `"10,Programmazione ad Oggetti,James Gosling"`.

Suggerimenti:

- &#9758; Possiamo assumere in che ogni università offra non più di 50 corsi.

## R4. Partecipazione ai corsi

Gli studenti che desiderano partecipare a un corso devono essere registrati tramite il metodo `register()` che accetta come argomenti l'ID dello studente e il codice del corso.

Per ottenere l'elenco degli studenti che partecipano a un corso possiamo utilizzare il metodo `listAttendees()` che accetta il codice del corso e restituisce una stringa contenente l'elenco degli studenti. 
Gli studenti appaiono uno per riga (le righe sono terminate da un carattere di a-capo `'\n'`) e ciascuna riga è formattata come descritto nel requisito R2.

Dato l'ID di uno studente, è possibile ottenere l'elenco dei corsi ai quali partecipa tramite il metodo `studyPlan()`. I corsi sono descritti uno per riga e formattati come descritto nel requisito R3.

Suggerimenti:

- &#9758; Possiamo assumere che nessun corso abbia più di 100 partecipanti e che ogni studente possa partecipare a non più di 25 corsi distinti.


--- 

**Requisiti aggiuntivi** (pensati per la pratica autonoma a casa)


## R5. Esami

Gli studenti possono sostenere gli esami per i corsi a cui sono iscritti. Il voto per un esame può essere registrato tramite il metodo `exam()` che accetta come parametri l'ID dello studente, il codice del corso e un voto (intero tra 0 e 30).

Per ottenere la media dei voti di uno studente, viene utilizzato il metodo `studentAvg()`. Accetta come argomento l'ID dello studente. Se lo studente ha sostenuto almeno un esame, restituisce una stringa nel formato seguente: `"Student STUDENT_ID : AVG_GRADE"`. In caso contrario, restituisce `"Student STUDENT_ID hasn't taken any exams"`.

È possibile ottenere la media dei voti di tutti gli studenti che hanno sostenuto l'esame per un determinato corso, utilizzando il metodo courseAvg(), che accetta come argomento il codice del corso. Il formato restituito è il seguente: `"The average for the course COURSE_TITLE is: COURSE_AVG"`, se almeno uno studente ha sostenuto l'esame per quel corso. Se questo non è il caso, il metodo restituisce `"No student has taken the exam in COURSE_TITLE"`.

Suggerimenti:

- &#9758; Si può assumere che se uno studente sostiene un esame, è stato precedentemente iscritto al corso corrispondente.


## R6. Premio studentesco

L'università ha deciso di premiare i migliori studenti per il loro duro lavoro e impegno. Il metodo `topThreeStudents()` viene utilizzato per recuperare le informazioni per aiutare la commissione a assegnare il/i premio/i.

Il punteggio degli studenti è valutato come la media dei voti degli esami che hanno sostenuto. Per tenere conto del numero di esami sostenuti e non solo dei voti, viene assegnato un bonus speciale in aggiunta alla media dei voti: il numero di esami sostenuti diviso per il numero di corsi a cui lo studente è iscritto, moltiplicato per 10, viene aggiunto.

Il metodo restituisce una stringa con le informazioni sui tre studenti con il punteggio più alto. Gli studenti appaiono uno per riga (le righe sono terminate da un carattere di a-capo `'\n'`) e ognuno di essi è formattato come: "NOME_STUDENTE COGNOME_STUDENTE : PUNTEGGIO".

Suggerimenti:

- &#9758; Si assume che non ci siano parità di punteggio.


## R7. Logger

Aggiungere il logging delle operazioni eseguite attraverso la classe facade `University`. Tali operazioni includono:

- l'iscrizione di un nuovo studente, con un messaggio di log come `"New student enrolled: 10000, Mario Rossi"`
- l'attivazione di un nuovo corso, con un messaggio di log come `"New course activated: 11, Object Oriented Programming James Gosling"`
- l'iscrizione di uno studente a un corso, con un messaggio di log come `"Student 10004 signed up for course 11"`
- uno studente che svolge un esame, con un messaggio di log come `"Student 10001 took an exam in course 12 with grade 27"`

Suggerimenti:

- &#9758; per eseguire il logging è disponibile un oggetto `logger` della classe `java.util.logging.Logger` all'interno della classe [`University`](src/university/University.java);
- &#9758; utilizzare il metodo `info()` dell'oggetto `logger` per generare i messaggi di log
- &#9758; si noti che normalmente i messaggi verranno stampati sulla console.

---

Versione 1.1 - 2024-03-16
