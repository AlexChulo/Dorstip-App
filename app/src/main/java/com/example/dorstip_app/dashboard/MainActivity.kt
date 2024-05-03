package com.example.dorstip_app.dashboard


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.dorstip_app.dashboard.BannerSlider.SliderAdapter
import com.example.dorstip_app.dashboard.BannerSlider.SliderModel
import com.example.dorstip_app.dashboard.Categories.CategoryAdapter
import com.example.dorstip_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel=MainViewModel()
    private lateinit var binding:ActivityMainBinding

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                binding= ActivityMainBinding.inflate(layoutInflater)
                setContentView(binding.root)

                initBanner()
                initCategory()
            }

            private fun initBanner() {
                viewModel.banners.observe(this, Observer {items->
                banners(items)
                })
                viewModel.loadBanners()
            }

            private fun banners(images:List<SliderModel>){
                binding.vpBanner.adapter= SliderAdapter(images,binding.vpBanner)
                binding.vpBanner.clipToPadding=false
                binding.vpBanner.clipChildren=false
                binding.vpBanner.offscreenPageLimit=3
                binding.vpBanner.getChildAt(0).overScrollMode=RecyclerView.OVER_SCROLL_NEVER

                val compositePageTransformer=CompositePageTransformer().apply {
                    addTransformer(MarginPageTransformer(40))
                }
                binding.vpBanner.setPageTransformer(compositePageTransformer)
                if (images.size>1){
                    binding.diBanner.visibility= View.VISIBLE
                    binding.diBanner.attachTo(binding.vpBanner)
                }
            }

            private fun initCategory(){
                viewModel.categories.observe(this, Observer {
                    binding.rvCategory.layoutManager=LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
                    binding.rvCategory.adapter=CategoryAdapter(it)
                })
                viewModel.loadCategory()
            }
        }






