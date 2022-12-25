plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("xyz.jpenilla.run-paper") version "2.0.0"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.2"
}

group = "party.morino"
version = "0.1"

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://jitpack.io")

    // Paper
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    // Paper
    compileOnly("io.papermc.paper", "paper-api", "1.19.3-R0.1-SNAPSHOT")
}

bukkit {
    name = rootProject.name
    version = project.version as String
    main = "party.morino.yukigassen.YukiGassen"
    apiVersion = "1.19"
    description = "雪合戦"
    author = "Unitarou"
    website = "https://morino.party"
}

tasks {
    shadowJar {
        this.archiveClassifier.set(null as String?)
    }
    runServer {
        minecraftVersion("1.19.3")
    }
}
