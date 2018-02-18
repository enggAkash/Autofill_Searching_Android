package com.example.autofillrough1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView userName;
    private EditText userMobile, userEmail, userAddress;

    private String jsonString = "{\n" +
            "  \"user_detail\": [\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"name\": \"Akash\",\n" +
            "      \"mobile\": \"9999999999\",\n" +
            "      \"email\": \"email@example.com\",\n" +
            "      \"address\": \"New Delhi, India\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 2,\n" +
            "      \"name\": \"sarvesh\",\n" +
            "      \"mobile\": \"2333377777\",\n" +
            "      \"email\": \"email@sarvu.com\",\n" +
            "      \"address\": \"Kolkata, India\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 3,\n" +
            "      \"name\": \"Chandan\",\n" +
            "      \"mobile\": \"5555555555\",\n" +
            "      \"email\": \"email@chandan.com\",\n" +
            "      \"address\": \"Beijing, Chin\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 4,\n" +
            "      \"name\": \"Harry\",\n" +
            "      \"mobile\": \"3333333333\",\n" +
            "      \"email\": \"nepali@example.com\",\n" +
            "      \"address\": \"Kathmandoo, Nepal\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 5,\n" +
            "      \"name\": \"Nishant\",\n" +
            "      \"mobile\": \"3333333333\",\n" +
            "      \"email\": \"nishant@bhushan.com\",\n" +
            "      \"address\": \"Greater Noida, India\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 6,\n" +
            "      \"name\": \"Indar\",\n" +
            "      \"mobile\": \"2222222222\",\n" +
            "      \"email\": \"indarjeet2017@gmail.com\",\n" +
            "      \"address\": \"Karol Bagh, Delhi, India\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 7,\n" +
            "      \"name\": \"Aisha\",\n" +
            "      \"mobile\": \"77777777\",\n" +
            "      \"email\": \"aisha@example.com\",\n" +
            "      \"address\": \"Canbera, Australia\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 8,\n" +
            "      \"name\": \"Drona\",\n" +
            "      \"mobile\": \"666999999\",\n" +
            "      \"email\": \"drona@example.com\",\n" +
            "      \"address\": \"Bihar, India\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 9,\n" +
            "      \"name\": \"David\",\n" +
            "      \"mobile\": \"779999999\",\n" +
            "      \"email\": \"david@example.com\",\n" +
            "      \"address\": \"Washington, USA\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 10,\n" +
            "      \"name\": \"Jack\",\n" +
            "      \"mobile\": \"8899999999\",\n" +
            "      \"email\": \"jack@example.com\",\n" +
            "      \"address\": \"New York, USA\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    private ArrayList<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        userList = extractUser(jsonString);

        UserAdapter userAdapter = new UserAdapter(this, R.layout.user_raw_layout, userList);

        userName.setAdapter(userAdapter);
        userName.setThreshold(1);

        userName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                User user = (User) parent.getItemAtPosition(position);

                userName.setText(user.getName());
                userMobile.setText(user.getMobile());
                userEmail.setText(user.getEmail());
                userAddress.setText(user.getAddress());

            }
        });

    }

    private void initView() {
        userName = findViewById(R.id.user_name);
        userMobile = findViewById(R.id.user_mobile);
        userEmail = findViewById(R.id.user_email);
        userAddress = findViewById(R.id.user_address);

        userList = new ArrayList<>();

    }


    private ArrayList<User> extractUser(String jsonString) {
        ArrayList<User> list = new ArrayList<>();

        try {
            JSONObject rootJO = new JSONObject(jsonString);

            JSONArray userJA = rootJO.getJSONArray("user_detail");

            for (int i = 0; i < userJA.length(); i++) {

                JSONObject jo = userJA.getJSONObject(i);

                int id = jo.getInt("id");
                String name = jo.getString("name");
                String mobile = jo.getString("mobile");
                String email = jo.getString("email");
                String address = jo.getString("address");

                User user = new User(id, name, mobile, email, address);

                list.add(user);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }


}
