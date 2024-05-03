package com.example.dorstip_app.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dorstip_app.dashboard.BannerSlider.SliderModel
import com.example.dorstip_app.dashboard.Categories.CategoryModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.sql.Ref

class MainViewModel():ViewModel() {

    private val firebaseDatabase=FirebaseDatabase.getInstance()

    private val category=MutableLiveData<MutableList<CategoryModel>>()
    private val banner=MutableLiveData<List<SliderModel>>()

    val categories: LiveData<MutableList<CategoryModel>> = category
    val banners: LiveData<List<SliderModel>> = banner

    fun loadBanners(){
        val Ref=firebaseDatabase.getReference("Banner")
        Ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<SliderModel>()
                for (childSnapshot in snapshot.children){
                    val list=childSnapshot.getValue(SliderModel::class.java)
                    if (list!=null){
                        lists.add(list)
                    }
                }
                banner.value =lists
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
    fun loadCategory(){
        val Ref=firebaseDatabase.getReference("Category")
        Ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<CategoryModel>()
                for (childSnapshot in snapshot.children){
                    val list=childSnapshot.getValue(CategoryModel::class.java)
                    if (list !=null){
                        lists.add(list)
                    }
                }
                category.value=lists
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
    
}