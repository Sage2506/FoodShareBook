package com.example.sage.foodsharebook.Dishes;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.sage.foodsharebook.Ingredients.IngredientListActivity;
import com.example.sage.foodsharebook.R;
import com.example.sage.foodsharebook.adapters.DishIngredientAdapter;
import com.example.sage.foodsharebook.apiFoodShareBookServices.ApiRetrofit;
import com.example.sage.foodsharebook.models.Dish;
import com.example.sage.foodsharebook.models.DishIngredient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.example.sage.foodsharebook.Config.Constants.*;


public class DishFormActivity extends AppCompatActivity {
    private final String TAG = "DishForm";

    private static final int PICK_DISH_INGREDIENT_REQUEST = 1;
    private static final int RESULT_LOAD_IMG = 2;

    private EditText name;
    private EditText recipe;
    private EditText description;
    private ApiRetrofit api;
    private Button btnCreate;
    private Button btnAddIngredient;
    private RecyclerView recyclerView;
    private DishIngredientAdapter adapter;
    private ImageButton ib_dish_pic;
    private String imgDecodableString;
    private String imageURL = "";
    private uploadImage uploadServ;
    SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_form);

        api = new ApiRetrofit(this);
        uploadServ = new uploadImage();


        name = findViewById(R.id.te_dish_name);
        recipe = findViewById(R.id.te_dish_recipe);
        description = findViewById(R.id.te_dish_desc);
        btnAddIngredient = findViewById(R.id.btn_add_ingredient);
        btnCreate = findViewById(R.id.btn_create_dish);
        recyclerView = findViewById(R.id.rv_ingredients);
        ib_dish_pic = findViewById(R.id.ib_dish_pic);

        adapter = new DishIngredientAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager( this, 1);
        recyclerView.setLayoutManager(layoutManager);
        // TODO select image for dish

        ib_dish_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImageFromGallery(view);
            }
        });


        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableInputs(false);
                //TODO: validate empty fields
                if(adapter.getItemCount() > 1)
                    uploadServ.execute();
                else
                    Toast.makeText(getApplicationContext(), "Por favor elija dos o mas ingredientes", Toast.LENGTH_SHORT).show();
                enableInputs(true);
            }
        });

        btnAddIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ingredientListActivity = new Intent(getApplicationContext(),IngredientListActivity.class);
                startActivityForResult(ingredientListActivity,PICK_DISH_INGREDIENT_REQUEST);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case PICK_DISH_INGREDIENT_REQUEST:
                if (resultCode == Activity.RESULT_OK && null != data)
                {
                    if(data != null){
                        adapter.addDishIngredientItem(new DishIngredient(
                                data.getIntExtra(INGREDIENT_ID,-1),
                                data.getStringExtra(INGREDIENT_NAME),
                                data.getStringExtra(INGREDIENT_IMAGE),
                                data.getIntExtra(MEASURE_ID,-1),
                                data.getFloatExtra(QUANTITY,-1.0f)
                        ));
                    }
                }
            break;
            case RESULT_LOAD_IMG:
                try {
                    if (resultCode == Activity.RESULT_OK && null != data){
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = { MediaStore.Images.Media.DATA };

                        Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        imgDecodableString = cursor.getString(columnIndex);
                        cursor.close();
                        ImageView imgView = findViewById(R.id.ib_dish_pic );
                        imgView.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));
                    }else {
                        Toast.makeText(this, "You haven't picked Image",Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e)
                {
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG) .show();
                }
                break;
        }

    }
    //TODO: use realm to store the measures selection
    public void loadImageFromGallery(View view){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery, RESULT_LOAD_IMG);
    }

    private void enableInputs(boolean status){
        name.setEnabled(status);
        recipe.setEnabled(status);
        description.setEnabled(status);
        btnCreate.setEnabled(status);
    }

    private void clearFields(){
        name.setText("");
        recipe.setText("");
        description.setText("");
        ib_dish_pic.setImageResource(R.color.design_default_color_primary_dark);
        imageURL = "";
    }

    private void createIngredient(){
        prefs = getApplicationContext().getSharedPreferences(SHARED_PREFERENCES_NAME,0);

        api.postDish(new Dish(name.getText().toString().trim(), description.getText().toString().trim(), recipe.getText().toString().trim(),imageURL, adapter.getDataset()), new ApiRetrofit.DishCallBack() {
            @Override
            public void response(Boolean bool, Dish dish) {
                if(bool){
                    clearFields();
                    adapter.clearDataset();
                    //TODO: send to see only that dish
                    Log.i(TAG,""+dish);
                } else {
                    Log.i(TAG,"Something went wrong");
                }
            }
        });
    }

    private class uploadImage extends AsyncTask<Void, Void, Void> {
        private Cloudinary cloudinary;



        public uploadImage() {
            super();
            Map config = new HashMap();
            config.put("cloud_name","dbo96sjb");
            config.put("api_key","757447362712211");
            config.put("api_secret","z_F0g_ccUUJG24DDJJjyNdjl0RM");
            this.cloudinary = new Cloudinary(config);
        }



        protected Void doInBackground(Void... params) {
            try {
                Map response = cloudinary.uploader().upload(imgDecodableString, ObjectUtils.asMap("folder","ingredients"));
                imageURL = response.get("url").toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
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
            createIngredient();
        }
    }

}
