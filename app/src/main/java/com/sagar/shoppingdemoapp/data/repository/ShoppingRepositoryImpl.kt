package com.example.todolist.data.repository

import android.content.Context
import com.example.todolist.data.db.ShoppingDao
import com.example.todolist.domain.repository.ShoppingRepository
import com.sagar.shoppingdemoapp.data.model.Cart
import com.sagar.shoppingdemoapp.data.model.Product
import com.sagar.shoppingdemoapp.utils.parseJsonToModel
import com.sagar.shoppingdemoapp.utils.readJsonFromAssets
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

public class ShoppingRepositoryImpl @Inject constructor(
    private val dao: ShoppingDao,
    private val context: Context
) :
    ShoppingRepository {
    override suspend fun insert(product: Product) {
        dao.insert(product)
    }

    override suspend fun getAllItems(): Flow<List<Product>> {
        //return dao.getAllProducts()
        delay(3000)
        val jsonString = readJsonFromAssets(context, "products.json")
        val products: List<Product> = parseJsonToModel(jsonString)
        return flow { emit(products) }
    }

    override suspend fun addToCard(item: Product) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCardItem(itemId: Cart) {
        dao.deleteCardItem(itemId)
    }

    override suspend fun updateCart(item: Cart) {
        dao.updateCart(item)
    }

    override suspend fun insertInCart(item: Product) {
        delay(2000)
        val cartItem: Cart = Cart(productId = item.id, quantity = 1)
        try {
            dao.insertInCart(cartItem)
        }catch (e:Exception){
            val updatedCartItem = dao.getCartByProduct(item.id).apply { quantity++ }
            dao.updateCart(updatedCartItem)
        }
    }


    override suspend fun getAllProducts(): Flow<List<Product>> {
        return dao.getAllProducts()
    }

    override suspend fun getAllCartProducts(): Flow<List<Cart>> {
        delay(1000)
        var cartList: List<Cart> = emptyList()
        cartList = dao.getAllCartProductsList().also {// 2nd api call to get product
            it.forEach { eachProduct ->
                eachProduct.apply {
                    var product1 = getProductModel(eachProduct.productId)
                    product = product1
                }
            }
        }
        return flow {
            emit(cartList)
        }
    }

    override suspend fun getProduct(_id: Int): Flow<Product?> {
        val jsonString = readJsonFromAssets(context, "products.json")
        val products: List<Product> = parseJsonToModel(jsonString)
        return flow {
            emit(products.filter { it.id == _id }.firstOrNull())
        }
    }

    override suspend fun getProductModel(_id: Int): Product? {
        val jsonString = readJsonFromAssets(context, "products.json")
        val products: List<Product> = parseJsonToModel(jsonString)
        return products.filter { it.id == _id }.firstOrNull()
    }

    override suspend fun getCartByProduct(id: Int): Cart? {
        return dao.getCartByProduct(id)
    }
}
