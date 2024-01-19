Die Aufgabe:
------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------------------
0 Teil.

1) Lege in deiner Dateistruktur einen Ordner mit dem Namen „Die Welt von Zuul“ an. Öffne die Kommandozeile (in Windows cmd in das Suchfeld eingeben). Wechsle über den Befehl cd Dateipfad in den eben erstellten Ordner. Klone das Projekt „Die Welt von Zuul“ über den Git-Befehl

git clone https://Soeren22@bitbucket.org/szut_anwend/worldofzuul.git.

2) Um einen Überblick über die Klassen der Anwendung zu bekommen, öffne und studiere das Klassendiagramm im Ordner doc des Hauptverzeichnisses.

3) Die Anwendung ist sehr gut dokumentiert. Erstelle im Ordner doc eine Javadoc- HTML-Dokumentation. Erkundige dich dafür mit Hilfe des Links https://www.jetbrains.com/help/idea/working-with-code-documentation.html.

Erfahre mehr über die Anwendung, indem du die erstellte HTML-Dokumentation in einem Browser öffnest und liest.

4) Teste die Anwendung. Führe dazu die main-Funktion in der Klasse ZuulUI aus. Schaue in einer geeigneten Klasse nach den bisher erlaubten Befehlen.

5) Schaue dir danach die Implementierung der Klassen an. Kläre dabei folgende Fragen:

a. Was tut diese Anwendung?

b. Welche Befehle akzeptiert das Spiel?

c. Was bewirken die einzelnen Befehle?

d. Wie viele Räume gibt es in der virtuellen Umgebung und wie sind sie angeordnet? Zeichne die virtuelle Umgebung.

e. Erkunde die einzelnen Klassen und schreibe für jede Klasse ihren Einsatzzweck auf.

6) Wie bei deinem Fußballmanager aus der letzten Lernsituation wirst du auch dieses Mal verschiedene Versionen der Anwendung erstellen. Dieses Mal soll allerdings nicht jedes Mal die alte Version in ein neues Package kopiert, sondern die verschiedenen Versionen mit der Versionsverwaltung Git verwaltet werden. Git verwaltet die verschiedenen Versionen einer Anwendung in einem Repository. Das befindet sich im Ordner .git, den du über den Explorer im Hauptverzeichnis des Zuul-Projekts sehen kannst. Um ein völlig neues Repository zu erstellen, lösche den Ordner.git aus dem Verzeichnis. Öffne in IntelliJ das Terminal (Reiter unten links) und setze den Befehl

>git init

ab. Git erzeugt nun im Hauptverzeichnis des Projekts ein leeres Repository. Die schon angelegten Dateien des Projekts wurden dem Repository noch nicht hinzugefügt. Das kannst du mithilfe eines Git-Befehls auch in Erfahrung bringen:

>git status

Dieser Befehl zeigt dir alle ungetrackten Dateien bzw. Ordner an, das heißt Git hat diese noch nicht im Repository abgespeichert. Um die schon angelegten Dateien diesem Repository hinzuzufügen sind zwei Schritte nötig. Zunächst müssen die „untracked Files“ der so genannten Staging Area hinzugefügt werden. Dorthin werden alle Dateien übernommen, die im zweiten Schritt einer neuen Version hinzugefügt werden sollen; sie werden quasi für die Versionierung angemeldet:

>git add -A

-A steht für All. Das heißt Git fügt alle untracked Files der Staging Area hinzu. Alternativ könnten die Namen der Dateien genannt werden, was in unserem Fall aber zu aufwendig wäre. Wenn du nun ein weiteres Mal den status-Befehl absetzt, zeigt Git dir alle Dateien, die es der Staging Area hinzugefügt hat, in grün an. Um nun deine erste Version zu erzeugen, muss ein Commit erstellt werden:

>git commit -m “zuul 0“

Der commit-Befehl verpackt die Dateien in ein Commit und speichert sie im Repository. Ein Commit ist ein Schnappschuss des aktuellen Dateisystems. Git verpackt den aktuellen Status und versieht ihn mit einem eindeutigen Hashwert und speichert ihn mit verschiedenen Metadaten ab. Mit mehreren Commits nacheinander entsteht eine Historie der Zustände, in der man sich auch zurückhangeln kann. Der Parameter -m hinter commit gibt an, dass dahinter ein Kommentar angegeben wird. Der Kommentar sollte sprechend sein, d.h. die getätigte Änderung am Projekt erkennbar benennen. In diesem Fall wollen wir unsere Versionen anhand der Arbeitsblätter benennen.

------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------------------

1 Teil.

Die Welt von Zuul

- Kohäsionsverletzung durch Codeduplizierung-

Schlechte Klassenentwürfe werden meistens dann offensichtlich, wenn eine Anwendung angepasst oder erweitert werden soll. Unser Programm kann bisher nicht sonderlich viel und ist nicht sonderlich spannend. Wir wollen es daher Schritt für Schritt erweitern. Während der Erweiterung gehen wir auf einige Aspekte des Klassenentwurfs ein und werden feststellen, dass diese Anwendung einige schlechte Entwurfsentscheidungen enthält, die wir beheben. Einen schlechten Entwurf erkennt man nicht unbedingt daran, dass eine Anwendung nicht funktioniert. Diese Anwendung tut, was sie soll. Ein schlechter Entwurf ist niemals dadurch zu entschuldigen, dass das umzusetzende Problem komplex ist. Ein schlechter Entwurf hängt von den Entscheidungen ab, die wir bei der Lösung des Problems treffen. Um Anhaltspunkte dafür zu haben, was schlechte Entwurfsentscheidungen sind, benötigen wir die Begriffe Kopplung und Kohäsion.

Kopplung

Der Begriff Kopplung beschreibt den Grad der Abhängigkeit zwischen Klassen. Es wird eine möglichst lose Kopplung angestrebt, also ein System, in dem jede Klasse weitgehend unabhängig ist und mit anderen Klassen nur über möglichst schmale, wohl definierte Schnittstellen kommuniziert.

Der Begriff bezieht sich also darauf, wie Klassen miteinander verknüpft sind. Je loser die Kopplung von Klassen ist, desto leichter kann eine Anwendung geändert werden. In einer eng gekoppelten Klassenstruktur zieht eine Änderung in einer Klasse viele Änderungen in anderen Klassen nach sich. Das soll vermieden werden. Zieht sich eine Änderung durch eine ganze Anwendung ist das ein Zeichen für einen schlechten Entwurf!

Kohäsion

Der Begriff Kohäsion beschreibt, wie gut eine Programmeinheit eine logische Aufgabe oder Einheit abbildet. In einem System mit hoher Kohäsion ist jede Programmeinheit (eine Methode, Klasse oder ein Modul) für genau eine wohl definierte Aufgabe oder Einheit verantwortlich. Eine Methode sollte daher immer nur eine logische Operation implementieren. Eine Klasse sollte genau einen Typ von Objekt modellieren. Hauptanlass für Kohäsion ist die Wiederverwendung. Ein hoher Grad an Kohäsion erhöht die Wahrscheinlichkeit, dass eine Klasse oder Methode in einem anderen Zusammenhang eingesetzt werden kann. Außerdem gibt es den Vorteil, dass im Falle von Änderungen die Stellen, die von diesen Änderungen betroffen sind, eher in einer Einheit zu finden sind.

Verstößt man gegen das DRY-Prinzip (Don’t repeat yourself), liegt häufig eine schlechte Kohäsion vor. Code-Duplizierung ist dafür ein Beispiel. Von Code-Duplizierung spricht man dann, wenn ein Quelltextabschnitt mehr als einmal in einer Anwendung erscheint. Das führt dazu, dass Änderungen an einer Stelle der Anwendung immer auch an anderen Stellen durchgeführt werden müssen. Wenn das vergessen wird, enthält der Quellcode zwangsläufig Fehler und Inkonsistenzen. Codeduplizierung ist ein Zeichen für schlechte Kohäsion.

Aufgaben

1) Schaue dir die Methoden private void goRoom(Command command) und private void printWelcome() der Klasse Spiel an. Dort gibt es eine Code-Duplizierung. Folgendes Quellcodefragment kommt nämlich in beiden Methoden vor:

System.out.println();

System.out.println("You are " + currentRoom.getDescription()); System.out.print("Exits: "); if(currentRoom.northExit != null) {

System.out.print("north "); } if(currentRoom.eastExit != null) { System.out.print("east "); } if(currentRoom.southExit != null) { System.out.print("south "); } if(currentRoom.westExit != null) { System.out.print("west "); } System.out.println();

Diese Code-Duplizierung liegt daran, dass beide Methoden zwei Dinge erledigen. printWelcome() gibt einen Willkommenstext aus und liefert Informationen über den aktuellen Raum. goRoom() wechselt den Raum und gibt ebenfalls Informationen über diesen aus. Beide Methoden geben dieselben Informationen aus, aber keine kann die andere aufrufen, weil sie jeweils noch eine andere Aufgabe ausführt. Ein klarer Verstoß gegen das Prinzip der Kohäsion. Wir erhöhen den Grad der Kohäsion unserer Anwendung, indem wir den doppelten Quellcode in eine eigene Methode auslagern.

2) Schreibe eine Methode printRoomInformation(), die in den Methoden goRoom() und printWelcome() aufgerufen wird und darüber die Codeduplizierung und die schlechte Kohäsion auflöst. Benutze für das Auslagern der Methode die Tastenkombination Strg+Alt+m, nachdem du den auszulagernden Quellcode markiert hast.

3) Wir wollen im nächsten Schritt das Spiel um die Bewegungsrichtungen up und down erweitern. Dementsprechend brauchen wir ein paar neue Räume. Die Tempelpyramide hat nun einen ersten Stock, in dem der Zauberer untergebracht ist. Außerdem hat die Tempelpyramide einen Keller, von dem ein Geheimgang zu einer alten Piratenhöhle unter der Opferstätte führt. Folgender Graph verdeutlicht, welche Wege möglich sein sollen. Erweitere das Programm ohne die bisherige Logik und die Klassenstruktur des Programms zu verändern. Mache dir vor deiner Erweiterung klar, an welchen Stellen im Programm du Änderungen durchführen musst. Teste dein Programm!

4) Die auf diesem Aufgabenzettel erstellten Änderungen am Ursprungsprojekt sollen abschließend mit Git versioniert werden. Lasse dir mithilfe des status-Befehls alle ungetrackten Dateien anzeigen. Führe danach wiederum die Befehle add und commit aus. Dieses Mal sind nicht so viele Dateien geändert worden. Benutze daher dieses Mal den add-Befehl so, dass du hinter add den jeweiligen Dateinamen angibst. Kontrolliere den Erfolg deines Befehls mit dem status-Befehl. Wenn alle Dateien der Staging Area hinzugefügt worden sind, committe:

>git add dateiname_mit_dateiendung > git commit -m “zuul 1“

5) Git speichert die Änderungen im Repository in einer verketteten Liste von aufeinanderfolgenden Commits. Diese Versionsgeschichte kann mit Git abgerufen werden:

>git log

Der Befehl log ohne Parameter zeigt die Commits mit ihren jeweiligen Metadaten und den Hashwerten in kompletter Länge. Um die Informationen verkürzt darzustellen, geht auch

>git log --oneline

Die verkürzten Hashwerte reichen schon, um die Commits eindeutig zu identifizieren. Beachte auch, auf welches Commit die sogenannten Head- und Masterpointer zeigen.

6) Nun lässt sich von der Versionierung profitieren, indem ältere Versionen der Dateien abgerufen und in das Arbeitsverzeichnis geladen werden. Dazu genügt es, den Headpointer umzusetzen. Der Headpointer zeigt immer auf den aktuellen Commit, also das Dateisystem, das aktuell auf dem Rechner angezeigt wird. Wenn der Headpointer auf ein anderes Commit umgesetzt wird, wird im Arbeitsverzeichnis der ausgewählte ältere Zustand angezeigt. Das Umsetzen des Headpointers geschieht mit

> git checkout ID des Commits

Kopiere dazu den kurzen oder langen Hashwert des ersten Commits von der Konsole und führe den checkout-Befehl durch. Kontrolliere den Zustand deines Dateisystems. Wenn alles geklappt hat, müssten deine Dateien den Zustand deiner ersten Version haben.

7) Lasse dir wieder die letzte Dateiversion anzeigen, indem du den Headpointer wieder auf den Masterpointer umsetzt:

> git checkout master

------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------------------

2 Teil.
