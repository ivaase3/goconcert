package practica.iase.com.funcionaa.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import adapters.ConciertoAdapter;
import adapters.FestivalAdapter;
import base.Concierto;
import base.Festival;
import practica.iase.com.funcionaa.R;

public class ListaFestivalesActivity extends Activity implements View.OnClickListener{

    public static ArrayList<Festival> festibales = new ArrayList<>();
    private FestivalAdapter adapter;

    ListView lvFestivales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_festivales);






        Button btConciertos = findViewById(R.id.ibConciertos);
        btConciertos.setOnClickListener(this);
        Button btFestibales = findViewById(R.id.ibFestivales);
        btFestibales.setClickable(false);
        Button btBuscar = findViewById(R.id.ibBuscar);
        btBuscar.setOnClickListener(this);
        Button btCuenta = findViewById(R.id.ibCuenta);
        btCuenta.setOnClickListener(this);

        TareaAsincrona3 tareaAsincrona = new TareaAsincrona3(this);
        tareaAsincrona.execute();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.ibConciertos:
                Intent intent = new Intent(this, ListaConciertosActivity.class);
                startActivity(intent);
                break;
            case R.id.ibBuscar:
                Intent intent2 = new Intent(this, BuscarActivity.class);
                startActivity(intent2);
                break;
            case R.id.ibCuenta:
                Intent intent3 = new Intent(this, UsuarioActivity.class);
                startActivity(intent3);
                break;

        }
    }

    public void terminado(ArrayList<Festival> festibales){
        ListaFestivalesActivity.festibales = festibales;

        lvFestivales = findViewById(R.id.lvFestivales);
        adapter = new FestivalAdapter(this,
                R.layout.item_festival, festibales);
        lvFestivales.setAdapter(adapter);


        lvFestivales.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent1 = new Intent(ListaFestivalesActivity.this, FestibalActivity.class);
                startActivity(intent1);
            }
        });
        registerForContextMenu(lvFestivales);

        adapter.notifyDataSetChanged();
    }


    public class TareaAsincrona3 extends AsyncTask<Void,Void,Void> {
        List<Festival> festivales;
        private ListaFestivalesActivity actividad = new ListaFestivalesActivity();

        public TareaAsincrona3(ListaFestivalesActivity activity){
            this.actividad = activity;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Festival[] arrayFestivales=restTemplate.getForObject("http://192.168.42.140:8082"
                    + "/festivales", Festival[].class);
            festivales = Arrays.asList(arrayFestivales);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){


            ArrayList<Festival> festivales2 = new ArrayList<>();
            for(Festival festival : festivales){
                festivales2.add(festival);
            }
            terminado((ArrayList<Festival>) festivales2);
        }
    }
}
