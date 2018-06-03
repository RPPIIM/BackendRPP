# BackendRPP
Backend za RPP i APURSP

## Preuzimanje i podešavanje tomcat servera

Preuzeti [Apache Tomcat 9](http://www-us.apache.org/dist/tomcat/tomcat-9/v9.0.8/bin/apache-tomcat-9.0.8.exe)  
Pokrenuti instalaciju, kada se dođe do dela Configuration, definisati username i password (Roles ostaviti manager-gui)  
Za pokretanje servera pokretnuti **startup.bat** koji se nalazi u bin folderu servera    
(default putanja je C:\Program Files\Apache Software Foundation\Tomcat 9.0\bin)  
U browser-u otići na adresu **localhost:8080**  
Ukoliko je učitana početna stranica tomcat servera, server je uspešno instaliran, podešen i pokrenut.

## Kreiranje war fajla backend aplikacije  
Modifikovati aplikaciju za kreiranje war fajla ([Vežbe 12](https://github.com/IIS/IIS1/commit/3ada60c3e100db2870f5c32af71433b430045a75))  
Pokrenuti maven install (desni taster na projekat, run as -> maven install)  
U oviru target foldera projekta kreiraće se fajl **backend.war**  

Ukoliko maven install ne funkcioniše, podesiti java build path STS-a da koristi java JDK umesto JRE  
(u STS-u, window -> preferences -> java -> installed JREs -> selektovati postojeći i kliknuti na edit -> izabrati putanju do Java JDK foldera)


## Deployment aplikacije na tomcat server
U browser-u otići na adresu **localhost:8080**  
Kliknuti na Manager app, uneti username i password  
U delu deploy war file odabrati backend.war i kliknuti na deploy  
Aplikacija je pokrenuta na adresi **localhost:8080/backend/**  
(testirati npr. učitavanje artikala na adresi localhost:8080/backend/artikl) 
