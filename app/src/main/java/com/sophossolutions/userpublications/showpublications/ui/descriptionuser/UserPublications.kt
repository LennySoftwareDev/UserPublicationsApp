package com.sophossolutions.userpublications.showpublications.ui.descriptionuser

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sophossolutions.userpublications.showpublications.ui.listusers.ViewModelUser

@Composable
fun UserPublications(
    viewModelUserPublications: ViewModelUserPublications,
    viewModelUser: ViewModelUser,
    userId: String
) {

    viewModelUserPublications.getUserById(userId.toInt())

    viewModelUserPublications.onShowUserPublications()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = "User Publications",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        HorizontalDivider(thickness = 4.dp)
        UserPublications(viewModelUser, viewModelUserPublications)
    }
}

@Composable
fun InformationUser(
    viewModelUser: ViewModelUser,
    viewModelUserPublications: ViewModelUserPublications
) {

    val idUser by viewModelUserPublications.idUser.collectAsState()

    val dataUser by viewModelUser.dataUserLocal.observeAsState()

    //TODO: validar que el usuario exista y que tenga publicaciones
    val user = dataUser!!.result!!.find { it.id == idUser }

    user?.let {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row {
                Text(text = "Name: ", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = it.name)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Text(text = "Phone: ", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = it.phone ?: "N.A")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Text(text = "Email: ", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = it.email ?: "N.A")
            }
        }
    } ?: run {
        Text(text = "User not found")
    }
}

@Composable
fun UserPublications(
    viewModelUser: ViewModelUser,
    viewModelUserPublications: ViewModelUserPublications
) {
    val isLoading by viewModelUserPublications.isLoadingUserInformation.collectAsState()

    val userPublication by viewModelUserPublications.mapUser.collectAsState()

    if (isLoading) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Loading...",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
            )
            CircularProgressIndicator(
                modifier = Modifier.size(48.dp)
            )
        }

    } else {
        InformationUser(viewModelUser, viewModelUserPublications)
        LazyColumn {
            items(userPublication.toList()) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(16.dp),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Text(
                        text = it.first,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(16.dp)
                    )
                    Text(
                        text = it.second,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}