# 📍 Mini Navigation App

A modern Android application built with **Jetpack Compose** that simulates a real-time navigation experience. This app demonstrates high-level architecture, type-safe navigation, and reactive state management.

---

## 🏗 Architecture Overview

The project is built using **Clean Architecture** principles and the **MVVM (Model-View-ViewModel)** pattern. This separation ensures that business logic remains independent of the UI.

### 1. Presentation Layer (UI)
- **Jetpack Compose:** 100% declarative UI.
- **State Management:** Uses `StateFlow` and `collectAsStateWithLifecycle` to ensure the UI is lifecycle-aware and efficient.
- **Sealed Interfaces:** UI States (Idle, Loading, Success, Error) are modeled as sealed interfaces for exhaustive and type-safe UI rendering.
- **Reliability:** By using SavedStateHandle, the app is "crash-proof" against memory pressure.

### 2. Domain Layer (Business Logic)
- **Use Cases:** Encapsulates specific tasks like `SimulateNavigationUseCase` and `GetPlaceDetailsUseCase`.
- **Models:** Pure Kotlin data classes representing entities like `Place`.
- **Location Provider:** A single source of truth for the user's starting point, facilitating consistency across screens.
- **Decoupling:** Business logic is independent of data sources and UI frameworks.
- **Main safety:** The distance calculation is are offloaded to `Dispatchers.Default` using the `flowOn` operator, ensuring the Main thread remains unblocked for UI rendering.

### 3. Data Layer
- **Repository Pattern:** Acts as an abstraction layer over data sources. Currently, its a Mock Provider, but the architecture allows for a seamless transition to a Remote API or Room Database without affecting the Domain or UI.
- **Dependency Injection (Hilt):** Hilt is used to manage the dependency graph, promoting the **Dependency Inversion Principle**. This facilitates easy swapping of implementations for testing (e.g., providing a fake location provider during unit tests).
---

## 🚀 Getting Started

### Prerequisites
* **Android Studio:** Ladybug (2024.2.1) or newer.
* **JDK:** 17 or higher.
* **Minimum SDK:** API 24 (Android 7.0).

### Installation & Run
1. Clone this repository to your local machine.
2. Open the project in Android Studio.
3. Allow Gradle to sync and download dependencies.
4. Run the app on an Emulator or Physical Device.

---

## 📝 Key Assumptions & Simplifications

* **Real-Time Simulation:** Utilizes **Kotlin Flow** to stream navigation updates (Distance and ETA) every second, simulating a constant velocity of **20 m/s**.
* **Type-Safe Navigation:** Implements the latest **Jetpack Compose Navigation** with type-safe arguments to ensure robust transitions between the Place List, Details, and Simulation screens.
* **Stack Management:** Implements strategic backstack clearing (e.g., popping the simulation screen upon arrival) to ensure a clean navigation history and prevent "ghost" states.

---

## 🛠 Tech Stack

* **Language:** Kotlin (Coroutines, Flow)
* **UI Framework:** Jetpack Compose (Material 3)
* **DI Framework:** Hilt (Dagger 2.5x)
* **Architecture:** Clean Architecture + MVVM
* **Navigation:** Jetpack Compose Navigation (Type-Safe)
* **Persistence:** SavedStateHandle (Process Death Resilience)
* **Testing:** JUnit 4, MockK, Turbine (for Flow testing)

---

## 📂 Project Structure

```text
com.example.mininavigationapp
├── di              # Hilt Modules (Dependency Injection setup)
├── domain          # Business Logic (UseCases, Domain Models, Contracts)
├── data            # Infrastructure (Repository implementations, Mock data)
├── presentation    # UI (Composables, ViewModels, UI State models)
└── utils           # Formatters and Helper logic