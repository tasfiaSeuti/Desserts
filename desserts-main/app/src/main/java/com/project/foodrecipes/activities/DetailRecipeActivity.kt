package com.project.foodrecipes.activities

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.project.foodrecipes.model.ModelMain
import com.project.foodrecipes.networking.Api
import com.project.foodrecipes.R
import com.project.foodrecipes.databinding.ActivityDetailRecipeBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_recipe.*
import org.json.JSONException
import org.json.JSONObject


@Suppress("DEPRECATION")
class DetailRecipeActivity : AppCompatActivity() {

    private var idMeal: String? = null
    private var strMeal: String? = null
    private var modelMain: ModelMain? = null
    private var binding: ActivityDetailRecipeBinding? = null
    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (null == binding) binding = ActivityDetailRecipeBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)

        toolbar_detail?.title = null
        setSupportActionBar(toolbar_detail)
        assert(supportActionBar != null)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val picasso : Picasso = Picasso.get()

        progressDialog = ProgressDialog(this)
        progressDialog?.setTitle(getString(R.string.please_wait_msg))
        progressDialog?.setMessage(getString(R.string.displaying_data_msg))
        progressDialog?.setCancelable(false)
        progressDialog?.setCanceledOnTouchOutside(false)

        modelMain = intent.getSerializableExtra("detailRecipe") as ModelMain
        if (modelMain != null) {
            idMeal = modelMain?.idMeal
            strMeal = modelMain?.strMeal

            titleTextView.text = strMeal
            titleTextView.isSelected = true

            picasso.load(modelMain?.strMealThumb)
                .placeholder(R.drawable.ic_food_placeholder)
                .into(imgThumb)

            detailRecipe
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private val detailRecipe: Unit
        get() {
            progressDialog?.show()
            AndroidNetworking.get(Api.DetailRecipe)
                .addPathParameter("idMeal", idMeal)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        try {
                            progressDialog!!.dismiss()
                            val playerArray = response.getJSONArray("meals")
                            for (i in 0 until playerArray.length()) {

                                val temp = playerArray.getJSONObject(i)
                                val instructions = temp.getString("strInstructions")
                                tvInstructions!!.text = instructions

                                val category = temp.getString("strCategory")
                                val area = temp.getString("strArea")
                                subTitle?.text = category.plus(" | ").plus(area)

                                val source = temp.getString("strSource")
                                sourceTextView?.setOnClickListener {
                                    val intentYoutube = Intent(Intent.ACTION_VIEW)
                                    intentYoutube.data = Uri.parse(source)
                                    startActivity(intentYoutube)
                                }

                                val youtube = temp.getString("strYoutube")
                                youtubeTextView?.setOnClickListener { v: View? ->
                                    val intentYoutube = Intent(Intent.ACTION_VIEW)
                                    intentYoutube.data = Uri.parse(youtube)
                                    startActivity(intentYoutube)
                                }

                                val shareRecipe = temp.getString("strSource")
                                tvShareRecipe?.setOnClickListener {
                                    val shareIntent = Intent()
                                    shareIntent.action = Intent.ACTION_SEND
                                    shareIntent.type="text/plain"
                                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareRecipe)
                                    startActivity(Intent.createChooser(shareIntent, "Share with"))
                                }

                                for (n in 1 .. 20){
                                    val ingredient = temp.getString("strIngredient$n")
                                    val measure = temp.getString("strMeasure$n")
                                    if (ingredient.trim() != "" && ingredient.trim() != "null") tvIngredients.append("\n \u2022 $ingredient")
                                    if (measure.trim() != "" && measure.trim() != "null") tvMeasure.append("\n : $measure")
                                }

                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                            Toast.makeText(this@DetailRecipeActivity, getString(R.string.something_is_wrong), Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onError(anError: ANError) {
                        progressDialog?.dismiss()
                        Toast.makeText(this@DetailRecipeActivity, getString(R.string.no_internet_connection), Toast.LENGTH_SHORT).show()
                    }
                })
        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}