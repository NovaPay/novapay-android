novapay-android
=======

Novapay Android SDK

Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.

Adding dependency
=======
1. Clone project, it contains repo folder with library and its dependencies
2. Add repo folder to your root project directory
3. Inside your project at `settings.gradle` under `dependencyResolutionManagement` add `novapay-android` as a maven repository
```
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        //...
        maven {
            url = uri("novapay-android")
        }
    }
}
```
4. At module-level `build.gradle` add `implementation("ua.novapay:sdk:1.0.0")`



1. Inside your project at `settings.gradle` under `dependencyResolutionManagement` add `https://storage.googleapis.com/np_public` as a maven repository
```
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        //...
        maven {
            url = uri("https://storage.googleapis.com/np_public")
        }
    }
}
```
2. At module-level `build.gradle` add `implementation("com.novapaysdk:nova_pay_sdk:1.0.0")`