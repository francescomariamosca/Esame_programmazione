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


### Class Diagram
![Use Class Diagram](/Esame_ClassiDiagram.jpg)


### Rotte Applicazione
| Tipo | Rotta | Descrizione |
| ---------- | ----------- | ----------- |
| GET | /Cerca?zipCode=00187 | Restituisce i dati del tempo a partire dallo zip code richiesto |
| GET | /Suggest | Restituisce la lista capoluoghi |
| GET | /Ricerca?q=Mi | Ricerca capoluoghi italiani a partire dalla sottostringa inserita |
| GET | /Stats?nomeCitta=Macerata | Restituisce le statistiche di temperatura e umidità della città richiesta |
| POST | /Filters | Restituisce le statistiche filtrate in base ai parametri dati in ingresso |
