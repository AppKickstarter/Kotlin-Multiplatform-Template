package cli.app

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.default
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.prompt
import com.github.ajalt.clikt.parameters.types.file
import com.github.ajalt.clikt.parameters.types.int
import java.io.File

/*
fun main(args: Array<String>) {
    //val parser = ArgParser("${App.appName}:: ${App.version}")
    val parser = ArgParser("example")
   // val version by parser.option(ArgType.Boolean, shortName = "V", description = "Version").default(false)

    println("Welcome to AppKickstarter. \n" +
            "Here you'll be guided to setup the template for your appcc.\n")

    println("Hey test")
    val appName by parser.option(ArgType.String, description = "Enter your app's name").required()

    parser.parse(args)
    println("Hey appName $appName")
   // if(version) println(App.version)
}
*/

class Cli : CliktCommand() {
    val kotlinOldAndroidApp by argument().file()
        .default(File("androidApp/src/main/java/com/lduboscq/appkickstarter"))

    val appName: String by option().prompt("Enter the name of your app")
    val packageName: String by option().prompt("Enter the package name (com.example.app)")

    val firebaseSetup: String by option().prompt(
        "\nClick here to init Firebase\n" +
                "https://console.firebase.google.com\n" +
                "Press enter to continue",
        default = "",
    )
    val googleServicesJson: String by option().prompt(
        "\nAdd the google-services.json in the androidApp module\n" +
                "Press enter to continue",
        default = "",
    )

    val androidAppModule by argument().file().default(File("androidApp"))
    val androidAppBuildGradle by argument().file().default(File("androidApp/build.gradle.kts"))

    override fun run() {
        val appIdToChange = "com.lduboscq.appkickstarter.android"
        val androidAppBuildGradleContent = androidAppBuildGradle.readText()
        androidAppBuildGradle.delete()
        androidAppBuildGradle.createNewFile()
        androidAppBuildGradle.writeText(
            androidAppBuildGradleContent.replace(
                appIdToChange,
                packageName
            )
        )

        setupAppName()
        setupAndroidApp(packageName)
        setupFirebase(packageName)
        setupShared(packageName)

        // todo git add .
        // todo git commit -m "setup $appName app ($packageName)"

        echo("\nSetup finished. Thanks to use AppKickstarter.")
        echo("Your turn to build amazing apps!")
    }

    private fun setupAndroidApp(packageName: String) {

        val manifest = File("androidApp/src/main/AndroidManifest.xml")
        val manifestContent = manifest.readText()
        manifest.delete()
        manifest.createNewFile()
        manifest.writeText(manifestContent.replace("com.lduboscq.appkickstarter", packageName))

        val kotlinNewAndroidApp =
            File("androidApp/src/main/kotlin/${packageName.replace(".", "/")}")
        kotlinNewAndroidApp.mkdirs()
        kotlinOldAndroidApp.copyRecursively(kotlinNewAndroidApp, overwrite = true)
        kotlinOldAndroidApp.deleteRecursively()
        File("androidApp/src/main/java").deleteRecursively()

        kotlinNewAndroidApp
            .walkTopDown()
            .filter { it.isFile }
            .forEach {
                val content = it.readText()
                it.delete()
                it.createNewFile()
                it.writeText(content.replace("com.lduboscq.appkickstarter", packageName))
            }
    }

    private fun setupAppName() {
        val appNameToChange = "AppKickstarter"

        val stringsxml = File("shared/src/androidMain/res/values/strings.xml")
        val stringsXmlContent = stringsxml.readText()
        stringsxml.delete()
        stringsxml.createNewFile()
        stringsxml.writeText(stringsXmlContent.replace(appNameToChange, appName))

        val settingsGradle = File("settings.gradle.kts")
        val settingsContent = settingsGradle.readText()
        settingsGradle.delete()
        settingsGradle.createNewFile()
        settingsGradle.writeText(settingsContent.replace(appNameToChange, appName))
    }

    private fun setupFirebase(newPackage: String) {
        File("firebase")
            .walkTopDown()
            .filter { it.isFile /*&& it.name != "build.gradle.kts" */}
            .forEach {
                val content = it.readText()
                it.delete()
                it.createNewFile()
                it.writeText(
                    content
                        .replace("com.lduboscq.appkickstarter", newPackage)
                        .replace("com.lduboscq.firebase", newPackage)
                )
            }
/*
        val buildGradle = File("firebase/build.gradle.kts")
        val content = buildGradle.readText()
        buildGradle.delete()
        buildGradle.createNewFile()
        buildGradle.writeText(
            content
                .replace("com.lduboscq.appkickstarter", newPackage)
        )
*/
        val commonMainFirebase = File("firebase/src/commonMain/kotlin/com/lduboscq/appkickstarter")
        commonMainFirebase
            .copyRecursively(
                File("firebase/src/commonMain/kotlin/${packageName.replace(".", "/")}")
            )
        commonMainFirebase.deleteRecursively()

        val androidMainFirebase = File("firebase/src/androidMain/kotlin/com/lduboscq/appkickstarter")
        androidMainFirebase
            .copyRecursively(
                File("firebase/src/androidMain/kotlin/${packageName.replace(".", "/")}")
            )
        androidMainFirebase.deleteRecursively()

        val iosMainFirebase = File("firebase/src/iosMain/kotlin/com/lduboscq/firebase")
        iosMainFirebase
            .copyRecursively(
                File("firebase/src/iosMain/kotlin/${packageName.replace(".", "/")}")
            )
        iosMainFirebase.deleteRecursively()
    }

    private fun setupShared(newPackage: String) {

        File("shared")
            .walkTopDown()
            .filter { it.isFile }
            .forEach {
                val content = it.readText()
                it.delete()
                it.createNewFile()
                it.writeText(
                    content
                        .replace("com.lduboscq.appkickstarter", newPackage)
                )
            }

        val commonMainFirebase = File("shared/src/commonMain/kotlin/com/lduboscq/appkickstarter")
        commonMainFirebase
            .copyRecursively(
                File("shared/src/commonMain/kotlin/${packageName.replace(".", "/")}")
            )
        commonMainFirebase.deleteRecursively()

        val androidMainFirebase = File("shared/src/androidMain/kotlin/com/lduboscq/appkickstarter")
        androidMainFirebase
            .copyRecursively(
                File("shared/src/androidMain/kotlin/${packageName.replace(".", "/")}")
            )
        androidMainFirebase.deleteRecursively()

        val iosMainFirebase = File("shared/src/iosMain/kotlin/com/lduboscq/appkickstarter")
        iosMainFirebase
            .copyRecursively(
                File("shared/src/iosMain/kotlin/${packageName.replace(".", "/")}")
            )
        iosMainFirebase.deleteRecursively()
    }
}
