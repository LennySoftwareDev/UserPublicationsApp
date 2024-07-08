package com.sophossolutions.userpublications.showpublications.ui.listusers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sophossolutions.userpublications.showpublications.ui.navigation.AppRoutesScreen

@Composable
fun UsersScreen(viewModelUser: ViewModelUser, navController: NavController) {

    LaunchedEffect(viewModelUser.dataUserLocal.value == null) {
        viewModelUser.getPublications()
    }

    val isLoading: Boolean by viewModelUser.isLoadingUsers.observeAsState(
        initial = false
    )

    if (isLoading) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Loading User's List...",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
            )
            CircularProgressIndicator(
                modifier = Modifier.size(48.dp)
            )
        }

    }else{

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "User's List",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            HorizontalDivider(thickness = 4.dp)
            SearchBarUser(viewModelUser,navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarUser(viewModelUser: ViewModelUser, navController: NavController){

    val nameUser: String by viewModelUser.nameUser.observeAsState(initial = "")

    var active by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar(
            modifier = Modifier.wrapContentHeight(),
            query = nameUser,
            onQueryChange = { viewModelUser.onTextChanged(it) },
            onSearch = {
                       active = false
            },
            active = active,
            onActiveChange = { active = it },
            placeholder = { Text("search user") },

        ) {
            UsersFilter(viewModelUser,navController)
        }
    }
}

@Composable
fun UsersFilter(viewModelUser: ViewModelUser, navController: NavController) {

    LazyColumn {
        items(
            viewModelUser.onSearchUser(viewModelUser.nameUser.value.toString()).toList()
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp)
                    .clickable {
                        navController.navigate(AppRoutesScreen.ScreenUserPublications.route.plus("/${it.first}"))
                    },
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Text(
                    text = it.second,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}