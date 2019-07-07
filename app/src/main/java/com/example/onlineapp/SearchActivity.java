package com.example.onlineapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchActivity extends AppCompatActivity {

    private EditText searchbox;
    private Button searchButton;
    private RecyclerView recyclerView;
    private StudentAdapter adapter;
    private LinearLayoutManager layoutManager;
    private List<Student> students;
    private String url = ApiUrl.SEARCH_URL;
    private TextView warning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        warning = findViewById(R.id.data);
        searchbox = findViewById(R.id.edittext);
        searchButton = findViewById(R.id.searchbutton);
        recyclerView = findViewById(R.id.reyclerview);
        recyclerView.setHasFixedSize(true);
        students = new ArrayList<>();
        SearchStudentFromServer();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchStudentFromServer();
            }
        });

    }

    private void SearchStudentFromServer() {

        final String getid = searchbox.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    students.clear();
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject object = jsonArray.getJSONObject(i);
                        students.add(new Student(
                                object.getString("student_id"),
                                object.getString("name"),
                                object.getString("email"),
                                object.getString("phone")
                        ));
                        warning.setVisibility(View.GONE);

                    }

                    adapter = new StudentAdapter(students,SearchActivity.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SearchActivity.this, "Error for "+error, Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();
                map.put("student_id",getid);
                return map;

            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);

    }
}
