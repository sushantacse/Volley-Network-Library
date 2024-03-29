package com.example.onlineapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StudentAdapter adapter;
    private LinearLayoutManager layoutManager;
    private List<Student> students;
    private String url = ApiUrl.READ_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.reyclerview);
        recyclerView.setHasFixedSize(true);
        students = new ArrayList<>();
        CourseDatabase();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void CourseDatabase()
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("serverRsp",response);
                try {

                    JSONArray jsonArray = new JSONArray(response);
                    if (jsonArray.length() == 0)
                    {
                        Toast.makeText(MainActivity.this, "No data Found", Toast.LENGTH_SHORT).show();

                    }
                    for (int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject object = jsonArray.getJSONObject(i);
                        students.add(new Student(
                                object.getString("id"),
                                object.getString("student_id"),
                                object.getString("name"),
                                object.getString("email"),
                                object.getString("phone")
                        ));


                    }

                    adapter = new StudentAdapter(students,MainActivity.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, "Error for "+error, Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);

    }

    public void gotosearch(View view) {

        startActivity(new Intent(getApplicationContext(),SearchActivity.class));
    }


    public void gotoaddstudent(View view) {

        startActivity(new Intent(getApplicationContext(),AddActivity.class));
    }
}
