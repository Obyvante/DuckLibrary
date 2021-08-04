<p align="center">
<img src="https://imgur.com/BwOX9Pw.png">
</p>
<p align="center">
<a href="https://github.com/Obyvante/DuckLibrary/blob/main/LICENSE"><img src="https://img.shields.io/github/license/Obyvante/DuckLibrary?color=red"></a>
<a href="https://github.com/Obyvante/DuckLibrary/issues"><img src="https://img.shields.io/github/issues/Obyvante/DuckLibrary"></a>
<a href="https://lgtm.com/projects/g/Obyvante/DuckLibrary"><img src="https://img.shields.io/lgtm/grade/java/g/Obyvante/DuckLibrary.svg?logo=lgtm&logoWidth=18"></a>
</p>

Duck Library is a library for developers who don't want to spend their time to write same library consistently. It has
almost every useful feature to develop any kind of Minecraft server.

### Supporting versions: 1.8.x to 1.17.x

### Libraries

- MySQL, MongoDB, Redis and InfluxDB
- Messages such as title and action bar.
- Packet
- Hologram
- NPC
- Particle
- Scoreboard
- World border
- Player list (TAB)
- Name tag
- Sign and anvil UI
- Chat input handler
- Custom logger (info, warn, error adn debug)
- Duck Chain (Communicating between servers via Redis channels)
- Proxy security
- Proxy connection metadata (Binding metadata to player profile and listen in bukkit server.)
- Metrics
- Bukkit simplifier such as location, rotation, vectors and packets.

### First Installation

If you want to use as a JAR dependency, you can download the latest
build [here](https://github.com/Obyvante/DuckLibrary/issues)

Using as a maven dependency

```xml
<repository>
    <id>obyvante-releases</id>
    <url>https://repo.obyvante.com/repository/maven-releases/</url>
</repository>
```

```xml
<dependency>
    <groupId>com.obyvante</groupId>
    <artifactId>duck-library</artifactId>
    <version>0.0.1</version>
</dependency>
```

### Documentation

You can check our detailed [GitHub wiki](https://github.com/Obyvante/DuckLibrary/wiki).

For Javadoc, you can take a look at [here](https://github.com/Obyvante/DuckLibrary/issues).