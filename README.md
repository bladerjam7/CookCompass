# Cook Compass 🍳

⚠️ Status: Not completed

Cook Compass is a clean, modern Android recipe app that helps users discover, save, and manage their favorite recipes in one place. The app focuses on a simple, intuitive UI and a delightful cooking experience.

## ✨ Features

* 📋 **My Recipes** – View all saved recipes in a beautiful card-based layout
* ➕ **Add Recipe** – Create and store your own custom recipes
* 🖼️ **Recipe Images** – Each recipe includes a featured image
* ⏱️ **Cook Time** – Quick visibility into how long a recipe takes
* 👥 **Servings** – See how many people the recipe serves
* 🎯 **Difficulty Levels** – Easy, Medium, or Hard
* 🏷️ **Tags & Categories** – Organize recipes (e.g., Pasta, Italian, Breakfast)
* ⋮ **Recipe Actions** – Edit or delete recipes via overflow menu

## 📱 Screenshots

| My Recipes Screen                         |
| ----------------------------------------- |
<img width="290" height="700" alt="image" src="https://github.com/user-attachments/assets/d93f3382-976d-4295-bdef-b6b4dfbfe724" />
<img width="290" height="700" alt="image" src="https://github.com/user-attachments/assets/1131260c-027e-4fac-a271-c325e61ce64e" /> 
<img width="290" height="700" alt="image" src="https://github.com/user-attachments/assets/9d978924-f5eb-4d3e-9158-f604c3cafb60" /> 

## 🏗️ Tech Stack

* **Language:** Kotlin
* **UI:** Jetpack Compose
* **Architecture:** MVVM (Model–View–ViewModel)
* **State Management:** State / Flow
* **Image Loading:** Coil
* **Navigation:** Jetpack Navigation Compose
* **Dependency Injection:** Hilt (optional / if used)
* **Persistence:** Room / DataStore (optional / if used)

## 🧱 Architecture

Cook Compass follows **MVVM best practices**:

* **Model:** Recipe data models and repository layer
* **View:** Jetpack Compose UI
* **ViewModel:** Holds UI state and business logic

This separation ensures:

* Testability
* Scalability
* Clear separation of concerns

## 📂 Project Structure

```
app/
├── model/
├── ui/
│   ├── screens/
│   ├── components/
│   └── theme/
└── MainActivity.kt
```

## 🚀 Getting Started

### Prerequisites

* Android Studio Hedgehog or newer
* Android SDK 24+

### Installation

1. Clone the repository:

   ```bash
   https://github.com/bladerjam7/CookCompass.git
   ```
2. Open the project in **Android Studio**
3. Sync Gradle
4. Run on an emulator or physical device

## 🎨 Design Philosophy

* Minimal, distraction-free UI
* Large recipe imagery
* Clear typography and spacing
* Touch-friendly actions

Inspired by modern Material Design and cooking apps.

## 🛣️ Roadmap

* 🔍 Recipe search & filtering
* ☁️ Cloud sync
* ❤️ Favorites
* 📦 Ingredient-based recommendations
* 🤖 AI-assisted recipe generation

Made with ❤️ using Kotlin & Jetpack Compose
