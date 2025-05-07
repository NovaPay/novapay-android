# Novapay Android SDK

Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.

## Adding dependency

### Local repository

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


### Remote repository (prefered)

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

## Initialization

Initialize the SDK in your application or activity:

```kotlin
PaymentSdk.initialize(
    context = context,
    environment = "development" // or "staging" for testing
)
```

## Features

### 1. Payment Sheet
The SDK provides a ready-to-use payment sheet that handles the payment flow:

```kotlin
PaymentSdk.showPaymentSheet(
    fragmentActivity = activity,
    sessionId = "your_session_id",
    sessionStatusCallback = { status -> 
        // Handle session status updates
    },
    paymentSheetStatusCallback = { status ->
        // Handle payment sheet status updates
    }
)
```

sessionId - this is session_id retuned by server /init method

To dismiss the payment sheet:
```kotlin
PaymentSdk.dismissPaymentSheet(activity)
```

### 2. Payment Methods
The SDK supports multiple payment methods:
- Manual card payments
- Wallet card
- Google Pay
- Apple Pay

### 3. Session Management
You can check the status of a payment session:

```kotlin
val sessionStatus = PaymentSdk.getSession(sessionId)
```

## Integration Example

Here's a basic example of how to integrate the SDK:

```kotlin
class YourActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize SDK
        PaymentSdk.initialize(
            context = this,
            environment = "development"
        )
        
        // Show payment sheet when needed
        PaymentSdk.showPaymentSheet(
            fragmentActivity = this,
            sessionId = "your_session_id",
            sessionStatusCallback = { status ->
                // Handle status updates
                when (status) {
                    "SUCCESS" -> handleSuccess()
                    "FAILED" -> handleFailure()
                    else -> handleOtherStatus(status)
                }
            },
            paymentSheetStatusCallback = { status ->
                // Handle payment sheet status
                Log.d("PaymentSheet", status)
            }
        )
    }
}
```

## Requirements

- Android API level 21 or higher
- Google Play Services (for Google Pay functionality)
- Internet permission in your AndroidManifest.xml:
```xml
<uses-permission android:name="android.permission.INTERNET" />
```

## Environment Configuration

The SDK supports three environments:
- `development` - For development and testing
- `staging` - For pre-production testing
- `production` - For production

## Error Handling

The SDK provides status callbacks for both session and payment sheet status updates. Make sure to implement proper error handling in your callbacks.

## Security

- The SDK handles sensitive payment information securely
- All communication is done over HTTPS
- Card details are never stored on the device unless explicitly requested

## Support

For any issues or questions, please contact NovaPay support.

## License

This SDK is proprietary software. Please refer to your license agreement for usage terms and conditions.

---

This documentation provides a basic overview of the NovaPay Android SDK. For more detailed information about specific features or implementation details, please refer to the example app included in the SDK package. 