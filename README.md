# Product Explorer

A simple Android application that allows users to browse, search, and view product details using data fetched from the DummyJSON API.

## Features

- Browse products in a scrollable list
- Search products by name or category
- View product images, price, category, description, and rating
- Product details screen
- Back navigation
- Loading state
- Error state
- Empty search state
- Material 3 UI

## Tech Stack

- Kotlin
- Jetpack Compose
- Material 3
- Retrofit
- Gson
- Kotlin Coroutines
- ViewModel
- Repository Pattern
- Navigation Compose
- Coil

## Architecture

The application follows a simple layered architecture:

UI → ViewModel → Repository → Retrofit API → DummyJSON

## API

The application uses the DummyJSON Products API to fetch product data.

## Project Structure

```text
com.example.productexplorer
│
├── data
│   ├── Product.kt
│   ├── ProductApi.kt
│   ├── ProductRepository.kt
│   └── ProductResponse.kt
│
├── ui
│   ├── HomeScreen.kt
│   ├── DetailsScreen.kt
│   ├── ProductViewModel.kt
│   └── theme
│
└── MainActivity.kt
