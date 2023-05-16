# Planet-a-thon Mobile App
This project is a sample Jetpack Compose Android application written in Kotlin that connects to an API and exposes a presentation layer to the user.

## Project Structure
This Android application uses Jetpack Compose and follows the Model-View-ViewModel (MVVM) architectural pattern. The project includes several classes that are used to make HTTP requests to a server that provides cartoon data, as well as several composable views and view models that display that data.

The project contains the following folder structure:
- **client**: The HTTP client that gperforms the requests to the backend.
- **dto**: Contains the DTO classes (Data Transfer Objects) that are used to transfer data between the server and the client.
- **ui**: Contains all the UI related code (viewmodels, etc).
- **util**: Contains utility functions that assist in writing more readable code.
- **MainActivity.kt**: The main entry point for the Android application.

## Functionality
This Android Application aims to act as a presentation layer for the backend in this project. It currently calls one of the APIs exposed by the backend, and renders them in a classic sticky-header contact like list. When you click on a cartoon, the app takes you to a details page.

## Getting Started

To get started with the project, follow these steps:

0. Make sure the backend is running, or that you are using a remote deployment!
1. Clone the repository to your local machine.
2. Open the project in Android Studio.
3. Create an emulator, `Pixel 2, API 30, Android 11`
4. Build the project to download the required dependencies.
5. Run the Android app through the `MainActivity` class to launch it on the device.
