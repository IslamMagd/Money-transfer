# Money Transfer Application

## Overview
 The Money Transfer application is a secure and user-friendly Android app developed using Kotlin and Jetpack Compose. Designed to facilitate convenient and seamless money transfers, this app incorporates essential features typical of online banking and mobile money solutions, offering users a familiar and reliable experience for managing their transactions.


 # Clean Architecture (UI-Data-Domain)

 ## Features
 - **User Accounts and Profiles:** Users can create profiles, set up online accounts, and link them to bank accounts.

- **Internal and External Transfers:** Users can transfer funds between their own accounts (internal) or to other users within the app (internal) or to external accounts.

- **Account Management:**: Users can view account balances, transaction history, and manage personal information.

- **Change Currency**: The app will display the user's current country's currency and allow conversions between supported currencies for international transfers.

- **Favorite Recipients Management**: Save and manage favorite recipients for quick transfers.


## Demo Video
Watch a detailed video overview of the Money Transfer app functionality on [Google Drive](https://drive.google.com/drive/folders/1tX-A7dYMzYat_SedTjjiIYJSuRADxrSS?usp=sharing).



## Screenshots
- **onboarding screens**
 <div style="display: flex; justify-content: space-between; flex-wrap: wrap; gap: 10px;">
  <img src="screenshots/onboardingScreens/onboarding 1.jpg" alt="Amount" width="20%">
  <img src="screenshots/onboardingScreens/onboarding 2.jpg" alt="Confirmation" width="20%">
  <img src="screenshots/onboardingScreens/onboarding 3.jpg" alt="Payment" width="20%">
</div>

- **Sign in & sigin up screens**
 <div style="display: flex; justify-content: space-between; flex-wrap: wrap; gap: 10px;">
  <img src="screenshots/sign in &sign up/splash screen.jpg" alt="Splash" width="20%">
  <img src="screenshots/sign in &sign up/sign up validation.jpg" alt="sign up" width="20%">
  <img src="screenshots/sign in &sign up/sign up.jpg" alt="sign up" width="20%">
  <img src="screenshots/sign in &sign up/complete sign up.jpg" alt="sign up" width="20%">
  <img src="screenshots/sign in &sign up/country sheet.jpg" alt="sign up" width="20%">
  <img src="screenshots/sign in &sign up/date picker.jpg" alt="Sign up" width="20%">
  <img src="screenshots/sign in &sign up/sign in.jpg" alt="sign up" width="20%">
</div>

- **Add Card screens**
 <div style="display: flex; justify-content: space-between; flex-wrap: wrap; gap: 10px;">
  <img src="screenshots/cards/my accounts 1.jpg" alt="My Accounts" width="20%">
  <img src="screenshots/cards/select currency.jpg" alt="Select Currency" width="20%">
  <img src="screenshots/cards/add card.jpg" alt="Add Card" width="20%">
  <img src="screenshots/cards/otp empty.jpg" alt="OTP" width="20%">
  <img src="screenshots/cards/otp email.jpg" alt="OTP Email" width="20%">
  <img src="screenshots/cards/otp error.jpg" alt="OTP" width="20%">
  <img src="screenshots/cards/otp correct.jpg" alt="OTP" width="20%">
  <img src="screenshots/cards/otp connected.jpg" alt="OTP Connected" width="20%">
  <img src="screenshots/cards/my accounts 2.jpg" alt="My Accounts" width="20%">
</div>

- **Transfer Money screens**
 <div style="display: flex; justify-content: space-between; flex-wrap: wrap; gap: 10px;">
  <img src="screenshots/transfer/home screen 1.jpg" alt="Home screen" width="20%">
  <img src="screenshots/transfer/amount screen 1.jpg" alt="Amount screen" width="20%">
  <img src="screenshots/transfer/amount screen 2.jpg" alt="Add Card" width="20%">
  <img src="screenshots/transfer/favorite sheet.jpg" alt="favorite" width="20%">
  <img src="screenshots/transfer/confirmation screen.jpg" alt="confirmation" width="20%">
  <img src="screenshots/transfer/payment screen.jpg" alt="payment screen" width="20%">
  <img src="screenshots/transfer/home screen 2.jpg" alt="home screen" width="20%">
</div>

- **Transaction screens**
 <div style="display: flex; justify-content: space-between; flex-wrap: wrap; gap: 10px;">
  <img src="screenshots/transactions/transactions screen.jpg" alt="transactions screen" width="20%">
  <img src="screenshots/transactions/transaction details.jpg" alt="transaction details" width="20%">
</div>

- **Error screens**
 <div style="display: flex; justify-content: space-between; flex-wrap: wrap; gap: 10px;">
  <img src="screenshots/error/internet error.jpg" alt="internet error" width="20%">
  <img src="screenshots/error/server error.jpg" alt="server error" width="20%">
</div>

- **More screens**
 <div style="display: flex; justify-content: space-between; flex-wrap: wrap; gap: 10px;">
  <img src="screenshots/more/more screen.jpg" alt="more screen" width="20%">
  <img src="screenshots/more/favorite list.jpg" alt="favorite list" width="20%">
  <img src="screenshots/more/help sheet.jpg" alt="more screen" width="20%">
</div>

- **Profile screens**
 <div style="display: flex; justify-content: space-between; flex-wrap: wrap; gap: 10px;">
  <img src="screenshots/profile/profile screen.jpg" alt="profile screen" width="20%">
  <img src="screenshots/profile/profile Info.jpg" alt="profile Info" width="20%">
  <img src="screenshots/profile/setting screen.jpg" alt="setting screen" width="20%">
  <img src="screenshots/profile/change password.jpg" alt="change password" width="20%">
  <img src="screenshots/profile/edit profile.jpg" alt="edit profile" width="20%">
</div>

## Dependencies
* [Jetpack Compose](https://developer.android.com/jetpack/compose) 
* [Compose Navigation](https://developer.android.com/jetpack/compose/navigation) 
* [Retrofit](https://github.com/square/retrofit) 
* [Interceptor](https://square.github.io/okhttp/interceptors/) 
* [Room Database](https://developer.android.com/training/data-storage/room) 
* [Coroutines](https://developer.android.com/kotlin/coroutines) 
* [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow) 
* [Flows](https://developer.android.com/kotlin/flow) 
* [Model-View-ViewModel (MVVM)](https://developer.android.com/topic/libraries/architecture/viewmodel) 
* [SharedPreferences](https://developer.android.com/training/data-storage/shared-preferences) 
* [Dependency Injection with Hilt](https://developer.android.com/training/dependency-injection/hilt-android) 
* [Clean Architecture](https://www.raywenderlich.com/3595916-clean-architecture-tutorial-for-android-getting-started) 

For a complete list of dependencies, please refer to the `build.gradle` files.




