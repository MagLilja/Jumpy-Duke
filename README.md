
Jag har några saker och frågor som jag tycker är viktigt att det kommer med och vi svarar på i vår ReadMe:

- Vad är det för projekt?
    - Förklara spelet.
    - Strukturen?
        - Spelet, hemsidan, databasen etc.
        - Klasser, konfiguration, etc?
- Varför har vi gjort projektet?
- Vad har vi använt för verktyg etc?
    - Vilket ramverk är spelet byggt i? Varför?
    - Vilket byggverktyg har vi? Varför?
    - Vilka direkta dependencies har vi i applikationen? Varför? Vad används dom till?
- Hur bygger man projektet?
- Hur kör man spelet
    - Vilka alternativ finns?


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

