# OOP Lab 3 - Diet and Restaurants

(La versione Inglese è disponibile nel file [README.md](README.md)).


Sviluppare un'applicazione che consenta di gestire la dieta tramite il calcolo dei valori nutritivi.

L'applicazione deve permettere di definire le materie prime, di utilizzarle come ingredienti per delle ricette, di gestire prodotti confezionati e menù.

Tutte le classi devono trovarsi nel package `diet`.


## R1 - Materie Prime

Il sistema interagisce principalmente tramite la classe `Food`.

Per definire una materia prima, si utilizza il metodo `defineRawMaterial()` che riceve come parametri il nome, le kilo-calorie, le proteine, i carboidrati e i grassi, tutti riferiti a 100 grammi di materia prima. Si può assumere che il nome delle materie prima sia univoco.

Per avere informazioni sulle materie prime, si utilizza il metodo `rawMaterials()` che restituisce la collezione delle materie prime in ordine alfabetico. Per ottenere le informazioni su una specifica materia prima ci si avvale del metodo getRawMaterial() che riceve come parametro il nome e restituisce la materia prima corrispondente.

Gli elementi restituiti dai due metodi precedenti implementano l'interfaccia `NutritionalElement` che definisce i metodi `getName()`, `getCalories()`, `getProteins()`, `getCarbs()`, `getFat()`. Le calorie sono espresse in KCal, mentre proteine, carboidrati e grassi sono espressi in grammi. 
Inoltre, l'interfaccia definisce il metodo booleano `per100g()` che indica se il valori nutritivi sono espressi per 100 grammi di elemento oppure esprimono un valore assoluto.
Nel caso delle materie prime i valori nutritivi sono espressi sempre per 100 grammi.


## R2 - Prodotti

È possibile considerare anche prodotti preconfezionati (ad esempio un cono gelato). I prodotti vengono definiti tramite il metodo `defineProduct()` della classe Food che riceve come parametri il nome, le kilo-calorie totali, le proteine, i carboidrati e i grassi che esprimono i valori complessivi per il prodotto (ovvero non per 100 g). Si può assumere che il nome del prodotto sia univoco.

Per avere informazioni sui prodotti si utilizza il metodo `products()` della classe Food che restituisce la collezione dei prodotti in ordine alfabetico. Per otterenere le informazioni su uno specifico prodotto ci si avvale del metodo `getProduct()` della classe Food che riceve come parametro il nome e restituisce il prodotto corrispondente.

Gli elementi restituiti dai due metodi precedenti implementano l'interfaccia `NutritionalElement` (descritta al punto precedente); i valori sono espressi per prodotto intero, perciò il metodo `per100g()`, in tal caso, restituisce false.


## R3 - Ricette

Le materie prime possono essere combinate come ingredienti di ricette. Per definire una ricetta viene utilizzato il metodo `createRecipe()` che riceve come parametro il nome della ricetta, che può essere considerato unico.

Le ricette sono rappresentate da oggetti di classe `Recipe` che permettono di aggiungere ingredienti tramite il metodo `addIngredient()` che riceve come parametri il nome di una materia prima e la sua quantità in grammi.

La classe `Recipe` implementa l'interfaccia `NutritionalElement` e i valori nutrizionali sono espressi per 100 grammi.

Per avere informazioni sulle ricette si utilizza il metodo `recipes()` della classe Food che restituisce la collezione delle ricette in ordine alfabetico. Per ottenere le informazioni su una specifica ricetta ci si avvale del metodo `getRecipe()` che riceve come parametro il nome e restituisce la ricetta corrispondente. Entrambi i metodi restituiscono le ricette sotto forma di NutritionalElement.

Attenzione: la somma delle quantità (in grammi) degli ingredienti di una ricetta non necessariamente è pari a 100g, i valori nutrizionali della ricetta devono essere invece riferiti a un'ideale porzione di 100g.


## R4 - Menù

Il menù è composto sia da porzioni di ricette sia da prodotti preconfezionati.

Un nuovo menù è creato tramite il metodo `createMenu()` che accetta come parametro il nome (unico) del menù.

I menù sono rappresentati dalla classe `Menu` che permette di aggiungere una porzione di una ricetta tramite il metodo `addRecipe()` che riceve come parametro il nome di una ricetta e la dimensione della porzione in grammi. Per aggiungere un prodotto preconfezionato, la classe Menu offre il metodo `addProduct()` che riceve come parametro il nome del prodotto.

Inoltre la classe `Menu` implementa l'interfaccia `NutritionalElement`; in questo caso i valori restituiti si riferiscono al totale del menu.


----

**Requisiti aggiuntivi**

----

## R5 - Ristorante

La gestione dei ristoranti è effettuata trami la classe `Takeaway` che rappresenta una catena di ristoranti.
Il costruttore accetta un riferimento a un oggetto `Food` in modo da poter definire ingredienti e prodotti una volta per tutte.

I ristoranti possono essere creati tramite il metodo `addRestaurant()` che accetta il nome del ristorante e restituisce un oggetto di tipo `Restaurant`. Il cui metodo getter `getName()` restituisce il nome. Il metodo `restaurants()` della classe `Takeaway` restituisce il nome di tutti i ristoranti registrati nella catena.

Attraverso il metodo `setHours()` è possibile definire l'orario di servizio del ristorante. Il metodo accetta un vettore di stringhe (in numero pari di elementi) secondo il formato `"HH:MM"`, tale per cui le ore di chiusura seguono le ore di apertura (es., per un ristorante aperto dalle *8:15* alle *14:00* e dalle *19:00* alle *00:00*, gli argomenti devono essere `"08:15", "14:00", "19:00", "00:00"`).
È possibile sapere se un ristorante è aperto a un dato orario con il metodo `isOpenAt()` che accetta un orario (con il formato precedente).

I ristoranti offrono diversi menù, che possono essere creati legati al ristorante tramite il metodo `addMenu()` che ha come parametro il menù che potrà poi essere utilizzato in seguito per effettuare degli ordini. È possibile ottenere un menù di un ristorante a partire dal nome tramite il metodo `getMenu()`.


## R6 - Clienti

Un cliente è definito fornendo il suo nome, cognome, email e numero di telefono al metodo `registerCustomer()` il quale ritorna un oggetto `Customer`. Sono disponibili dei metodi getter per tutti i campi (`getFirstName()`, `getLastName()`, `getEmail()`, `getPhone()`), mentre i metodi setter sono forniti solo per l'email e il numero di telefono (`setEmail()`, `setPhone()`). La rappresentazione come stringa dell'oggetto `Customer` è il nome separato da uno spazio e seguito dal cognome.

Per ottenere i clienti disponibili è possibile utilizzare il metodo `customers()` della classe `Takeaway` che ritorna una collezione di clienti ordinati per cognome e, nel caso di cognomi uguali, per nome.


## R7 - Ordini

Un utente registrato può effettuare un ordine in uno dei ristoranti disponibili. Per tale scopo il metodo `createOrder()` accetta come argomenti l'oggetto `Customer` che effettua l'ordine, il nome del ristorante (String) e l'orario di consegna desiderato. Inoltre, se per un certo ordine l'orario di consegna è al di fuori dell'orario di servizio del ristorante, l'orario di consegna è impostato alla prima ora di apertura successiva (es., facendo un ordine per un ristorante con orario di servizio dalle 8:15 alle 14:00 e dalle 19:00 alle 00:00, e richiedendo una consegna per le 15:30, l'orario di consegna sarà impostato alle 19:00).

Un ordine può essere in uno dei seguenti stati: `ORDERED`, `READY` e `DELIVERED`, accessibile attraverso i metodi setter e getter `setStatus()` e `getStatus()` (`ORDERED` è lo stato di default). Inoltre, il tipo di pagamento di un ordine può essere: `PAID`, `CASH` o `CARD`, accessibile attraverso i metodi setter e getter `setPaymentMethod()` e `getPaymentMethod()` (`CASH` è il pagamento di default).

I menù possono essere aggiunti a un ordine chiamando il metodo `addMenus()` e specificando il nome del menù (come stringa) e la quantità (come numero intero).

Quando un ordine è stampato, deve essere formattato come segue:

```
"RESTAURANT_NAME, USER_FIRST_NAME USER_LAST_NAME : (DELIVERY_HH:MM):
	MENU_NAME_1->MENU_QUANTITY_1
	...
	MENU_NAME_k->MENU_QUANTITY_k
"
```

I menù sono ordinati per nome e sono riportati, uno per linea, preceduti da una tabulazione ('\t').

Attenzione: Se un certo menù è già stato aggiunto ad un ordine, l'usarlo di nuovo come argomento di `addMenus()` deve sovrascrivere la quantità definita in precedenza.


## R8 - Informazioni

Per ottenere alcune informazioni relative ai ristoranti può essere utilizzato il metodo `openRestaurants()` il quale ha un argomento di tipo stringa nel formato `"HH:MM"` e ritorna una collezione di oggetti Restaurant che sono aperti in un certo orario, ordinati alfabeticamente per nome. Un ristorante è aperto se c'è almeno un segmento di orario di servizio tale per cui l'orario richiesto è compreso nell'intervallo [aperto, chiuso).

Le informazioni relative agli ordini di un ristorante possono essere ottenute attraverso il metodo `ordersWithStatus()` della classe `Restaurant`. Tale metodo ritorna una stringa ottenuta dalla concatenazione di tutti gli ordini che soddisfano il criterio.

```
Napoli, Judi Dench : (19:00):
	M6->1
Napoli, Ralph Fiennes : (19:00):
	M1->2
	M6->1
```

La lista è ordinata per nome del ristorante, nome dell'utente ed orario di consegna.

---

Versione 1.0.1 - 2024-04-04
