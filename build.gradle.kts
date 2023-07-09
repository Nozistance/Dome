plugins {
    id ("com.modrinth.minotaur") version "2.+"
    id ("fabric-loom") version "1.2-SNAPSHOT"
}

group = property("maven_group")!!
version = property("mod_version")!!

repositories {
    mavenCentral()
    maven("https://maven.shedaniel.me/")
    maven("https://maven.terraformersmc.com/releases/")
}

dependencies {
    modImplementation("net.fabricmc:fabric-loader:${property("loader_version")}")
    minecraft("com.mojang:minecraft:${property("minecraft_version")}")
    mappings("net.fabricmc:yarn:${property("yarn_mappings")}:v2")

    modImplementation("me.shedaniel.cloth:cloth-config-fabric:${property("cloth_config_version")}") {
        exclude(group = "net.fabricmc.fabric-api")
    }

    modImplementation("com.terraformersmc:modmenu:${property("modmenu_version")}")

    compileOnly("org.projectlombok:lombok:${property("lombok_version")}")
    annotationProcessor("org.projectlombok:lombok:${property("lombok_version")}")
}

tasks {
    processResources {
        inputs.property("version", project.version)
        filesMatching("fabric.mod.json") {
            expand(mutableMapOf("version" to project.version))
        }
    }
}

modrinth {
    versionType.set("release")
    loaders.addAll("fabric", "quilt")
    uploadFile.set(tasks.remapJar.get())
    token.set(System.getenv("MODRINTH_TOKEN"))
    projectId.set("${property("archives_base_name")}")
    gameVersions.addAll("${property("minecraft_version")}")
    syncBodyFrom.set(rootProject.file("README.md").readText())
    dependencies {
        required.version("y0kQixP8") // cloth-config
        optional.version("RTFDnTKf") // modmenu
    }
}