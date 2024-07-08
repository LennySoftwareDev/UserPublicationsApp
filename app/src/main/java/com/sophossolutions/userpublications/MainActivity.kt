package com.sophossolutions.userpublications

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sophossolutions.userpublications.showpublications.ui.descriptionuser.UserPublications
import com.sophossolutions.userpublications.showpublications.ui.descriptionuser.ViewModelUserPublications
import com.sophossolutions.userpublications.showpublications.ui.listusers.UsersScreen
import com.sophossolutions.userpublications.showpublications.ui.listusers.ViewModelUser
import com.sophossolutions.userpublications.showpublications.ui.navigation.AppRoutesScreen
import com.sophossolutions.userpublications.ui.theme.UserPublicationsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModelUser: ViewModelUser by viewModels()
    private val viewModelUserPublications: ViewModelUserPublications by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UserPublicationsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    //color = MaterialTheme.colors.background
                ) {
                    val navigationController = rememberNavController()
                    NavHost(
                        navController = navigationController,
                        startDestination = AppRoutesScreen.ScreenUserList.route
                    ){
                        composable(AppRoutesScreen.ScreenUserList.route){
                            UsersScreen(
                                viewModelUser = viewModelUser,
                                navController = navigationController
                            )
                        }
                        composable(AppRoutesScreen.ScreenUserPublications.route.plus("/{userId}")){
                            UserPublications(
                                viewModelUserPublications,
                                viewModelUser,
                                it.arguments?.getString("userId").orEmpty()
                            )
                        }
                    }
                }
            }
        }
    }
}

