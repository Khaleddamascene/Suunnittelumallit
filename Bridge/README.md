# Bridge-malli: SmartTv + SmartRemote

Laajennus Refactoring.Guru-sivuston Java-esimerkkiin
<https://refactoring.guru/design-patterns/bridge/java/example>

## Rakenne

```
src/main/java/
├── Demo.java                      (alkuperäinen)
├── SmartDemo.java                 UUSI - esimerkkiohjelma
├── SmartTests.java                UUSI - 19 automaattista tarkistusta
├── devices/
│   ├── Device.java, Radio.java, Tv.java   (alkuperäiset)
│   ├── InternetCapable.java       UUSI - valinnainen ominaisuusrajapinta
│   └── SmartTv.java               UUSI - televisio internetselaimella
└── remotes/
    ├── Remote.java, BasicRemote.java, AdvancedRemote.java   (alkuperäiset)
    └── SmartRemote.java           UUSI - äänikomennot (voiceControl)
output/                            Ajojen tulosteet vertailua varten
```

Alkuperäisiin tiedostoihin ei ole tehty muutoksia.

## Ajaminen

Linux / macOS (tarvitsee JDK 11 tai uudemman):

    ./run.sh

Windows tai käsin:

    mkdir out
    javac -d out (kaikki .java-tiedostot kansiossa src)
    java -cp out refactoring_guru.bridge.example.SmartDemo
    java -cp out refactoring_guru.bridge.example.SmartTests

## Tuetut äänikomennot (SmartRemote.voiceControl)

`power on`, `power off` (molemmat kytkevät virran), `volume up`, `volume down`,
`volume <0-100>`, `channel up`, `channel down`, `channel <n>`, `mute`,
`browse <url>` (toimii vain InternetCapable-laitteilla, esim. SmartTv).
