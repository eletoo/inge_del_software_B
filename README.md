# STRUTTURA GERARCHICA DEI FILE
Nel file `structure.tex` si possono inserire le sezioni che si vogliono introdurre nella presentazione.
Si è posto ogni "capitolo" della presentazione (testing, MVC, pattern Grasp, pattern SOLID, refactoring, testing finale) in una sezione diversa, in modo tale da ridurre l'articolazione della struttura della presentazione, perlomeno in fase di scrittura.

Nella sezione `settings.tex` si trova la definizione dell'intestazione con autore, titolo, istituto e data.

# ORGANIZZAZIONE SEZIONI
In ogni sezione è predisposto un file `nomeSezioneMain.tex` al cui interno è riportato:
- titolo della sezione 
- elenco di file da includere nella sezione: i file sono gli altri presenti nella cartella relativa alla sezione considerata

# STRUTTURA SLIDE
Nei file che compongono ogni sezione si usi la seguente struttura per generare le slide:

```Latex
\begin{frame}{titolo della slide}
    \framesubtitle{sottotitolo della slide -- opzionale}

    %inserire contenuto della slide

    \note{
        %Commenti che compariranno nella slide a lato di quella considerata. 
        %I commenti sono visibili solamente a chi presenta le slide
        %i.e. se si condivide lo schermo si può scegliere di presentare le sole slide senza i commenti
    }    
\end{frame}
```


Se si vogliono inserire immagini all'interno delle slide, salvare tali immagini nella cartella `images`, già preimpostata come _path_ in cui recuperare componenti grafiche della presentazione.

# FILE DI OUTPUT E PDF
Nella cartella `texout` si trovano i file di output ottenuti a seguito della compilazione del codice; 
in particolare `presentation.pdf` contiene le slide della presentazione in pdf.
Le slide possono essere visualizzate singolarmente come presentazione tramite il software `pympress`, che permette di condividere lo schermo nella modalità specificata nel commento all'interno della nota nel modello di slide.