package com.example.onlineapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

import java.util.HashMap;
import java.util.Map;

public class StudentDetailsActivity extends AppCompatActivity {

    private TextView stdid,stdname,stdemail,stdphone;
    private Button delete;
    private String id,studentid,name,email,phone;

    private EditText std_id,std_name,std_email,std_phone;
    private Button updatestudent;
    private LinearLayout linearLayout;
    private Button showUpdatePanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        id = getIntent().getStringExtra("id");
        studentid = getIntent().getStringExtra("student_id");
        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        phone = getIntent().getStringExtra("phone");

        stdid = findViewById(R.id.studentid);
        stdname = findViewById(R.id.studentname);
        stdemail = findViewById(R.id.studentemail);
        stdphone = findViewById(R.id.studentphone);

        stdid.setText(studentid);
        stdname.setText(name);
        stdemail.setText(email);
        stdphone.setText(phone);

        delete = findViewById(R.id.deletebutton);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteservercall();
            }
        });

        std_id = findViewById(R.id.student_id);
        std_name = findViewById(R.id.student_name);
        std_email = findViewById(R.id.student_email);
        std_phone = findViewById(R.id.student_phone);
        updatestudent = findViewById(R.id.updatestudent);

        std_id.setText(studentid);
        std_name.setText(name);
        std_email.setText(email);
        std_phone.setText(phone);

        updatestudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateservercall();

            }
        });

        linearLayout = findViewById(R.id.linearlayout);
        showUpdatePanel = findViewById(R.id.showupdate);
        showUpdatePanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                linearLayout.setVisibility(View.VISIBLE);
            }
        });


    }

    private void updateservercall() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiUrl.UPDATE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Abirresponse",response);

                try
                {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    String code = jsonObject.getString("code");
                    String message = jsonObject.getString("message");
                    Toast.makeText(StudentDetailsActivity.this, code+"\n\n"+message, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(StudentDetailsActivity.this, MainActivity.class));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(StudentDetailsActivity.this, "Error for "+error, Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();
                map.put("id",id);
                map.put("student_id",std_id.getText().toString());
                map.put("name",std_name.getText().toString());
                map.put("email",std_email.getText().toString());
                map.put("phone",std_phone.getText().toString());
                return map;

            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);



    }

    private void deleteservercall() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiUrl.DELETE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Abirresponse",response);

                try
                {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    String code = jsonObject.getString("code");
                    String message = jsonObject.getString("message");
                    Toast.makeText(StudentDetailsActivity.this, code+"\n\n"+message, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(StudentDetailsActivity.this, MainActivity.class));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(StudentDetailsActivity.this, "Error for "+error, Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();
                map.put("id",id);
                return map;

            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);

    }


}
