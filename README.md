novapay-android
=======

Novapay Android SDK

Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.

Adding dependency
=======
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