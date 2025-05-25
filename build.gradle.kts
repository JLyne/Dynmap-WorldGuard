import net.minecrell.pluginyml.bukkit.BukkitPluginDescription
import net.minecrell.pluginyml.paper.PaperPluginDescription

plugins {
    java
    alias(libs.plugins.pluginYmlPaper)
}

group = "org.dynmap"
version = "1.3-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
	mavenLocal()
	mavenCentral()
	maven {
		url = uri("https://repo.papermc.io/repository/maven-public/")
	}
    maven {
        url = uri("https://repo.mikeprimm.com/")
    }
    maven {
        url = uri("https://maven.enginehub.org/repo/")
    }
}

dependencies {
	compileOnly(libs.paperApi)
	compileOnly(libs.worldedit)
	compileOnly(libs.dynmap)
	compileOnly(libs.worldguard)
}

paper {
    main = "org.dynmap.worldguard.DynmapWorldGuardPlugin"
    apiVersion = libs.versions.paperApi.get().replace(Regex("\\-R\\d.\\d-SNAPSHOT"), "")
    authors = listOf("mikeprimm", "Jim (AnEnragedPigeon)")
    description = "Displays WorldGuard regions on Dynmap"

    serverDependencies {
        register("dynmap") {
            load = PaperPluginDescription.RelativeLoadOrder.BEFORE
            required = true
        }
        register("WorldGuard") {
            load = PaperPluginDescription.RelativeLoadOrder.BEFORE
            required = true
        }
    }
}

tasks {
    compileJava {
        options.compilerArgs.addAll(listOf("-Xlint:all", "-Xlint:-processing"))
        options.encoding = "UTF-8"
    }
}
