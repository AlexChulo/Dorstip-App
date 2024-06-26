package com.example.dorstip_app.dashboard


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.dorstip_app.dashboard.BannerSlider.SliderAdapter
import com.example.dorstip_app.dashboard.BannerSlider.SliderModel
import com.example.dorstip_app.dashboard.Categories.CategoryAdapter
import com.example.dorstip_app.dashboard.Products.ItemModel
import com.example.dorstip_app.dashboard.Products.ProductAdapter
import com.example.dorstip_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel = MainViewModel() // Create an instance of MainViewModel
    private lateinit var binding: ActivityMainBinding // Initialize ActivityMainBinding
    private lateinit var adapter: ProductAdapter // Assuming you have a RecyclerView adapter for products
    private lateinit var products: List<ItemModel> // Assuming you have a list of products
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBanner() // Initialize the banner
        initCategory() // Initialize the category
        initProduct() // Initialize the product items
    }

    // Function to initialize the banner
    private fun initBanner() {
        viewModel.banners.observe(this, Observer { items ->
            banners(items)
        })
        viewModel.loadBanners()
    }

    // Function to set up the banner with images
    private fun banners(images: List<SliderModel>) {
        // Set up ViewPager2 for the banner
        binding.vpBanner.adapter = SliderAdapter(images, binding.vpBanner)
        binding.vpBanner.clipToPadding = false
        binding.vpBanner.clipChildren = false
        binding.vpBanner.offscreenPageLimit = 3
        binding.vpBanner.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        // Apply a composite page transformer with margin to the ViewPager2
        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }
        binding.vpBanner.setPageTransformer(compositePageTransformer)

        // If there are more than one image, show the indicator
        if (images.size > 1) {
            binding.diBanner.visibility = View.VISIBLE
            binding.diBanner.attachTo(binding.vpBanner)
        }
    }

    // Function to initialize the category
    private fun initCategory() {
        viewModel.categories.observe(this, Observer {
            // Set up RecyclerView for the category
            binding.rvCategory.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            binding.rvCategory.adapter = CategoryAdapter(it)
        })
        viewModel.loadCategory() // Load categories from ViewModel
    }

    // Function to initialize the product items
    private fun initProduct() {
        viewModel.products.observe(this, Observer {
            // Set up RecyclerView for recommended items
            binding.rvDrinks.layoutManager = GridLayoutManager(this@MainActivity, 2)
            binding.rvDrinks.adapter = ProductAdapter(it)
        })
        viewModel.loadRecommended() // Load recommended items from ViewModel
    }
}