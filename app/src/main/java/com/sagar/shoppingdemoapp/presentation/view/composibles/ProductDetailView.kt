package com.sagar.shoppingdemoapp.presentation.view.composibles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sagar.shoppingdemoapp.data.model.Product


@Composable
fun ProductDetailView(product: Product, addProductToCart: () -> Unit, navigateToCart: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .background(Color.LightGray) // Set your desired background color here
        ) {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                contentScale = ContentScale.Crop,
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = product.name,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = "Prize: ${product.price.toString()}",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
        )
        Text(
            text = "Ratting: ${product.rating.toString()}",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
        )
        Spacer(modifier = Modifier.height(20.dp))

        Row {
            Button(modifier = Modifier, onClick = {
                addProductToCart()
            }) {
                Text(
                    text = "Add To Card",
                    modifier = Modifier
                        .wrapContentHeight(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            Spacer(modifier = Modifier.width(20.dp))

            Button(modifier = Modifier, onClick = {
                navigateToCart()
            }) {
                Text(
                    text = "View Cart",
                    modifier = Modifier
                        .wrapContentHeight(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
        product.specification?.let {
            Text(
                text = "Ratting: ${product.specification}",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
            )
        }
    }
}
