package com.project.simplecoffee.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.project.simplecoffee.common.Resource
import com.project.simplecoffee.constant.ErrorConst
import com.project.simplecoffee.domain.models.Drink
import com.project.simplecoffee.domain.models.DrinkCategory
import com.project.simplecoffee.domain.repository.IDrinkCategoryRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.NullPointerException
import javax.inject.Inject

class DrinkCategoryRepo @Inject constructor(
    db: FirebaseFirestore
) : IDrinkCategoryRepo {
    private val collection = db.collection("drink_category")
    private var listDrinkCategory: MutableList<DrinkCategory>? = null

    override suspend fun getDrinkCategory(): Resource<List<DrinkCategory>?> =
        withContext(Dispatchers.IO) {
            try {
                if (listDrinkCategory == null) {
                    listDrinkCategory = mutableListOf()
                    val listDocuments = collection.get().await()
                    for (document in listDocuments) {
                        listDrinkCategory?.add(
                            document.toObject(DrinkCategory::class.java).withId(document.id)
                        )
                    }
                }
                Resource.OnSuccess(listDrinkCategory)
            } catch (e: Exception) {
                Resource.OnFailure(null, ErrorConst.ERROR_UNEXPECTED)
            }
        }

    override suspend fun getDrinkCategory(id: String): Resource<DrinkCategory?> =
        withContext(Dispatchers.IO) {
            try {
                val matchDrinkCategory = listDrinkCategory?.filter { id == it.id }
                if (matchDrinkCategory != null && matchDrinkCategory.isNotEmpty()) {
                    Resource.OnSuccess(matchDrinkCategory.first())
                } else {
                    val document = collection.document(id).get().await()
                    val drinkCategory = document.toObject(DrinkCategory::class.java)
                        ?.withId<DrinkCategory>(document.id) ?: throw NullPointerException()
                    Resource.OnSuccess(drinkCategory)
                }
            } catch (e: Exception) {
                Resource.OnFailure(null, ErrorConst.ERROR_UNEXPECTED)
            }
        }
}