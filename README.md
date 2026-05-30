# SquareApp - Multi-Module Android App

A production-ready **multi-module Android application** built with **Jetpack Compose**, **Clean Architecture**, **MVVM**, **Hilt DI**, and **Kotlin**. Features modern networking, image loading, and centralized dependency management via Gradle Version Catalogs.

[square_repo_003.webm](https://github.com/user-attachments/assets/404b4ec8-e54f-41ad-b740-98a2617c001f)

<img width="290" height="620" alt="Square_error_ss" src="https://github.com/user-attachments/assets/6afe8358-aae4-4b68-b31e-1a43dcce9d96" />


## 🏗️ Project Structure

com.gd.squareapp # Application/
├── app/ # Presentation Layer (Compose UI + ViewModels)
├── data/ # Data Layer (Repository impl + API)
├── domain/ # Domain Layer (UseCases + Models + Gateways)
├── gateway/ # Gateway Interfaces (API contracts)
├── gradle/libs.versions.toml # Centralized versions + plugins
└── build.gradle.kts (project) # Plugin declarations

## ✨ Key Features

- **Clean Architecture** (Domain → Data → Presentation)
- **MVVM + StateFlow** (Reactive UI state management)
- **Hilt Dependency Injection** (Full project coverage)
- **Jetpack Compose** (Material3 + Navigation + Icons)
- **Retrofit + Kotlin Serialization** (Type-safe networking)
- **Coroutines ** (Async operations)
- **KSP** (Fast annotation processing)
- **Unit tests covered** ViewMode, Domain, Gateway and repository unit tests covered

## 📦 Technology Stack

| Layer | Technologies                                         |
|-------|------------------------------------------------------|
| **UI** | Jetpack Compose, Material3, Coil 2.5.0               |
| **Architecture** | Clean Arch, MVVM, Hilt 2.51.1, Lifecycle 2.8.6       |
| **Networking** | Retrofit 2.9.0, OkHttp 4.12.0                        |
| **Async** | Kotlin Coroutines 1.8.0, Kotlin 2.0.0                |
| **Build** | AGP 8.7.2, KSP 2.0.0-1.0.23, Gradle Version Catalogs |


## 🛠️ Module Responsibilities

### 1. `:app` (Presentation)
* Jetpack Compose UI
* ViewModels + UIState
* Hilt Navigation Compose
* Material3 Theming

### 2. `:data` (Data Layer)
* Repository implementations
* Retrofit API services
* Remote data sources

### 3. `:domain` (Domain Layer)
* Pure business logic
* UseCases
* Domain Models
* Gateway interfaces

**Plugins**: `android-library`, `kotlin-android`

### 4. `:gateway` (Contracts)
* API interface definitions
* Data source abstractions
* Shared between Data/Domain

## Author

**Ghanshyam Dongare**
---
*Built with ❤️ using Kotlin 2.0 + Compose 2026*
