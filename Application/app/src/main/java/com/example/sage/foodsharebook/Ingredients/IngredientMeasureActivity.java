package com.example.sage.foodsharebook.Ingredients;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.sage.foodsharebook.Config.Constants.*;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.sage.foodsharebook.R;
import com.example.sage.foodsharebook.models.DishIngredient;

import java.util.ArrayList;
import java.util.List;

public class IngredientMeasureActivity extends AppCompatActivity {
    private static String TAG = "IngredientMeasureActivity";
    private TextView tv_ingredient_name;
    private EditText et_quantity;
    private ImageView iv_ingredient_image;
    private RadioGroup rg_measures;
    private Button btn_add_ingredient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_measure);

        iv_ingredient_image = findViewById(R.id.iv_ingredient_pic);
        tv_ingredient_name = findViewById(R.id.tv_ingredient_name);
        et_quantity = findViewById(R.id.et_measure_quantity);
        rg_measures = findViewById(R.id.rg_measures);
        btn_add_ingredient = findViewById(R.id.btn_add_ingredient);


        final Intent intent = getIntent();
        final String ingredient_name = intent.getStringExtra(INGREDIENT_NAME);
        final String ingredient_image = intent.getStringExtra(INGREDIENT_IMAGE);
        String measuresString = intent.getStringExtra(INGREDIENT_MEASURES);
        if(!measuresString.isEmpty()){
            List<Integer> measures = new ArrayList<>();
            for(String measure : measuresString.split(",")){
                measures.add(Integer.parseInt(measure));
            }
            makeRadioButtons(measures, rg_measures);
        }


        Glide.with(getApplicationContext())
                .load(ingredient_image)
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(iv_ingredient_image);
        tv_ingredient_name.setText(ingredient_name);

        btn_add_ingredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_quantity.getText().toString().isEmpty() || rg_measures.getCheckedRadioButtonId() == -1 ){
                    Toast.makeText(getApplicationContext(), "Fields missing", Toast.LENGTH_SHORT).show();
                } else {
                    int measure_id = (int)findViewById(rg_measures.getCheckedRadioButtonId()).getTag();
                    Log.i(TAG, "Mostrando ingrediente creado");
                    DishIngredient dishIngredient = new DishIngredient(
                      intent.getIntExtra(INGREDIENT_ID,-1),
                      ingredient_name,
                      ingredient_image,
                      measure_id,
                      Float.parseFloat(et_quantity.getText().toString().trim())
                    );
                    Intent intent = new Intent();
                    intent.putExtra(INGREDIENT_NAME, dishIngredient.getIngredientName());
                    intent.putExtra(INGREDIENT_IMAGE, dishIngredient.getIngredientImage());
                    intent.putExtra(QUANTITY, dishIngredient.getQuantity());
                    intent.putExtra(MEASURE_ID, dishIngredient.getMeasureId());
                    intent.putExtra(INGREDIENT_ID, dishIngredient.getIngredientId());
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });

    }

    private void makeRadioButtons(List<Integer> measures, RadioGroup radioGroup ){
        int i = 0;
        for(Integer measure : measures){
            RadioButton rb = new RadioButton(this);
            rb.setText(measureString(measure));
            rb.setTag(measure);
            radioGroup.addView(rb);
            i++;
        }
    }

    private String measureString(int measure){
        String result = "";
        switch (measure){
            case 1: result = "Piezas";
            break;
            case 2: result = "Gramos";
            break;
            case 3: result = "Kilogramos";
            break;
            case 4: result = "Mililitros";
            break;
            case 5: result = "Litros";
            break;
            case 6: result = "Cucharadas";
            break;
            case 7: result = "Tazas";
            break;
            case 8: result = "Rebanadas";
            break;
            case 9: result = "Pieza";
            break;
            default: result = "";
        }
        return result;
    }
}
