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
    Screen risposta
* ***/Suggest***
    Screen risposta
* ***/Ricerca?q=Mi***
    Screen risposta
* ***/Stats?nomeCitta=Roma***
    Screen risposta
* ***/Filters***
    Questa chiamata è di tipo *POST*
    Il body è un oggetto JSON con il seguente formato:
    Screen 
    La risposta sarà come segue:
    Screen

## Eccezioni
Il programma gestisce le Possibili eccezioni che possono risultare durante il suo utilizzo e prevede due Eccezioni personalizzate:
* **NoDataException**
    Viene lanciata dal programma nel caso in cui viene fatta una richiesta in cui sono necessari dati non presenti nel dataset.
    Screen
* **NoBodyException**
    Viene eseguita se viene effettuata una richiesta POST senza il body.
    Screen

## Autori
[@Dennis Pierantozzi](https://github.com/DennisPierantozzi)
[@Nicola Mochi](https://github.com/NicolaMochi)
[@Francesco Maria Mosca](https://github.com/francescomariamosca)