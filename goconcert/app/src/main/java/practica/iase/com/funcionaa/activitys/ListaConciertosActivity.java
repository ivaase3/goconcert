package practica.iase.com.funcionaa.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
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
import base.Concierto;
import base.Usuario;
import practica.iase.com.funcionaa.R;

public class ListaConciertosActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    public static ArrayList<Concierto> conciertos = new ArrayList<>();
    private ConciertoAdapter adapter;





    ListView lvConciertos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_conciertos);




        Button btConciertos = findViewById(R.id.ibConciertos);
        btConciertos.setClickable(false);
        Button btFestibales = findViewById(R.id.ibFestivales);
        btFestibales.setOnClickListener(this);
        Button btBuscar = findViewById(R.id.ibBuscar);
        btBuscar.setOnClickListener(this);
        Button btCuenta = findViewById(R.id.ibCuenta);
        btCuenta.setOnClickListener(this);

        TareaAsincrona2 tareaAsincrona = new TareaAsincrona2(this);
        tareaAsincrona.execute();

    }

    @Override
    protected void  onResume() {
        super.onResume();

        //refrescarLista();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.ibFestivales:
                Intent intent = new Intent(this, ListaFestivalesActivity.class);
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





    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ConciertoActivity.class);
        Concierto concierto = (Concierto) lvConciertos.getItemAtPosition(position);
        intent.putExtra("concierto", (Parcelable) concierto);

        startActivity(intent);


    }
    public void terminado(final ArrayList<Concierto> conciertos){
        ListaConciertosActivity.conciertos = conciertos;

        lvConciertos = findViewById(R.id.lvConciertos);
        adapter = new ConciertoAdapter  (this,
                R.layout.item_concierto2, conciertos);
        lvConciertos.setAdapter(adapter);


        lvConciertos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                Intent intent1 = new Intent(ListaConciertosActivity.this, ConciertoActivity.class);
                intent1.putExtra("nombre",conciertos.get(posicion).getNombre());
                intent1.putExtra("personas",conciertos.get(posicion).getPersonas());
                intent1.putExtra("dia",conciertos.get(posicion).getFecha().toString());
                intent1.putExtra("precio",conciertos.get(posicion).getPrecio());
                startActivity(intent1);
            }
        });
        registerForContextMenu(lvConciertos);

        adapter.notifyDataSetChanged();
    }

    public class TareaAsincrona2 extends AsyncTask<Void,Void,Void> {
         List<Concierto> conciertos;
        private ListaConciertosActivity actividad = new ListaConciertosActivity();

        public TareaAsincrona2(ListaConciertosActivity activity){
            this.actividad = activity;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
             Concierto[] arrayConciertos=restTemplate.getForObject("http://192.168.42.140:8082"
                     + "/conciertos", Concierto[].class);
             conciertos = Arrays.asList(arrayConciertos);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            //MainActivity.usuario = this.usuario;

            ArrayList<Concierto> conciertos2 = new ArrayList<>();
            for(Concierto concierto : conciertos){
                conciertos2.add(concierto);
            }
            terminado((ArrayList<Concierto>) conciertos2);
        }
    }
}
