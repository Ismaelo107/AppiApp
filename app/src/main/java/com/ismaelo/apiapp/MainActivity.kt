package com.ismaelo.apiapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.ismaelo.apiapp.data.local.LocalDatasource
import com.ismaelo.apiapp.navigation.AppNavigation
import com.ismaelo.apiapp.navigation.NavigationDrawer.DrawerContent
import com.ismaelo.apiapp.navigation.navigationBar.TopNavigationBar
import com.ismaelo.apiapp.ui.theme.ApiAppTheme
import com.ismaelo.apiapp.ui.view.component.AnimatedGradientBackground
import com.ismaelo.apiapp.viewModel.MovieViewModel
import com.ismaelo.apiapp.viewModel.MovieViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var movieViewModel: MovieViewModel

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val localDatasource = LocalDatasource(applicationContext)
        val factory = MovieViewModelFactory(localDatasource)
        movieViewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

        setContent {
            val navController = rememberNavController()
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()

            ApiAppTheme {

                ModalNavigationDrawer(drawerState = drawerState,
                    drawerContent = { DrawerContent(navController) }) {
                    Scaffold(topBar = {
                        TopNavigationBar(
                            onMenuClick = { scope.launch { drawerState.open() } },
                        )
                    }) { innerPadding ->
                        AnimatedGradientBackground()
                        Column(
                            modifier = Modifier
                                .padding(innerPadding)
                                .background(Color.Black)

                        ) {
                            AppNavigation(movieViewModel, navController = navController)
                        }
                    }
                }
            }
        }
    }
}
