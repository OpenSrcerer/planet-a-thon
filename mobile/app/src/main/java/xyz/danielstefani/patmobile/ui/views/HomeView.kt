package xyz.danielstefani.patmobile.ui.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import xyz.danielstefani.patmobile.dto.Cartoon
import xyz.danielstefani.patmobile.ui.theme.Purple40
import xyz.danielstefani.patmobile.ui.theme.PurpleGrey80
import xyz.danielstefani.patmobile.ui.viewmodels.CartoonViewModel
import xyz.danielstefani.patmobile.ui.viewmodels.HomeViewModel

@ExperimentalFoundationApi
@Composable
fun HomeView(
    controller: NavController,
    model: HomeViewModel = HomeViewModel.get()
) {
    ArtistList(controller, model)
}


@ExperimentalFoundationApi
@Composable
fun ArtistList(
    controller: NavController,
    model: HomeViewModel
) {
    LazyColumn {
        model.cartoons.value().entries.sortedBy { it.key }.forEach { entry ->
            stickyHeader {
                StickyHeaderStyle(name = entry.key.toString())
            }

            items(entry.value) { toon ->
                ListedCartoonStyle(controller, model, toon)
            }
        }
    }
}

@Composable
fun StickyHeaderStyle(
    name: String
) {
    Row(
        modifier = Modifier
            .background(Purple40)
            .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.headlineLarge,
            lineHeight = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ListedCartoonStyle(
    controller: NavController,
    model: HomeViewModel,
    toon: Cartoon
) {
    Row(
        modifier = Modifier
            .clickable {
                CartoonViewModel.get().setCartoon(toon)
                controller.navigate("cartoon")
            }
            .background(PurpleGrey80)
            .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
            .fillMaxWidth()
    ) {
        val toonName = toon.name ?: ""
        val cartoonName =
            if (toonName.length > 30)
                "${toonName.subSequence(0, 30)}..."
            else
                toonName
        Text(
            text = cartoonName,
            style = MaterialTheme.typography.headlineMedium,
            lineHeight = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}