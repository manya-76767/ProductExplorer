package com.example.productexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.productexplorer.data.ProductApi
import com.example.productexplorer.data.ProductRepository
import com.example.productexplorer.ui.HomeScreen
import com.example.productexplorer.ui.ProductViewModel
import com.example.productexplorer.ui.theme.ProductExplorerTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.productexplorer.ui.DetailsScreen

class MainActivity : ComponentActivity() {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dummyjson.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(ProductApi::class.java)
    private val repository = ProductRepository(api)

    private val viewModelFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ProductViewModel(repository) as T
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ProductExplorerTheme {

                val viewModel: ProductViewModel = viewModel(
                    factory = viewModelFactory
                )

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "home"
                ) {
                    composable("home") {
                        HomeScreen(
                            viewModel = viewModel,
                            onProductClick = { product ->
                                navController.navigate("details/${product.id}")
                            }
                        )
                    }

                    composable("details/{productId}") { backStackEntry ->
                        val productId = backStackEntry.arguments
                            ?.getString("productId")
                            ?.toIntOrNull()

                        val product = viewModel.products.value
                            .find { it.id == productId }

                        if (product != null) {
                            DetailsScreen(
                                product = product,
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}