package com.sophossolutions.userpublications.showpublications.ui.navigation

sealed class AppRoutesScreen(val route: String) {

    data object ScreenUserList : AppRoutesScreen("UsersScreen")
    data object ScreenUserPublications : AppRoutesScreen("UserPublications")
}