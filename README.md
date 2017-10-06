# MobDev
## Pokemon App
De bedoeling is een applicatie te maken die het mogelijk maakt pokemongevechten te voeren met een computergestuurde tegenstander.
Een gevecht verloopt als volgt:

###Stap 1  
De speler kiest 6 pokemon uit zijn lijst beschikbare pokemon. Op basis van het gekozen team wordt een teamrating gegenereerd.
De speler kiest een moeilijkheidsniveau. Op basis van deze keuze en de teamrating van de speler, wordt een tegenstander gekozen.
Als de speler op de beginknop drukt, begint stap 2.

###Stap 2  
De speler kan tijdens het gevecht kiezen om aan te vallen, een item te gebruiken, of van pokemon te wisselen.
Na deze keuze, kan de speler kiezen tussen zijn beschikbare aanvallen/items/pokemon.
Na deze keuze, kiest de vijand een willekeurige, beschikbare aanval.
De resultaten worden berekend en de speler begint terug aan het begin van deze stap.

Deze stap wordt herhaald tot een pokemon 0% HP bereikt.
Als de tegenstander 0% HP bereikt, wordt een willekeurige andere pokemon gekozen uit zijn team.
Als de speler 0%HP bereikt, mag hij een nieuwe pokemon kiezen.
Als een van beide spelers geen pokemon over heeft, beëindigt het gevecht en begint stap 3.

###Stap 3  
Als de speler verliest, keert hij na een kleine melding terug naar het hoofdmenu.
Als de speler wint, wordt het aantal munten dat hij wint berekent aan de hand van de moeilijkheid en zijn teamrating. Hierna keert hij terug naar het hoofdmenu.

###Hoofdmenu
De speler krijgt hier de optie om te kiezen voor een nieuw gevecht, de winkel of zijn teampagina.
In de winkel kan de speler nieuwe items of pokemon kopen.
In zijn teampagina kan de speler zijn team aanpassen. Hij kan er ook evoluties of nieuwe aanvallen voor zijn pokemon kopen.
