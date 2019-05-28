package practica.iase.com.funcionaa.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adapters.ConciertoAdapter;
import base.Concierto;
import base.Usuario;
import practica.iase.com.funcionaa.R;

public class BuscarActivity extends Activity implements View.OnClickListener{

    public static ArrayList<Concierto> conciertos = new ArrayList<>();
    ListView lvBusqueda;
    Button btBuscaNombre;
    Button btBuscaFecha ;
    TextView etBusqueda ;
    public static boolean tipo;
    private ConciertoAdapter adapter;
    Button btBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        tipo =false;

        Button btConciertos = findViewById(R.id.ibConciertos);
        btConciertos.setOnClickListener(this);
        Button btFestibales = findViewById(R.id.ibFestivales);
        btFestibales.setOnClickListener(this);
        Button btBuscar = findViewById(R.id.ibBuscar);
        btBuscar.setClickable(false);
        Button btCuenta = findViewById(R.id.ibCuenta);
        btCuenta.setOnClickListener(this);

        btBuscaNombre= findViewById(R.id.btBuscaNombre);
        btBuscaFecha = findViewById(R.id.btBuscaFecha);
        etBusqueda= findViewById(R.id.etBusqueda);
        btBuscaNombre.setClickable(false);
        btBuscaNombre.setOnClickListener(this);
        btBuscaFecha.setOnClickListener(this);
        btBuscar = findViewById(R.id.btBuscar);
        btBuscar.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.ibFestivales:
                Intent intent = new Intent(this, ListaFestivalesActivity.class);
                startActivity(intent);
                break;
            case R.id.ibConciertos:
                Intent intent2 = new Intent(this, ListaConciertosActivity.class);
                startActivity(intent2);
                break;
            case R.id.ibCuenta:
                Intent intent3 = new Intent(this, UsuarioActivity.class);
                startActivity(intent3);
                break;
            case R.id.btBuscaFecha:
                btBuscaNombre.setClickable(true);
                btBuscaFecha.setClickable(false);
                tipo = true;
                Toast toast1 = Toast.makeText(getApplicationContext(),
                        "Ahora esta buscando por fecha aaaa-mm-dd",
                        Toast.LENGTH_SHORT);
                toast1.show();
                break;
            case R.id.btBuscaNombre:
                btBuscaFecha.setClickable(true);
                btBuscaNombre.setClickable(false);
                tipo = false;
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Ahora esta buscando por nombre ",
                        Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.btBuscar:


                TareaAsincrona4 tareaAsincrona = new TareaAsincrona4(this);
                tareaAsincrona.execute();

        }
    }

    public void terminado(ArrayList<Concierto> conciertos){
        BuscarActivity.conciertos = conciertos;
        if(conciertos.size()==0){
            Toast toast = Toast.makeText(getApplicationContext(), "sin resultados",
                    Toast.LENGTH_SHORT);
            toast.show();
        }


        lvBusqueda = findViewById(R.id.lvBusqueda);
        adapter = new ConciertoAdapter(this,
                R.layout.item_concierto2, conciertos);
        lvBusqueda.setAdapter(adapter);


        lvBusqueda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent1 = new Intent(BuscarActivity.this, ConciertoActivity.class);
                startActivity(intent1);
            }
        });
        registerForContextMenu(lvBusqueda);

        adapter.notifyDataSetChanged();
    }
    public class TareaAsincrona4 extends AsyncTask<Void,Void,Void> {
        List<Concierto> conciertos;
        private BuscarActivity actividad = new BuscarActivity();

        public TareaAsincrona4(BuscarActivity activity){
            this.actividad = activity;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            if(!tipo){
                Concierto[] arrayConciertos =restTemplate.getForObject("http://192.168.42.140:8082"
                                + "/conciertos_nombre?nombre="+ etBusqueda.getText().toString()
                        , Concierto[].class);
                conciertos = Arrays.asList(arrayConciertos);

            }else{
                Concierto[] arrayConciertos =restTemplate.getForObject("http://192.168.42.140:8082"
                                + "/conciertos_fecha?fechac="+ etBusqueda.getText().toString()
                        , Concierto[].class);
                conciertos = Arrays.asList(arrayConciertos);


            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            ArrayList<Concierto> conciertos2 = new ArrayList<>();
            if(conciertos!=null){
            for(Concierto concierto : conciertos){
                conciertos2.add(concierto);
            }}
            terminado(conciertos2);
        }
    }
}
