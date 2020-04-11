package com.manitos.dev.gilinhobakingapp.api.utilities;

import com.manitos.dev.gilinhobakingapp.api.models.Bake;
import com.manitos.dev.gilinhobakingapp.api.models.Ingredient;
import com.manitos.dev.gilinhobakingapp.api.models.Step;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gilberto hdz on 11/04/20.
 */
public class BakeListJsonUtils {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String SERVINGS = "servings";
    private static final String IMAGE = "image";

    private static final String INGREDIENTS = "ingredients";
    private static final String QUANTITY = "quantity";
    private static final String MEASURE = "measure";
    private static final String INGREDIENT = "ingredient";

    private static final String STEPS = "steps";
    private static final String SHORT_DESCRIPTION = "shortDescription";
    private static final String DESCRIPTION = "description";
    private static final String VIDEO_URL = "videoURL";
    private static final String THUMBNAIL_URL = "thumbnailURL";

    public static ArrayList<Bake> getBakeListFromJson(String bakeJsonStr) throws JSONException {

        JSONArray bakeArr = new JSONArray(bakeJsonStr);
        ArrayList<Bake> bakes = new ArrayList<>(bakeArr.length());

        // BAKES
        for (int i = 0; i < bakeArr.length(); i++) {
            Integer _id;
            String _name;
            Integer _servings;
            String _image;

            // BAKE
            JSONObject bakeObj = bakeArr.getJSONObject(i);
            _id = bakeObj.getInt(ID);
            _name = bakeObj.getString(NAME);
            _servings = bakeObj.getInt(SERVINGS);
            _image = bakeObj.getString(IMAGE);

            // INGREDIENTS
            JSONArray ingredientsArr = bakeObj.getJSONArray(INGREDIENTS);
            ArrayList<Ingredient> ingredients = new ArrayList<>(ingredientsArr.length());
            for (int j = 0; j < ingredientsArr.length(); j++) {
                Double _quantity;
                String _measure;
                String _ingredient;

                JSONObject ingredientObj = ingredientsArr.getJSONObject(j);
                _quantity = ingredientObj.getDouble(QUANTITY);
                _measure = ingredientObj.getString(MEASURE);
                _ingredient = ingredientObj.getString(INGREDIENT);

                ingredients.add(new Ingredient(_quantity, _measure, _ingredient));
            }

            // STEPS
            JSONArray stepsArr = bakeObj.getJSONArray(STEPS);
            ArrayList<Step> steps = new ArrayList<>(stepsArr.length());
            for (int k = 0; k < stepsArr.length(); k++) {
                Integer _stepId;
                String _shortDesc;
                String _desc;
                String _videoUrl;
                String _thumbnailUrl;

                JSONObject stepObj = stepsArr.getJSONObject(k);
                _stepId = stepObj.getInt(ID);
                _shortDesc = stepObj.getString(SHORT_DESCRIPTION);
                _desc = stepObj.getString(DESCRIPTION);
                _videoUrl = stepObj.getString(VIDEO_URL);
                _thumbnailUrl = stepObj.getString(THUMBNAIL_URL);

                steps.add(new Step(_stepId, _shortDesc, _desc, _videoUrl, _thumbnailUrl));
            }

            Bake bake = new Bake(_id, _name, _servings, _image, ingredients, steps);
            bakes.add(bake);
        }

        return bakes;
    }
}
