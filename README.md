# NovaPay Android SDK

A powerful and secure payment processing SDK for Android applications that enables seamless integration of various payment methods.

## 📦 Installation

### Remote Repository (Recommended)

1. Add the NovaPay repository to your `settings.gradle`:
    ```gradle
    dependencyResolutionManagement {
        repositories {
            google()
            mavenCentral()
            // Add NovaPay repository
            maven {
                url = uri("https://storage.googleapis.com/np_public")
            }
        }
    }
    ```
2. Add the dependency in your module's `build.gradle`:
    ```gradle
    implementation("ua.novapay:sdk:1.3.22")
    ```

## 🚀 Getting Started

### Initialization

Initialize the SDK in your application or activity:

```kotlin
PaymentSdk.initialize(
    context = context,
    environment = EnvironmentType.DEVELOPMENT, // Options: DEVELOPMENT, STAGING, PRODUCTION
    language = LanguageType.UK // Options: UK, EN
)
```

## 💳 Features

### Payment Sheet

The SDK provides a ready-to-use payment sheet that handles the complete payment flow:

```kotlin
PaymentSdk.showPaymentSheet(
    fragmentActivity = activity,
    sessionId = "your_session_id", // Session ID received from server's /init endpoint
    themeMode = SdkThemeMode.SYSTEM, // Options: LIGHT, DARK, SYSTEM
    paymentSheetStatus = { status -> 
        // Handle session status updates
        when (status) {
            is PaymentSheetStatus.Completed -> { 
                // Handle complete 
            }
            is PaymentSheetStatus.Failed -> {
                // Handle error message 
            }
            is PaymentSheetStatus.Canceled -> {
                // Handle canceled 
            }
        }
    }
)
```

To dismiss the payment sheet:
```kotlin
PaymentSdk.dismissPaymentSheet(activity)
```

### Supported Payment Methods

- 💳 Manual card payments
- 👛 Wallet card
- 🏷️ Google Pay

### Session Management

Check the status of a payment session:

```kotlin
lifecycleScope.launch {
    PaymentSdk.startSessionsStatusPooling(sessionId).collect { status ->
    }
}
```

Stop sessions status pooling:

```kotlin
PaymentSdk.stopSessionsStatusPooling()
```

### Wallet Sheet

The SDK provides settings for managing payment methods and preferences:

```kotlin
PaymentSdk.showWalletSheet(context, token)
```

### Payout Sheet

The SDK provides initiating a payout process:

```kotlin
PaymentSdk.showPayoutSheet(
    context = context,
    sessionId = "your_session_id", // Session ID received from server's /init-payout endpoint
    themeMode = SdkThemeMode.SYSTEM, // Options: LIGHT, DARK, SYSTEM
    payoutSheetStatus = { status -> 
        // Handle session status updates
        when (status) {
            is PayoutSheetStatus.Completed -> { 
                // Handle complete 
            }
            is PayoutSheetStatus.Failed -> {
                // Handle error message 
            }
            is PayoutSheetStatus.Canceled -> {
                // Handle canceled 
            }
        }
    })
```

## 📱 Integration Example

```kotlin
class YourActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize SDK
        PaymentSdk.initialize(
            context = this,
            environment = EnvironmentType.DEVELOPMENT,
            language = LanguageType.UK
        )
        
        // Show payment sheet
        PaymentSdk.showPaymentSheet(
            fragmentActivity = this,
            sessionId = "your_session_id",
            themeMode = SdkThemeMode.SYSTEM, // Options: LIGHT, DARK, SYSTEM
            paymentSheetStatus = { status ->
                when (status) {
                    is PaymentSheetStatus.Completed -> handleSuccess()
                    is PaymentSheetStatus.Canceled -> handleCanceled()
                    is PaymentSheetStatus.Failed -> handleFailure(message = status.error)
                }
            }
        )
    }
}
```

## ⚙️ Requirements

- Android API level 21+
- Google Play Services (for Google Pay)
- Internet permission in `AndroidManifest.xml`:
    ```xml
    <uses-permission android:name="android.permission.INTERNET" />
    ```
- Meta-data in AndroidManifest.xml to enable Google Pay:
   ```xml
    <meta-data
    android:name="com.google.android.gms.wallet.api.enabled"
    android:value="true" />
    ```

- Meta-data in AndroidManifest.xml to enable Google Pay:
   ```xml
    <meta-data
    android:name="com.google.android.gms.wallet.api.enabled"
    android:value="true" />
    ```

## 🛡️ ProGuard / R8 Rules

If you use ProGuard or R8, add the following rules to your `proguard-rules.pro` file:

```pro
-keep class ua.novapay.sdk.presentation.** { *; }
-keep class ua.novapay.sdk.domain.model.** { *; }
-keep class ua.novapay.sdk.data.model.** { *; }
-keep class ua.novapay.sdk.data.cache.bd.entity.** { *; }
-keep class ua.novapay.sdk.data.interceptor.** { *; }
-dontwarn java.lang.invoke.StringConcatFactory
```

## 🌍 Environment Configuration

| Environment | Description |
|------------|-------------|
| `development` | Development and testing environment |
| `staging` | Pre-production testing environment |
| `production` | Production environment |

## 🔒 Security Features

- Secure handling of sensitive payment information
- HTTPS communication
- Optional card details storage
- Comprehensive error handling through status callbacks

## 📞 Support

For technical support or questions, please contact NovaPay support team.

## 📄 License

This SDK is proprietary software. Usage terms and conditions are defined in your license agreement.

---

For detailed implementation examples and advanced features, please refer to the example app included in the SDK package. 
