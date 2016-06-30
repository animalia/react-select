Animalias React-Select
============

Denne er forket fra [github.com/JedWatson/react-select](https://github.com/JedWatson/react-select).

Bakgrunnen for dette er at vi ønsker å ha større kontroll over funksjonaliteten da husdyrkontrollene ønsker at enter-knappen skal oppføre seg mye på samme måte som tab.

Koden kan enkelt testes lokalt ved å gjøre følgende:

```
npm install
npm start
```

Åpne [localhost:8000](localhost:8000)

## Publisere en ny versjon

Gjør de endringene som skal til og commit disse.

Bygg nye js-filer med  [npm](https://docs.npmjs.com/getting-started/installing-node) og commit disse:

```
npm run-script build
```
Lag en relevant tag for versjonen.

Gå så inn i **build.boot** og endre `+lib-version+` til det samme som tagen over (uten prefikset "v")
Gjør det samme med `"version"` i **package.json** og **bower.json**.

Push commits og tag til github.

### Deploy til Nexus

>Vi bruker Boot for å lage jar-fil og deploye denne til nexus. Hvordan man installerer dette finner du [her](https://github.com/boot-clj/boot#install).
Denne byggeprosessen er inspirert av hvordan det er gjort i [cljsjs/packages](https://github.com/cljsjs/packages). **build.boot** og **react-select.ext.js** er hentet derfra
og førstnevnte er bygget videre på for å kunne deploye til nexus.

Først må det lages en jar-fil lokalt i target-mappa (følgende kommando legger også til prosjektet i ditt lokale maven-repo):

```
boot package install target
```

Deretter kan man pushe det ut til vårt nexus-repo. Enn så lenge må man ha satt brukernavn og passord som miljøvariabler.

```
export nexus_user=(finn dette i 1password)
export nexus_password=(finn dette i 1password)
```

Etter at disse er satt skal det bare være å kjøre følgende kommando:

```
boot push
```

##Lisens

MIT Licensed. Copyright (c) Jed Watson 2016.
