# Plenty2Go: CSV-Export in Paketdienst-Importdatei umwandeln

Kleines Java-Swing-Werkzeug, das Bestellexporte aus Plentymarkets (CSV, Semikolon) in XML-Importdateien für einen Paketdienst umwandelt. Entstanden 2021 im Rahmen einer Werkstudententätigkeit zur Automatisierung des Versandprozesses.

## Ablauf

1. CSV-Datei in den Ordner `Eingang` legen
2. Das Programm prüft den Ordner alle zwei Sekunden, wandelt die erste Datei um und legt die XML-Datei in `Ausgang` ab
3. Die verarbeitete CSV-Datei wird nach `Eingang/verarbeitet` verschoben (bei Fehlern mit der Endung `.fehler`)

## Konfiguration

Die Absenderdaten stehen **nicht** im Code. Vorlage `absender.properties.example` nach `absender.properties` kopieren und ausfüllen. Diese Datei ist per `.gitignore` ausgeschlossen.

## Bauen und Starten

```bash
javac -encoding UTF-8 -d out src/*/*.java
java -cp out main.App .
```

## Überarbeitung

- Hartkodierte Firmen- und Kundendaten in eine Konfigurationsdatei ausgelagert
- Zeitgesteuerte Abschaltung im Code entfernt, Programmlogik vereinfacht
- Kompilierte `.class`-Dateien aus dem Repository entfernt
- Feldzuordnung (Spaltennummern der CSV) ist weiterhin fest im Code und auf den damaligen Export zugeschnitten

*Hinweis: Frühere Commits können noch die alten Firmendaten enthalten. Das Repository sollte deshalb privat bleiben.*
