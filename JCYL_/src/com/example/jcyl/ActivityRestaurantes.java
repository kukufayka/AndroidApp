package com.example.jcyl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
// Aleksandrina Davidova
public class ActivityRestaurantes extends Activity {

	private CSVAdapter adapter;
	private ArrayList<Restorauntes> li;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurantes);
		loadArraylist();

		// Restoraunte
		// rest=this.getIntent().getSerializableExtra("restaurante");

		ListView restaurantes = (ListView) findViewById(R.id.lv_res);
		restaurantes.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				ListView restaurantes = (ListView) findViewById(R.id.lv_res);
				Restorauntes r = (Restorauntes) restaurantes.getItemAtPosition(arg2);
				Intent intent = new Intent(ActivityRestaurantes.this, ResumenRestActivity.class);
				intent.putExtra("restaurante", r);
				startActivityForResult(intent,1);
				
				// Toast.makeText(ActivityRestaurantes.this, r.toString(),
				// Toast.LENGTH_SHORT).show();
			}
		});
		
		
		Spinner listaprovincias = (Spinner) findViewById(R.id.sp_res);
		listaprovincias.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				Spinner listaprovincias = (Spinner) findViewById(R.id.sp_res);
				String provincia = (String) listaprovincias
						.getItemAtPosition(arg2);
				ArrayList<Restorauntes> dato = cargarList(provincia);
				ListView restaurantes = (ListView) findViewById(R.id.lv_res);
				ArrayAdapter<Restorauntes> adaptador = new ArrayAdapter<Restorauntes>(
						ActivityRestaurantes.this,
						android.R.layout.simple_list_item_1, dato);
				restaurantes.setAdapter(adaptador);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
	}

	public void loadArraylist() {
		li = new ArrayList<Restorauntes>();
		try {
			//String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
		    //String ru = "/restaurantes.csv";
			//FileInputStream is = new FileInputStream(new File(baseDir + File.separator + ru));
			InputStream is = getResources().getAssets().open("restaurantes.csv");
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.ISO_8859_1));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] rowData = line.split(";");
				
				String corNombre = CoregirDato(rowData, 2);
				String corPro = CoregirDato(rowData, 5);
				String corLoc= CoregirDato(rowData, 7);
				String corDir= CoregirDato(rowData, 3);
				String corTel=CoregirDato(rowData, 9);
				String corTel2=CoregirDato(rowData, 10);
				String corEmail= CoregirDato(rowData, 13);
				String corWeb= CoregirDato(rowData, 14);
				
				Restorauntes r = new Restorauntes();
				r.setCategoria(rowData[1]);
				r.setNombre(corNombre);
				r.setProvincia(corPro);
				r.setLocalidad(corLoc);
				r.setDireccion(corDir);
				r.setTelefono(corTel);
				r.setTelefono_2(corTel2);
				r.setEmail(corEmail);
				r.setWeb(corWeb);
				li.add(r);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public String CoregirDato (  String [] row,int i ){
		String dato= row[i];
		String coregido= dato.substring(1,dato.length() -1);
		
		return coregido;
		
	}

	public ArrayList<Restorauntes> cargarList(String provincia) {
		ArrayList<Restorauntes> filtrado = new ArrayList<Restorauntes>();
		for (int i = 0; i < li.size(); i++) {
			String respro = li.get(i).getProvincia().toString();
			if (respro.equals(provincia)) {
				filtrado.add(li.get(i));
			}
		}
		return filtrado;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Bundle b = data.getExtras();
                if (b != null) {
                    Restorauntes r = (Restorauntes) b.getSerializable("restaurante");
                   
                }  
            } else if (resultCode == 0) {
                System.out.println("RESULT CANCELLED");    
            }
        }
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.restaurantes, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
