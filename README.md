
Jag har några saker och frågor som jag tycker är viktigt att det kommer med och vi svarar på i vår ReadMe:

- Vad är det för projekt?
    - Förklara spelet.

    - Strukturen?
        - Spelet, hemsidan, databasen etc.

        - Klasser, konfiguration, etc?
          - Vi använder Libgdx med vad som kallas screens.
          - Vi har även actors som ligger i ett actor pack.
              - Se bild och komplettera med diagrams.draw
              - Googla generate diagram plugin för intellij
              - Markera projektet och tryck ctrl+shift+alt+u för att få diagram
          
- Varför har vi gjort projektet?







# Jumpy Duke The Game!
![](assets_to_be_packed/DukeThree.png)

## Description

### What?

An interactive platform game inspired by Flappy Bird.
You fly around with Java's own Duke and try to catch bugs and avoid obstacles.
Simple but elegant. The space key is almost the only one you need to keep an eye on.

In the game you get points, which are saved online in a MySQL database connected to the game and the website
through a NodeJS-Express routing application.

The application and the website can be found at this repository:

https://github.com/MagLilja/Jumpy-Duke-Website

### Why?

This project was made as an assignment for a course in Agile Software Development.

We were given the task of creating a game in the style of Flappy Bird and work according to Scrum methods.

The goal was to practice planning, structure and cooperation and to 
hopefully get a taste of what it's like in a real commercial project.

## Dependencies and Components

The game is written in Java 11 with the OpenGL (ES) game development framework [LibGDX](https://libgdx.com/). 

The build tool used is Gradle. 

## Building and running with Gradle

If you do not have gradle installed on your system you can clone 
this repository and build with the Gradle Wrapper. 
```
./gradlew build.
```

To run the program run the following command:

Clone this repository and build with the Gradle Wrapper
```
./gradlew build.
```

## Building a distribution jar

To build a runnable jar file run the following command:

Clone this repository and build with the Gradle Wrapper
```
./gradle dist
```

## Installing

### Windows/exe

* The game can be downloaded as an installable exe-file on the [website](https://jumpyduke.com/download.html)

### Creating exe installer with JPackage

- Activate .Net Framework 3.5
- Install [WiX Toolset](https://wixtoolset.org/)
- Put jar file in a folder 
- Run the following command on the same folder 
```
$ jpackage --input . --dest output --name jumpyDuke --main-jar desktop-1.0.jar --main-class se.yrgo.jumpyduke.desktop.DesktopLauncher --type exe 
```


## Screenshots

### Game
![img.png](Storyboard/img.png)
![img_1.png](Storyboard/img_1.png)
![img_2.png](Storyboard/img_2.png)
![img_3.png](Storyboard/img_3.png)
### Website

## Other tools and acknowledgments 

- IntelliJ for being an awsome IDE.
- Insomnia for doing our http requests. 
- Jenkins for CD
- GIT/GITHUB for CI


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

