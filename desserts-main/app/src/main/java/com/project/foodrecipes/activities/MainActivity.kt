package com.project.foodrecipes.activities

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.google.firebase.auth.FirebaseAuth
import com.project.foodrecipes.adapter.MainAdapter
import com.project.foodrecipes.model.ModelMain
import com.project.foodrecipes.networking.Api
import com.project.foodrecipes.R
import com.project.foodrecipes.databinding.ActivityMainBinding
import org.json.JSONException
import org.json.JSONObject

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), MainAdapter.OnSelectData {

    private var mainAdapter: MainAdapter? = null
    private var binding: ActivityMainBinding? = null
    private var progressDialog: ProgressDialog? = null
    private var mainModel: MutableList<ModelMain> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (null == binding) binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)

        progressDialog = ProgressDialog(this)
        progressDialog?.setTitle(getString(R.string.please_wait_msg))
        progressDialog?.setMessage(getString(R.string.displaying_data_msg))
        progressDialog?.setCancelable(false)
        progressDialog?.setCanceledOnTouchOutside(false)

        val mLayoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
        binding?.menuOfDessert?.layoutManager = mLayoutManager
        binding?.menuOfDessert?.setHasFixedSize(true)

        categories

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        FirebaseAuth.getInstance().signOut()
    }

    private val categories: Unit
        get() {
            AndroidNetworking.get(Api.Desssert)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        try {
                            val playerArray = response.getJSONArray("meals")
                            for (i in 0 until playerArray.length()) {

                                val temp = playerArray.getJSONObject(i)
                                val dataApi = ModelMain()
                                dataApi.idMeal = temp.getString("idMeal")
                                dataApi.strMeal = temp.getString("strMeal")
                                dataApi.strMealThumb = temp.getString("strMealThumb")
                                mainModel.add(dataApi)
                                showCategories()
                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                            Toast.makeText(this@MainActivity, getString(R.string.something_is_wrong), Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onError(anError: ANError) {
                        Toast.makeText(this@MainActivity, getString(R.string.no_internet_connection), Toast.LENGTH_SHORT).show()
                    }
                })
        }

    private fun showCategories() {
        mainAdapter = MainAdapter( mainModel, this)
        binding?.menuOfDessert?.adapter = mainAdapter
    }

    override fun onSelected(modelMain: ModelMain?) {
        val intent = Intent(this@MainActivity, DetailRecipeActivity::class.java)
        intent.putExtra("detailRecipe", modelMain)
        startActivity(intent)
    }
}