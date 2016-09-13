package com.example.jcyl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CSVAdapter extends ArrayAdapter<Restorauntes>{
	Context ctx;

	public CSVAdapter(Context context, int resource) {
		super(context, resource);
		this.ctx=context;
		
		loadArraylist();
	}
	
	
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		TextView mView= (TextView) convertView;
//		if (mView == null){
//			mView = new TextView(parent.getContext());
//			mView.setTextSize(28);
//		}
		
		mView.setText(getItem(position).getProvincia());
		
		
		
		return mView;
	}




	public void loadArraylist(){
		
		try {
			InputStream is= ctx.getAssets().open("restaurantes.csv");
			BufferedReader reader= new BufferedReader(new InputStreamReader(is));
			String line;
			
			
			while ((line= reader.readLine())!=null){
				String [] rowData= line.split(";");
								
				String nombre= rowData[2];
				String corNombre= nombre.substring(1,nombre.length()-1);
				
				Restorauntes r= new Restorauntes();
				r.setCategoria(rowData[1]);
				r.setNombre(corNombre);
				r.setLocalidad(rowData[7]);
				
				this.add(r);
				
				
			}	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
