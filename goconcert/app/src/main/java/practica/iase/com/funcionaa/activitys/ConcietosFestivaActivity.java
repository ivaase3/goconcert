package practica.iase.com.funcionaa.activitys;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import practica.iase.com.funcionaa.R;

public class ConcietosFestivaActivity extends Activity  implements AdapterView.OnItemSelectedListener {

    ArrayAdapter<String> diasAdapter;
    String[] dias ={"lunes","martes","miercoles"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concietos_festiva);
        diasAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, Arrays.asList(dias));
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(diasAdapter);
        spinner.setOnItemSelectedListener(this);

    }





    Spinner spiner;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String dia = dias[position];
        //TODO listar todos los conciertos de un dia

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
