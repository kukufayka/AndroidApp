package com.example.jcyl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
// Aleksandrina Davidova
public class ActivityHoteles extends Activity {
	
	private ArrayList<Hoteles> ho;
	Hoteles h ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hoteles);
		
		loadArraylist();
		
		ListView hoteles = (ListView) findViewById(R.id.lv_hotels);
		hoteles.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				ListView hoteles = (ListView) findViewById(R.id.lv_hotels);
				Hoteles h = (Hoteles) hoteles.getItemAtPosition(arg2);
				Intent intent = new Intent(ActivityHoteles.this, ResumenHotActivity.class);
				intent.putExtra("hoteles", h);
				startActivityForResult(intent,1);
				
				
			}
		});
		
		Spinner listaprovincias = (Spinner) findViewById(R.id.sp_hot);
		listaprovincias.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				
				Spinner listaprovincias = (Spinner) findViewById(R.id.sp_hot);
				String provincia = (String) listaprovincias.getItemAtPosition(arg2);
				ArrayList<Hoteles> dato = cargarList(provincia);
				ListView hoteles = (ListView) findViewById(R.id.lv_hotels);
				ArrayAdapter<Hoteles> adaptador = new ArrayAdapter<Hoteles>
				(ActivityHoteles.this, android.R.layout.simple_list_item_1, dato);
				hoteles.setAdapter(adaptador);	
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		 
	}
	
	public void loadArraylist() {
		ho = new ArrayList<Hoteles>();
		
		try {
					
			//String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
		    //String ru = "/alojamientoshoteleros.csv";
			//FileInputStream is = new FileInputStream(new File(baseDir + File.separator + ru));
			InputStream is = getResources().getAssets().open("alojamientoshoteleros.csv");
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
				
			    h = new Hoteles();
				h.setCategoria(rowData[1]);
				h.setNombre(corNombre);
				h.setProvincia(corPro);
				h.setLocalidad(corLoc);
				h.setDireccion(corDir);
				h.setTelefono(corTel);
				h.setTelefono_2(corTel2);
				h.setEmail(corEmail);
				h.setWeb(corWeb);
				ho.add(h);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String CoregirDato (  String[] row,int i ){
		String dato= row[i];
		String coregido= dato.substring(1,dato.length() -1);
		
		return coregido;
		
	}
	
	public ArrayList<Hoteles> cargarList(String provincia) {
		ArrayList<Hoteles> filtrado = new ArrayList<Hoteles>();
		for (int i = 0; i < ho.size(); i++) {
			String respro = ho.get(i).getProvincia().toString();
			if (respro.equals(provincia)) {
				filtrado.add(ho.get(i));
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
                    Hoteles h = (Hoteles) b.getSerializable("hoteles");
                   
                }  
            } else if (resultCode == 0) {
                System.out.println("RESULT CANCELLED");    
            }
        }
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hoteles, menu);
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
