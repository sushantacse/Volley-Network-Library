package com.example.onlineapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {

    private EditText studentid,name,email,phone;
    private Button addstudent;
    String url = ApiUrl.SEND_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        studentid = findViewById(R.id.student_id);
        name = findViewById(R.id.student_name);
        email = findViewById(R.id.student_email);
        phone = findViewById(R.id.student_phone);
        addstudent = findViewById(R.id.addstudent);
        addstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendStudentDataToServer();

            }
        });
    }
    private void sendStudentDataToServer() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {

                try
                {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    String code = jsonObject.getString("code");
                    String message = jsonObject.getString("message");
                    Toast.makeText(AddActivity.this, code+"\n\n"+message, Toast.LENGTH_SHORT).show();
                    studentid.setText("");
                    name.setText("");
                    email.setText("");
                    phone.setText("");
                    startActivity(new Intent(AddActivity.this, MainActivity.class));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "ERROR IS "+error, Toast.LENGTH_LONG).show();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<String, String>();
                map.put("student_id",studentid.getText().toString());
                map.put("name",name.getText().toString());
                map.put("email",email.getText().toString());
                map.put("phone",phone.getText().toString());
                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);

    }

    // using this method to call  back to go to previus activity
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
