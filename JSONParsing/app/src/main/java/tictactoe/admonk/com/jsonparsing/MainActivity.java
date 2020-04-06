package tictactoe.admonk.com.jsonparsing;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    TextView tvAuthor;
    TextView tvRating;
    TextView tvYear;
    ListView lvMovies;
    TextView tvMovies;

    ArrayList<HashMap<String, String>> moviesArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvAuthor = (TextView) findViewById(R.id.tvAuthor);
        lvMovies = (ListView) findViewById(R.id.lvMovies);

        String fileData = ReadFromFile("moviesData.json");

        ConvertStringToJSON(fileData);

    }

    private String ReadFromFile(String filename) {
        StringBuilder returnString = new StringBuilder();
        InputStream inputStream = null;
        InputStreamReader inputSteamReader = null;
        BufferedReader reader = null;
        try {
            inputStream = this.getResources().getAssets()
                    .open(filename, Context.MODE_WORLD_READABLE);
            inputSteamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputSteamReader);
            String line = "";
            while ((line = reader.readLine()) != null) {
                returnString.append(line);
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            //Error Handling
            try {
                if (inputSteamReader != null)
                    inputSteamReader.close();
                if (inputStream != null)
                    inputStream.close();
                if (reader != null)
                    reader.close();
            } catch (Exception e2) {
                e2.getMessage();
            }
        }
        return returnString.toString();

    }

    private void ConvertStringToJSON(String filedata) {
        try {
            JSONObject completeData = new JSONObject(filedata);
            JSONObject moviesData = completeData.getJSONObject("movieData");
            JSONObject authorData = completeData.getJSONObject("authorData");

            String authorName = authorData.getString("name");
            String company = authorData.getString("company");
            String website = authorData.getString("website");

            tvAuthor.setText(authorName + "\n" + company + "\n" + website);

            JSONArray moviesList = moviesData.getJSONArray("moviesList");

            for (int i = 0; i < moviesList.length(); i++) {
                //Fetching data for each movie from object
                JSONObject currentMovieData = moviesList.getJSONObject(i);

                String name = currentMovieData.getString("movieName");
                String year = currentMovieData.getString("year");
                String rating = currentMovieData.getString("rating");

                HashMap<String, String> movies = new HashMap<>();
                movies.put("name", name);
                movies.put("year", year);
                movies.put("rating", rating);

                moviesArrayList.add(movies);
            }
            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, // Context of the activity
                    moviesArrayList, // ArrayList containing data
                    R.layout.item_movies, // Layout which we will use to display data
                    new String[]{"name", "year", "rating"}, // Keys of hashmap to get values
                    new int[]{R.id.tvMovie, R.id.tvYear, R.id.tvRating} // Set values to textview accordingly.
            );

            lvMovies.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

