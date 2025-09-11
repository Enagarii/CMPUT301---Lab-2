package com.example.listycity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

	ListView cityList;
	ArrayAdapter<String> cityAdapter;
	ArrayList<String> dataList;

	Button addCity;
	Button deleteCity;
	Button confirmCity;
	EditText inputCity;

	String selectedCity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		cityList = findViewById(R.id.city_list);

		String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

		dataList = new ArrayList<>();
		dataList.addAll(Arrays.asList(cities));

		cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
		cityList.setAdapter(cityAdapter);

		addCity = findViewById(R.id.add_city);
		deleteCity = findViewById(R.id.delete_city);
		confirmCity = findViewById(R.id.confirm_add);
		inputCity = findViewById(R.id.input_city);

		cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				selectedCity = cityList.getItemAtPosition(position).toString();

				for (int i = 0; i < cityList.getChildCount(); i++)
				{
					if (position == i)
					{
						cityList.getChildAt(i).setBackgroundColor(Color.LTGRAY);
					}
					else
					{
						cityList.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
					}
				}
			}
		});


		addCity.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v)
			{
				inputCity.setVisibility(View.VISIBLE);
				confirmCity.setVisibility(View.VISIBLE);
			}
		});

		deleteCity.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v)
			{
				if (selectedCity == null) { return; }
				dataList.remove(selectedCity);
				cityAdapter.notifyDataSetChanged();

				for (int i = 0; i < cityList.getChildCount(); i++)
				{
					cityList.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
				}
			}
		});

		confirmCity.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v)
			{
				String input = inputCity.getText().toString().strip();

				if (input.equals("")){ return; }
				dataList.add(input);
				cityAdapter.notifyDataSetChanged();

				inputCity.setText("");

				inputCity.setVisibility(View.INVISIBLE);
				confirmCity.setVisibility(View.INVISIBLE);
			}
		});
	}



}