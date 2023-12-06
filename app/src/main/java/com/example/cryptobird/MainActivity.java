package com.example.cryptobird;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.cryptobird.Adapter.RecyclerItemAcdapter;
import com.example.cryptobird.databinding.ActivityMainBinding;
import com.example.cryptobird.models.Modelitem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    // important imports

    private ArrayList<Modelitem> data;
    private RecyclerItemAcdapter adapter;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater()); // inflate the layout
        setContentView(binding.getRoot()); // set the content view to the root of the binding object


        data = new ArrayList<>(); // initialize the data arraylist
        adapter = new RecyclerItemAcdapter(this, data); // initialize the adapter
        fetchData(this);// to fetch the data from the api

        // add data to the data arraylist

        binding.homeitemrv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)); // set the layout manager
        binding.homeitemrv.setAdapter(adapter); // set the adapter


        // search bar


    }

    public void fetchData(Context context) {
        String url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() { // Specify the type parameter for Response.Listener
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle the response
                        binding.progressBar.setVisibility(binding.progressBar.GONE);
                        try {
                            JSONArray dataArray = response.getJSONArray("data");

                            for (int i = 0; i < dataArray.length(); i++) { // Use < instead of <=
                                JSONObject dataObject = dataArray.getJSONObject(i);
                                String name = dataObject.getString("name");
                                String symbol = dataObject.getString("symbol");
                                String usd = dataObject.getJSONObject("quote").getJSONObject("USD").getString("price");
                                Float price1 = Float.parseFloat(usd);

                                String price = String.format("%.2f", price1);


                                // Add your Modelitem to the 'data' list
                                data.add(new Modelitem(name, symbol, price));
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace(); // Handle the exception appropriately
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                // headers.put("X-CMC_PRO_API_KEY", "70917751-257-47ed-8760-21592075f968"); //PAID KEY
                headers.put("X-CMC_PRO_API_KEY", "7dc864d0-f5ae-44f2-8027-869b6bc4138e");
                return headers;
            }
        };

        queue.add(jsonObjectRequest);
    }

}