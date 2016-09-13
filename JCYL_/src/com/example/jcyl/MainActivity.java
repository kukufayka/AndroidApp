package com.example.jcyl;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Messenger;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //Intent intent = new Intent(this, DownloadService1.class);      
        //intent.setData(Uri.parse("http://www.datosabiertos.jcyl.es/web/jcyl/risp/es/directorio/restaurantes/1284211839594.csv"));
        //intent.putExtra("urlpath", "http://www.datosabiertos.jcyl.es/web/jcyl/risp/es/directorio/restaurantes/1284211839594.csv");
        
        //Intent intent2 = new Intent(this.getApplicationContext(), DownloadService.class);   
        //intent2.setData(Uri.parse("http://www.datosabiertos.jcyl.es/web/jcyl/risp/es/directorio/alojamientos_hoteleros/1284211831639.csv"));
        //intent2.putExtra("urlpath", "http://www.datosabiertos.jcyl.es/web/jcyl/risp/es/directorio/alojamientos_hoteleros/1284211831639.csv");
        
        //startService(intent);
        //startService(intent2);

        
        Button res= (Button) findViewById(R.id.bu_rest);
        Button hot= (Button) findViewById(R.id.bu_hotel);
        
        res.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent= new Intent(MainActivity.this.getApplicationContext(),ActivityRestaurantes.class);
				startActivity(intent);	
			}
		});
        hot.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				Intent intent= new Intent(MainActivity.this.getApplicationContext(),ActivityHoteles.class);
				startActivity(intent);	
			}
		});		
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
