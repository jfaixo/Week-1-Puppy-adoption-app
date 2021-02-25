package com.example.androiddevchallenge.ui.puppydetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.models.Puppy
import com.example.androiddevchallenge.repositories.PuppyRepository
import kotlinx.coroutines.launch

@Composable
fun PuppyDetailScreen(navController: NavController, puppyId: Int) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val puppy = remember { PuppyRepository().get(puppyId) }
    val adoptedMessage = stringResource(id = R.string.puppy_detail_adopted_message)

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { PuppyDetailTopBar(navController, puppy.name) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(adoptedMessage)
                }
            }) {
                Icon(Icons.Filled.VolunteerActivism, contentDescription = stringResource(id = R.string.puppy_detail_adopt_button))
            }
        },
    ) { paddingValues ->
        PuppyDetail(paddingValues, puppy)
    }
}

@Composable
fun PuppyDetailTopBar(navController: NavController, puppyName: String) {
    TopAppBar(title = { Text(text = puppyName) },
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(Icons.Filled.ArrowBack, contentDescription = stringResource(id = R.string.puppy_detail_back_description))
            }
        })
}

@Composable
fun PuppyDetail(paddingValues: PaddingValues, puppy: Puppy) {
    Image(modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = puppy.picture),
        contentDescription = stringResource(id = R.string.puppy_detail_picture))
}
