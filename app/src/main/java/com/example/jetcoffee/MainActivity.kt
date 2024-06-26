package com.example.jetcoffee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetcoffee.model.Menu
import com.example.jetcoffee.model.dummyBestSellerMenu
import com.example.jetcoffee.model.dummyCategory
import com.example.jetcoffee.model.dummyMenu
import com.example.jetcoffee.ui.components.CategoryItem
import com.example.jetcoffee.ui.components.HomeSection
import com.example.jetcoffee.ui.components.MenuItem
import com.example.jetcoffee.ui.components.Search
import com.example.jetcoffee.ui.components.SectionText
import com.example.jetcoffee.ui.theme.JetCoffeeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetCoffeeTheme {
                JetCoffeeTheme {
                    JetCoffeeApp()
                }
            }
        }
    }
}

@Composable
fun JetCoffeeApp() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        Banner()
        HomeSection(
            title = stringResource(id = R.string.section_category),
            content = { CategoryRow() }
        )
        HomeSection(
            title = stringResource(R.string.section_favorite_menu),
            content = { MenuRow(dummyMenu) }
        )
        HomeSection(
            title = stringResource(R.string.section_best_seller_menu),
            content = { MenuRow(dummyBestSellerMenu) }
        )
    }
}

@Composable
fun Banner(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(painter = painterResource(R.drawable.banner),
            contentDescription = "Banner Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(160.dp)
        )
        Search()
    }
}

@Composable
fun CategoryRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(dummyCategory, key = {it.textCategory}) { category ->
            CategoryItem(category)
        }
    }
    
}

@Composable
fun MenuRow(
    listMenu: List<Menu>,
    modifier: Modifier = Modifier
) {
   LazyRow(
       horizontalArrangement = Arrangement.spacedBy(16.dp),
       contentPadding = PaddingValues(horizontal = 16.dp),
       modifier = modifier
   ) {
       items(listMenu, key = {it.title}) { menu ->
           MenuItem(menu = menu)
       }
   }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun JetCoffeeAppPreview() {
    JetCoffeeTheme {
       JetCoffeeApp()
    }
}