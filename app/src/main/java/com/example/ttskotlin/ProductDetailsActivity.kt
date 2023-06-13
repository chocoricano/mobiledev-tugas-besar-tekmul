package com.example.ttskotlin

import android.content.Intent
import com.android.volley.toolbox.Volley
import com.android.volley.toolbox.StringRequest
import com.android.volley.Request
import android.widget.LinearLayout
import org.json.JSONArray
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject
import android.view.View
import android.widget.TextView
import android.graphics.Color
import android.util.TypedValue
import com.bumptech.glide.Glide
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import java.util.*
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import android.net.Uri

class ProductDetailsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PRODUCT_NAME = "productName"
        const val EXTRA_PRODUCT_IMAGE_URL = "productThumbnail"
        const val EXTRA_PRODUCT_PRICE = "productPrice"
        const val EXTRA_PRODUCT_RATING = "productRating"
        const val EXTRA_PRODUCT_SOLD = "productSold"
        const val EXTRA_PRODUCT_LINK = "productLink"
    }

    private lateinit var productNameTextView: TextView
    private lateinit var productImageView: ImageView
    private lateinit var productPriceTextView: TextView
    private lateinit var productRatingTextView: TextView
    private lateinit var DescTextView: TextView
    private lateinit var LinkTextView: TextView

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        // Set the toolbar as the action bar for the activity
        val toolbar = findViewById<Toolbar>(R.id.toolbar2)
        toolbar.title = ""

        setSupportActionBar(toolbar)

        // Add a back button to the toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Get product details from intent extras
        val productName = intent.getStringExtra(EXTRA_PRODUCT_NAME)
        val productImageUrl = intent.getStringExtra(EXTRA_PRODUCT_IMAGE_URL)
        val productPrice = intent.getStringExtra(EXTRA_PRODUCT_PRICE)
        val productRating = intent.getStringExtra(EXTRA_PRODUCT_RATING)
        val productSold = intent.getStringExtra(EXTRA_PRODUCT_SOLD)
        val productDesc = intent.getStringExtra(EXTRA_PRODUCT_LINK)
        val productLink = intent.getStringExtra(EXTRA_PRODUCT_LINK)

        // Initialize UI elements
        productNameTextView = findViewById(R.id.productNameTextView)
        productImageView = findViewById(R.id.productImageView)
        productPriceTextView = findViewById(R.id.productPriceTextView)
        productRatingTextView = findViewById(R.id.productRatingTextView)
        DescTextView = findViewById(R.id.DescTextView)
        LinkTextView = findViewById(R.id.LinkTextView)

        // Set product name to text view
        productNameTextView.text = productName
        productPriceTextView.text = productPrice
        productRatingTextView.text = "★ " + productRating + " | " + productSold
        DescTextView.text = productDesc

        // Load product image using Glide
        Glide.with(this)
            .load(productImageUrl)
            .placeholder(R.drawable.product_placeholder)
            .error(R.drawable.product_placeholder)
            .into(productImageView)

        // Add OnClickListener to the DescTextView
        DescTextView.setOnClickListener {
            val openLinkIntent = Intent(Intent.ACTION_VIEW, Uri.parse(productLink))
            startActivity(openLinkIntent)
        }

        // Add OnClickListener to the LinkTextView
        LinkTextView.setOnClickListener {
            val openLinkIntent = Intent(Intent.ACTION_VIEW, Uri.parse(productLink))
            startActivity(openLinkIntent)
        }
    }
}