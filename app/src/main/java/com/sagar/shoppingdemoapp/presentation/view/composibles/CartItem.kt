package com.sagar.shoppingdemoapp.presentation.view.composibles

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sagar.shoppingdemoapp.R
import com.sagar.shoppingdemoapp.data.model.Cart


@Composable
fun CartItem(
    model: Cart,
    onAddQuantity: (cart: Cart) -> Unit,
    onRemoveQuantity: (cart: Cart) -> Unit
) {
    model.product?.let { product ->
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .wrapContentHeight()
                .clickable { },
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(modifier = Modifier.padding(5.dp)) {
                Column {
                    AsyncImage(
                        model = product.imageUrl,
                        contentDescription = "",
                        modifier = Modifier.size(120.dp),
                        contentScale = ContentScale.Fit,
                    )
                    Row(
                        modifier = Modifier.wrapContentSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.clickable { onRemoveQuantity(model) },
                            painter = painterResource(id = R.drawable.baseline_indeterminate_check_box_24),
                            contentDescription = ""
                        )
                        Text(
                            text = model.quantity.toString(),
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(start = 5.dp, end = 5.dp)
                        )
                        Image(
                            modifier = Modifier.clickable { onAddQuantity(model) },
                            painter = painterResource(id = R.drawable.baseline_add_box_24),
                            contentDescription = ""
                        )

                    }

                }
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
                        text = "Prize: ${product.price}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                    )
                    Text(
                        text = "Total: ${(model.quantity * product.price).toInt()}",
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
}