# Esame Programmazione ad Oggetti
### Descrizione Generale
Questo progetto consiste nella realizzazione di un servizio meteo che, dato lo zipCode di una città, permette di visualizzare i dati relativi a temperatura e umidità.
Permette la visione di statistiche personalizzabili con filtri, la ricerca per nome o sottostringa.

### Use Case Diagram
![Use Case Diagram](/UseCaseDiagram.jpg)

| Caso d'Uso | Descrizione |
| ---------- | ----------- |
| Cerca      | Permette all'utente di ricercare informazioni sul tempo |
| Zip Code   | Inserimento zipCode città italiana |
| Vedere Statistiche | Ottenere le statistiche sul dataSet disponibile |
| Filra Statistiche | Filtrare le statistiche sul dataSet disponibile |
| Giornaliero | Filtra i dati secondo una periodicità giornaliera |
| Settimanale | Filtra i dati secondo una periodicità giornaliera |
| Mensile | Filtra i dati secondo una periodicità mensile |
| Personalizzato | Filtra i dati secondo una range personalizzato |
| Inserisci Range | Permette di inserire il range personalizzato per i filtri |
| Richiedi Lista | Suggerisce zipCode dei capoluoghi da ricercare all'utente |
| Sottostringa | Ricerca capoluogo dalla Lista partendo dalla sottostringa inserita |
| Amministatore | Fornisce la lista dei capoluoghi |
| Utente | Utente del servizio |

## Come funziona
Scaricando il file zip del progetto e importandolo in un IDE (quale Eclipse ad esempio), il programma sarà pronto all'utilizzo.
Una volta eseguito possono essere richiamate le rotte programmate (e descritte nelle prossime righe in dettaglio) attraverso un browser o un programma apposito quale Postman.
Il programma ogni ora salverà all'interno del file serializzato "dataSet.txt" le chiamate effettuate all'API di OpenWeather così da averle a disposizione al seguente avvio. I dati verranno utilizzati per le richieste inerenti le statistiche e i filtri.
Attualmente il dataSet è già popolato con sufficienti dati per verificare la corretta esecuzione di ogni richiesta.
Tutte le rotte ritornano valori in formato JSON.

### Class Diagram
![Use Class Diagram](/Esame_ClassiDiagram.jpg)

## Rotte Applicazione
| Tipo | Rotta | Descrizione |
| ---------- | ----------- | ----------- |
| GET | /Cerca?zipCode={zipCodeCittaItaliana} | Restituisce i dati del tempo a partire dallo zip code richiesto |
| GET | /Suggest | Restituisce la lista capoluoghi |
| GET | /Ricerca?q={NomeCapoluogo o Sottostringa} | Ricerca capoluoghi italiani a partire dalla sottostringa inserita |
| GET | /Stats?nomeCitta={nomeCittaItaliana} | Restituisce le statistiche di temperatura e umidità della città richiesta |
| POST | /Filters | Restituisce le statistiche filtrate in base ai parametri dati in ingresso |

* ***/Cerca?zipCode=00187***
Attraverso il Controller viene invocato il metodo chiamataApi della classe WeatherUtils, che restituisce un JSONObject, cioè il risultato della nostra chiamata API, con le informazioni relative alla città all'applicazione di test.
![Risposta Cerca](/risposta_cerca.jpg)

* ***/Suggest***
Attraverso il Controller viene invocato il metodo visualizzaDizionario della classe DizionarioImpl, che restituisce un JSONArray, cioè il risultato della nostra chiamata API, con i capoluoghi delle regioni all'applicazione di test.
![Risposta Suggest](/risposta_suggest.jpg)

* ***/Ricerca?q=Mi***
Attraverso il Controller viene invocato il metodo cercaSottostringa della classe DizionarioImpl, che restituisce un JSONArray, cioè il risultato della nostra chiamata API, con i capoluoghi delle regioni che contengono la sottostringa passata come parametro.
![Risposta Ricerca](/risposta_ricerca.jpg)

* ***/Stats?nomeCitta=Roma***
Attraverso il Controller vengono invocati i metodi caricaArray della classe Stats, che dopo aver caricato gli array di temperatura e umidità li passa alle funzioni per il calcolo delle statistiche. Infine restituisce un JSONArray, cioè il risultato della nostra chiamata API, con le statistiche di quella città.
![Risposta Stats](/risposta_stats.jpg)

* ***/Filters***
Con il Controller viene invocato il metodo tipoFiltro della classe Filters, il quale in base al tipo di filtro inserito fa partire la specifica funzione per calcolare le statistiche filtrate. Dunque restituisce un JSONArray, cioè il risultato della nostra chiamata API, con le statistiche di quella città, filtrate.
    #### Questa chiamata è di tipo *POST*
    Il body è un oggetto JSON con il seguente formato:
    ![Richiesta FIlters](/filters_request.jpg)
    La risposta sarà come segue:
    ![Risposta Filters](/risposta_filters.jpg)

## Eccezioni
Il programma gestisce le Possibili eccezioni che possono risultare durante il suo utilizzo e prevede due Eccezioni personalizzate:
* **NoDataException**
    Viene lanciata dal programma nel caso in cui viene fatta una richiesta in cui sono necessari dati non presenti nel dataset.
    ![Eccezione NoData](/Eccezione_NoData.jpg)
* **NoBodyException**
    Viene eseguita se viene effettuata una richiesta POST senza il body.
    ![Eccezione NoBody](/Eccezione_NoBody.jpg)

### Sequence Diagram
![Sequence Diagram](/sequenze.jpg)

## Autori
[@Dennis Pierantozzi](https://github.com/DennisPierantozzi)
[@Nicola Mochi](https://github.com/NicolaMochi)
[@Francesco Maria Mosca](https://github.com/francescomariamosca)