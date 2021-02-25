/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.puppydetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material.rememberScaffoldState
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
            FloatingActionButton(
                onClick = {
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(adoptedMessage)
                    }
                }
            ) {
                Icon(Icons.Filled.VolunteerActivism, contentDescription = stringResource(id = R.string.puppy_detail_adopt_button))
            }
        },
    ) { paddingValues ->
        PuppyDetail(paddingValues, puppy)
    }
}

@Composable
fun PuppyDetailTopBar(navController: NavController, puppyName: String) {
    TopAppBar(
        title = { Text(text = puppyName) },
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(Icons.Filled.ArrowBack, contentDescription = stringResource(id = R.string.puppy_detail_back_description))
            }
        }
    )
}

@Composable
fun PuppyDetail(paddingValues: PaddingValues, puppy: Puppy) {
    Image(
        modifier = Modifier.fillMaxSize().padding(paddingValues),
        painter = painterResource(id = puppy.picture),
        contentDescription = stringResource(id = R.string.puppy_detail_picture)
    )
}
