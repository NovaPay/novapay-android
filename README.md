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
    implementation("ua.novapay:sdk:1.0.2")
    ```

## 🚀 Getting Started

### Initialization

Initialize the SDK in your application or activity:

```kotlin
PaymentSdk.initialize(
    context = context,
    environment = EnvironmentType.DEVELOPMENT // Options: DEVELOPMENT, STAGING, PRODUCTION
)
```

## 💳 Features

### Payment Sheet

The SDK provides a ready-to-use payment sheet that handles the complete payment flow:

```kotlin
PaymentSdk.showPaymentSheet(
    fragmentActivity = activity,
    sessionId = "your_session_id", // Session ID received from server's /init endpoint
    sessionStatusCallback = { status -> 
        // Handle session status updates
    },
    sessionErrorCallback = { message -> 
        // Handle session error message
    },
    paymentSheetStatusCallback = { status ->
        // Handle payment sheet status updates
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
val sessionStatus = PaymentSdk.getSession(sessionId)
```

```kotlin
data class SessionStatusResult(
    val status: String?,
    @SerializedName("reason_uk") val reasonUk: String?,
    @SerializedName("reason_en") val reasonEn: String?
)
```

## 📱 Integration Example

```kotlin
class YourActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize SDK
        PaymentSdk.initialize(
            context = this,
            environment = EnvironmentType.DEVELOPMENT
        )
        
        // Show payment sheet
        PaymentSdk.showPaymentSheet(
            fragmentActivity = this,
            sessionId = "your_session_id",
            sessionStatusCallback = { status ->
                when (status) {
                    SessionPaymentStatusType.HOLDED -> handleSuccess()
                    SessionPaymentStatusType.PAID -> handleSuccess()
                    SessionPaymentStatusType.FAILED -> handleFailure()
                    else -> handleOtherStatus(status)
                }
            },
            sessionErrorCallback = { message ->
                // Handle session error message
            },
            paymentSheetStatusCallback = { status ->
                Log.d("PaymentSheet", status)
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