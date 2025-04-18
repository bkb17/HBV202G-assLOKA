# Flappy Bird í JavaFX
Þetta er einföld útgáfa af Flappy Bird leiknum, forrituð í Java með JavaFX. Leikmaður stýrir fugli með því að ýta á spacebar og þarf að fljúga á milli hindrana án þess að rekast á þær. Verkefnið var unnið sem lokaverkefni í áfanganum Viðmótsforritun við Háskóla Íslands.

Leikurinn byggir á einföldum leikreglum: fuglinn flýgur upp þegar ýtt er á spacebar og fellur að sjálfu sér niður. Leikmaður þarf að forðast að rekast á hindranir eða jörðina. Ef það gerist birtist „Game Over“ skjár með möguleika á að byrja upp á nýtt eða fara aftur í main menu.

## Eiginleikar
Fuglinn hoppar með því að ýta á spacebar
Hindranir færast frá hægri til vinstri
Stig eru reiknuð þegar fuglinn fer framhjá pípu
"Game Over" skjár birtist þegar leik lýkur
Möguleiki á að byrja leikinn aftur eða fara í menu
Val á fugli (blár eða bleikur fugl)

## Uppsetning og keyrsla
1. Opnaðu verkefnið í IntelliJ IDEA eða öðrum IDE með JavaFX stuðningi
2. Keyrðu FlappyBirdApplication.java
3. Eða keyrðu í gegnum Maven með eftirfarandi skipunum í terminal:

- mvn clean install
- mvn javafx:run
- mvn exec:java

## Keyrsla án IDE
Hægt er að keyra forritið án IntelliJ eða annars IDE með fat JAR sem inniheldur öll dependencies:
1. Búðu til jar-with-dependencies með:
- mvn clean package
2. Keyrðu FlappyBird með skelsskriftunni:
- ./build.sh
- ATH: Þar sem JavaFX fylgir ekki sjálfkrafa með Java, þarf að vísa í JavaFX SDK með --module-path. Það er gert sjálfkrafa í build.sh skriptunni svo þú þarft ekki að stilla neitt sjálfur.

## Möppuskipan 
<pre> ``` src/
├── main/
│   ├── java/
│   │   └── hi/
│   │       └── flappybird/
│   │           ├── vidmot/
│   │           │   ├── BirdSelectionController.java
│   │           │   ├── FlappyBirdApplication.java
│   │           │   ├── GameSceneController.java
│   │           │   └── MainMenuController.java
│   │           └── vinnsla/
│   │               ├── Bird.java
│   │               ├── BirdMovement.java
│   │               ├── BlueBird.java
│   │               ├── CollisionHandler.java
│   │               ├── FastSpeedStrategy.java
│   │               ├── NormalSpeedStrategy.java
│   │               ├── ObstaclesHandler.java
│   │               ├── PinkBird.java
│   │               ├── SelectedBird.java
│   │               └── SpeedStrategy.java
│   ├── resources/
│   │   └── hi/
│   │       └── flappybird/
│   │           ├── bird-selection.fxml
│   │           ├── game-scene.fxml
│   │           ├── main-menu.fxml
│   │           ├── images/
│   │           └── ├── pinkbird1.png
│   │               ├── pinkbird2.png
│   │               └── ...
│   │           
│   └── module-info.java
├── test/
│   └── java/
│       └── hi/
│           └── flappybird/
│               ├── BirdMovementTest.java
│               ├── BirdTest.java
│               ├── BlueBirdTest.java
│               ├── CollisionHandlerTest.java
│               ├── JavaFXTestBase.java
│               ├── ObstaclesHandlerTest.java
│               ├── PinkBirdTest.java
│               └── SelectedBirdTest.java
📄 pom.xml
📄 build.sh
📄 run.sh
📄 README.md ``` </pre>


## Skráalýsing
FlappyBirdApplication.java – Byrjar leikinn og opnar menu
GameSceneController.java – Heldur utan um leik, fugl og stig
MainMenuController.java – Stýrir menu og PLAY hnappi
ObstaclesHandler.java – Býr til og hreyfir hindranir
BirdMovement.java – Heldur utan um hreyfingu fuglsins
CollisionHandler.java – Athugar hvort fugl rekst á hindrun
ObstaclesHandler.java - Býr til og hreyfir hindranir
.fxml skrár - Skilgreina útlit (UI) fyrir leik og main menu

## Kröfur
OpenJDK 23
Maven
JavaFX (með maven)
Höfundar
Nafn: Stefanía Guðrún Harðardóttir og Brynja Kristín Bertelsdóttir
Áfangi: Viðmótsforritun, vor 2025 lokaverkefni, Háskóli Íslands

## Licence
[Licence](/Users/brynja.kristin/IdeaProjects/HBV202G-assLOKA/LICENCE)

## Design
[Design](src/site/resources/UML.png)

## Staða verkefnis
Verkefnið er tilbúið sem demo. Leikurinn virkar, stig eru talin rétt og viðmótið birtir valkosti við leikslok. Í framtíðinni væri hægt að bæta við:  
- hljóðum og bakgrunnstónlist
- Þemum til að velja úr
- high score
