package com.example.sage.foodsharebook.Dishes

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.AsyncTask
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast

import com.cloudinary.Cloudinary
import com.cloudinary.utils.ObjectUtils
import com.example.sage.foodsharebook.Ingredients.IngredientListActivity
import com.example.sage.foodsharebook.R
import com.example.sage.foodsharebook.adapters.DishIngredientAdapter
import com.example.sage.foodsharebook.apiFoodShareBookServices.ApiRetrofit
import com.example.sage.foodsharebook.models.Dish
import com.example.sage.foodsharebook.models.DishIngredient

import java.io.IOException
import java.util.HashMap




class DishFormActivity : AppCompatActivity() {
    private val TAG = "DishForm"

    private var name: EditText? = null
    private var recipe: EditText? = null
    private var description: EditText? = null
    private var api: ApiRetrofit? = null
    private var btnCreate: Button? = null
    private var btnAddIngredient: Button? = null
    private var recyclerView: RecyclerView? = null
    private var adapter: DishIngredientAdapter? = null
    private var ib_dish_pic: ImageButton? = null
    private var imgDecodableString: String? = null
    private var imageURL = ""
    private var uploadServ: uploadImage? = null
    internal var prefs: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish_form)

        api = ApiRetrofit(this)
        uploadServ = uploadImage()


        name = findViewById(R.id.te_dish_name)
        recipe = findViewById(R.id.te_dish_recipe)
        description = findViewById(R.id.te_dish_desc)
        btnAddIngredient = findViewById(R.id.btn_add_ingredient)
        btnCreate = findViewById(R.id.btn_create_dish)
        recyclerView = findViewById(R.id.rv_ingredients)
        ib_dish_pic = findViewById(R.id.ib_dish_pic)

        adapter = DishIngredientAdapter(this)
        recyclerView!!.adapter = adapter
        recyclerView!!.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(this, 1)
        recyclerView!!.layoutManager = layoutManager
        // TODO select image for dish

        ib_dish_pic!!.setOnClickListener { view -> loadImageFromGallery(view) }


        btnCreate!!.setOnClickListener {
            enableInputs(false)
            //TODO: validate empty fields
            if (adapter!!.itemCount > 1)
                uploadServ!!.execute()
            else
                Toast.makeText(applicationContext, "Por favor elija dos o mas ingredientes", Toast.LENGTH_SHORT).show()
            enableInputs(true)
        }

        btnAddIngredient!!.setOnClickListener {
            val ingredientListActivity = Intent(applicationContext, IngredientListActivity::class.java)
            startActivityForResult(ingredientListActivity, PICK_DISH_INGREDIENT_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            PICK_DISH_INGREDIENT_REQUEST -> if (resultCode == Activity.RESULT_OK && null != data) {
                if (data != null) {
                    adapter!!.addDishIngredientItem(DishIngredient(
                            data.getIntExtra(INGREDIENT_ID, -1),
                            data.getStringExtra(INGREDIENT_NAME),
                            data.getStringExtra(INGREDIENT_IMAGE),
                            data.getIntExtra(MEASURE_ID, -1),
                            data.getFloatExtra(QUANTITY, -1.0f)
                    ))
                }
            }
            RESULT_LOAD_IMG -> try {
                if (resultCode == Activity.RESULT_OK && null != data) {
                    val selectedImage = data.data
                    val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)

                    val cursor = contentResolver.query(selectedImage!!, filePathColumn, null, null, null)
                    cursor!!.moveToFirst()
                    val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                    imgDecodableString = cursor.getString(columnIndex)
                    cursor.close()
                    val imgView = findViewById<ImageView>(R.id.ib_dish_pic)
                    imgView.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString))
                } else {
                    Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
            }

        }

    }

    //TODO: use realm to store the measures selection
    fun loadImageFromGallery(view: View) {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(gallery, RESULT_LOAD_IMG)
    }

    private fun enableInputs(status: Boolean) {
        name!!.isEnabled = status
        recipe!!.isEnabled = status
        description!!.isEnabled = status
        btnCreate!!.isEnabled = status
    }

    private fun clearFields() {
        name!!.setText("")
        recipe!!.setText("")
        description!!.setText("")
        ib_dish_pic!!.setImageResource(R.color.design_default_color_primary_dark)
        imageURL = ""
    }

    private fun createIngredient() {
        prefs = applicationContext.getSharedPreferences(SHARED_PREFERENCES_NAME, 0)

        api!!.postDish(Dish(name!!.text.toString().trim { it <= ' ' }, description!!.text.toString().trim { it <= ' ' }, recipe!!.text.toString().trim { it <= ' ' }, imageURL, adapter!!.dataset)) { bool, dish ->
            if (bool!!) {
                clearFields()
                adapter!!.clearDataset()
                //TODO: send to see only that dish
                Log.i(TAG, "" + dish!!)
            } else {
                Log.i(TAG, "Something went wrong")
            }
        }
    }

    private inner class uploadImage : AsyncTask<Void, Void, Void>() {
        private val cloudinary: Cloudinary

        init {
            val config = HashMap()
            config.put("cloud_name", "dbo96sjb")
            config.put("api_key", "757447362712211")
            config.put("api_secret", "z_F0g_ccUUJG24DDJJjyNdjl0RM")
            this.cloudinary = Cloudinary(config)
        }


        override fun doInBackground(vararg params: Void): Void? {
            try {
                val response = cloudinary.uploader().upload(imgDecodableString, ObjectUtils.asMap("folder", "ingredients"))
                imageURL = response["url"]!!.toString()
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return null
        }

        override fun onPostExecute(result: Void) {
            //Actualizar la imagen
            /*Log.i(TAG, "Imagen subida");
            Ingredient toUpdate = new Ingredient(ingredient);
            toUpdate.setImage(imageURL);
            api.putIngredient(ingredient.getId(), toUpdate, new ApiRetrofit.IngredientCallBack() {
                @Override
                public void response(Boolean bool, Dish ingredient) {
                    if(bool) {
                        Log.i(TAG, "URL de imagen agregada");
                    }
                }
            });*/
            createIngredient()
        }
    }

    companion object {

        private val PICK_DISH_INGREDIENT_REQUEST = 1
        private val RESULT_LOAD_IMG = 2
    }

}
