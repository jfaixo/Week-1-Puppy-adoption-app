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
package com.example.androiddevchallenge.ui.puppylist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.models.Puppy
import com.example.androiddevchallenge.repositories.PuppyRepository
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun PuppyListScreen(navController: NavController) {
    val puppies = remember { PuppyRepository().getAll() }

    Scaffold(
        topBar = { PuppyListTopBar() }
    ) { paddingValues ->
        PuppyList(paddingValues, puppies) { id -> navController.navigate("puppies/$id") }
    }
}

@Composable
fun PuppyListTopBar() {
    TopAppBar(title = { Text(text = stringResource(id = R.string.puppy_list_title)) })
}

@Composable
fun PuppyList(paddingValues: PaddingValues, puppies: List<Puppy>, navigateToDetail: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(paddingValues)
    ) {
        for (i in 0..puppies.size step 3) {
            SimpleRow(puppies, i, navigateToDetail)
        }
    }
}

@Composable
fun SimpleRow(puppies: List<Puppy>, startIndex: Int, navigateToDetail: (Int) -> Unit) {
    Row(modifier = Modifier.height(200.dp)) {
        for (i in startIndex..(startIndex + 2).coerceAtMost(puppies.size - 1)) {
            Box(Modifier.weight(1f)) {
                PuppyItem(puppy = puppies[i], navigateToDetail)
            }
        }
        if (startIndex + 2 > puppies.size - 1) {
            for (i in puppies.size..(startIndex + 2)) {
                Box(Modifier.weight(1f)) {
                }
            }
        }
    }
}

@Composable
fun PuppyItem(puppy: Puppy, navigateToDetail: (Int) -> Unit) {

    Card(
        modifier = Modifier
            .clickable(onClick = { navigateToDetail(puppy.id) })
            .padding(10.dp)
            .fillMaxSize(),
        elevation = 5.dp
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = puppy.picture),
                contentDescription = puppy.name,
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(Color.White.copy(alpha = 0.9f))
            ) {
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = puppy.name,
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        PuppyListScreen(rememberNavController())
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        PuppyListScreen(rememberNavController())
    }
}
