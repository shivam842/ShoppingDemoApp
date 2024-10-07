package com.sagar.shoppingdemoapp.presentation.view.composibles

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sagar.shoppingdemoapp.data.model.Product

@Composable
fun PLPItem(product: Product,onClick:(Int)->Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .wrapContentHeight().clickable { onClick(product.id) },
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(modifier = Modifier.padding(5.dp)) {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = "",
                modifier = Modifier.size(120.dp),
                contentScale = ContentScale.Fit,
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
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
            }
        }
    }
}

@Composable
private fun Preview() {
    val model = Product(
        234,
        "Motorolo mobile",
        324.34,
        4.5F,"",
        "https://images.unsplash.com/photo-1513151233558-d860c5398176?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&q=80&w=400"
    )
    PLPItem(product = model,{})
}