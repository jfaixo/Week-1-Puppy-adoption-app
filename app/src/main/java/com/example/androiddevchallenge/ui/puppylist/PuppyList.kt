package com.example.androiddevchallenge.ui.puppylist

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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
    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .padding(paddingValues)) {
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

    Card(modifier = Modifier
        .clickable(onClick = { navigateToDetail(puppy.id) })
        .padding(10.dp)
        .fillMaxSize(),
    elevation = 5.dp) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = puppy.picture),
                contentDescription = puppy.name,
                contentScale = ContentScale.Crop)
            Box(modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.White.copy(alpha = 0.9f))) {
                Text(modifier = Modifier.padding(5.dp),
                    text = puppy.name,
                    style = MaterialTheme.typography.h6)
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