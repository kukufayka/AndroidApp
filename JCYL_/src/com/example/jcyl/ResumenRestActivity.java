package com.example.jcyl;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class ResumenRestActivity extends Activity {

	private Restorauntes r=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resumen_rest);
		
		Bundle b = getIntent().getExtras();
		if (b !=null){
			r = (Restorauntes) getIntent().getSerializableExtra("restaurante");
		}
		 TextView nombre= (TextView) findViewById(R.id.tv_datoNombre);
		 nombre.setText(r.getNombre());
		 
		 TextView direcc= (TextView) findViewById(R.id.tv_datoDireccion);
		 direcc.setText(r.getDireccion());
		 
		 TextView loca = (TextView) findViewById(R.id.tv_datoLocalidad);
		 loca.setText(r.getLocalidad());
		 
		 TextView telf = (TextView) findViewById(R.id.tv_datoTelefono);
		 telf.setText(r.getTelefono());
		 telf.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(Intent.ACTION_CALL);
					intent.setData(Uri.parse("tel:"+r.getTelefono()));
					startActivity(intent);
				}
			});
			 
		 
		 TextView email = (TextView) findViewById(R.id.tv_datoEmail);
		 email.setText(r.getEmail());
		 email.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
					emailIntent.setType("plain/text");
					emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,new String[] { r.getEmail()  });
					emailIntent.putExtra(android.content.Intent.EXTRA_CC,new String[] { });
					emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,new String[] { "Comentario Formulario"});
					emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,"Hola Pepito");

					startActivity(Intent.createChooser(emailIntent, "Enviar correo..."));
					
				}
			});
		 
		 TextView web = (TextView) findViewById(R.id.tv_datoWeb);
		 web.setText(r.getWeb());
		 web.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					 TextView web = (TextView) findViewById(R.id.tv_datoWeb);
					 web.setText(r.getWeb());
					Intent intent = new Intent(Intent.ACTION_VIEW);
					intent.setData(Uri.parse("http://"+r.getWeb()));
					startActivity(intent);
					
				}
			});
		 
		
		
		
		
		
		
		
		
	}
    
    
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.resumen_rest, menu);
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
