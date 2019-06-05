package practica.iase.com.funcionaa.activitys;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import adapters.ConciertoAdapter;
import base.Concierto;
import base.Festival;
import base.Usuario;
import practica.iase.com.funcionaa.R;

public class ConciertoActivity extends Activity  implements View.OnClickListener{

    CheckBox chbFav;
    TextView lbNombre;
    TextView lbSemana;
    TextView lbDia;
    TextView lbHora;
    TextView lbPersonas;
    TextView lbPrecio;
    static boolean interes;
    public static Concierto concierto = new Concierto();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concierto);



        Button btCompra = findViewById((R.id.btCompra));
        btCompra.setOnClickListener(this);


        lbNombre= findViewById(R.id.lbNombre);
        lbSemana= findViewById(R.id.lbSemana);
        lbDia = findViewById(R.id.lbDia);
        lbHora = findViewById(R.id.lbHora);
        lbPersonas = findViewById(R.id.lbPersonas);
        lbPrecio = findViewById(R.id.lbPrecio);
        chbFav=findViewById(R.id.chbInteres);
        chbFav.setOnClickListener(this);
       TareaAsincrona8 tareaAsincrona = new TareaAsincrona8(this);
        tareaAsincrona.execute();

    }





    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btCompra:
                Toast toast1 = Toast.makeText(getApplicationContext(), "Gracias por realizar su compra",
                        Toast.LENGTH_SHORT);

                toast1.show();
                break;
                case R.id.chbInteres:
                    if(!interes){

                    }





        }
    }


    public String traduceSemana(int dia) {
        String cad;
        switch (dia) {
            case 1:
                cad = "lunes";
                break;
            case 2:
                cad = "martes";
                break;
            case 3:
                cad = "miercoles";
                break;
            case 4:
                cad = "jueves";
                break;
            case 5:
                cad = "viernes";
                break;
            case 6:
                cad = "sabado";
                break;
            default:
                cad = "domingo";
                break;



        }
        return cad;
    }

    public String traduceMes(String mes) {
        String cad;
        switch (mes) {
            case "Jan":
                cad = "1";
                break;
            case "Feb":
                cad = "2";
                break;
            case "Mar":
                cad = "3";
                break;
            case "Apr":
                cad = "4";
                break;
            case "May":
                cad = "5";
                break;
            case "Jun":
                cad = "6";
                break;
            case "Jul":
                cad = "7";
                break;
            case "Aug":
                cad = "8";
                break;
            case "Sep":
                cad = "9";
                break;
            case "Oct":
                cad = "10";
                break;
            case "Nov":
                cad = "11";
                break;
            case "Dic":
                cad = "12";
                break;
            default:
                cad = "error";
                break;
        }
        return cad;
    }

    public class TareaAsincrona8 extends AsyncTask<Void,Void,Void> {
        Concierto concierto;
        List<Concierto> conciertos;
        private ConciertoActivity actividad = new ConciertoActivity();

        public TareaAsincrona8(ConciertoActivity activity){
            this.actividad = activity;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Concierto[] conciertoArray=restTemplate.getForObject("http://192.168.42.221:8082/conciertos_nombre?nombre="+"acdc" , Concierto[].class);

            conciertos = Arrays.asList(conciertoArray);
            concierto = conciertos.get(0);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            //MainActivity.usuario = this.usuario;


            terminado(concierto);
        }
    }
    public void terminado(final Concierto concierto){
        if(concierto!= null) {

            Date fecha = concierto.getFecha();
            String semana = traduceSemana(fecha.getDay());
            int dia = fecha.getDate();
            int mes = fecha.getMonth()+1;
            int anio = fecha.getYear()+1900;

            ConciertoActivity.concierto = concierto;
               lbNombre.setText(concierto.getNombre());
               lbSemana.setText(semana);
               lbDia.setText("dia: "+anio+"-"+mes+"-"+dia);
               lbHora.setText(concierto.getHora_inicio());
               lbPersonas.setText(Integer.toString(concierto.getPersonas()));
               lbPrecio.setText(Float.toString(concierto.getPrecio()));

        }else{
            Toast toast1 = Toast.makeText(getApplicationContext(), "error, dato incorrecto",
                    Toast.LENGTH_SHORT);
            toast1.show();
        }
    }

}
