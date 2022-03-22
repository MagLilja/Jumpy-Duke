
Jag har några saker och frågor som jag tycker är viktigt att det kommer med och vi svarar på i vår ReadMe:

- Vad är det för projekt?
    - Förklara spelet.
      - An interactive platform game inspired by Flappy Bird.
      - Where you fly around with Java's own Duke and try to catch bugs and avoid obstacles.
      - Simple but elegant. The space key is almost the only one you need to keep an eye on.
    - Strukturen?
        - Spelet, hemsidan, databasen etc.
          - The game is written in Java 11.
          - In the game you get points, which are saved online by the game sending / posting the points to an api.
          - The API then saves the points in a database on the same server.
          - The website retrieves / gets the points from the apie, which in turn retrieves it from the database.
          - (The API is Restful-Node-Express and the database is MySQL)
        - Klasser, konfiguration, etc?
          - Vi använder Libgdx med vad som kallas screens.
          - Vi har även actors som ligger i ett actor pack.
              - Se bild och komplettera med diagrams.draw
              - Googla generate diagram plugin för intellij
              - Markera projektet och tryck ctrl+shift+alt+u för att få diagram
          
- Varför har vi gjort projektet?
  - We have taken a course in Agila project methods.
  - In the course we were given the task of creating a game in the style of Flappy Bird and
  - work based on the Scrum method.
  - To practice planning, structure and cooperation.
  - To hopefully get a taste of what it is like in working life.

- Vad har vi använt för verktyg etc?
    - Vilket ramverk är spelet byggt i? Varför?
      - Libgdx, which is a more modern tool than swing and more adapted for the purpose.
    - Vilket byggverktyg har vi? Varför?
      - Gradel, because Libgdx project generator creates the project with Gradel.
    - Vilka direkta dependencies har vi i applikationen? Varför? Vad används dom till?
      - We have used Git and GitHub for version control.
      - As our IDE, we have used Intellij because it's just soo good!
      - Jenkins for continuous construction and testing.
  
- Hur bygger man projektet?
  - To build the project run ./gradlew build.
  
- Hur kör man spelet
    - Vilka alternativ finns?
      - Download an .exe or .jar file via jumpyduke.com.
      - Alternatively, download the source code and run it directly via your IDE.
      - Clone the repo and run.
      - To run the program run ./gradlew run.


![](assets_to_be_packed/DukeThree.png)

# Project Title

Simple overview of use/purpose.

## Description

An in-depth paragraph about your project and overview of use.

## Getting Started

### Dependencies

* Describe any prerequisites, libraries, OS version, etc., needed before installing program.
* ex. Windows 10

### Installing

* How/where to download your program
* Any modifications needed to be made to files/folders

#### Creating exe installer with JPackage

- Activate .Net Framework 3.5
- Install [WiX Toolset](https://wixtoolset.org/)
- Put jar file in a folder 
- Run the following command on the same folder 
```
$ jpackage --input . --dest output --name jumpyDuke --main-jar desktop-1.0.jar --main-class se.yrgo.jumpyduke.desktop.DesktopLauncher --type exe 
```


### Executing program

* How to run the program
* Step-by-step bullets
```
code blocks for commands
```

## Help

Any advise for common problems or issues.
```
command to run if program contains helper info
```

## Authors

Contributors names and contact info

ex. Dominique Pizzie  
ex. [@DomPizzie](https://twitter.com/dompizzie)

## Version History

* 0.2
    * Various bug fixes and optimizations
    * See [commit change]() or See [release history]()
* 0.1
    * Initial Release

## License

This project is licensed under the [NAME HERE] License - see the LICENSE.md file for details

## Acknowledgments

Inspiration, code snippets, etc.
* [awesome-readme](https://github.com/matiassingers/awesome-readme)
* [PurpleBooth](https://gist.github.com/PurpleBooth/109311bb0361f32d87a2)
* [dbader](https://github.com/dbader/readme-template)
* [zenorocha](https://gist.github.com/zenorocha/4526327)
* [fvcproductions](https://gist.github.com/fvcproductions/1bfc2d4aecb01a834b46)

