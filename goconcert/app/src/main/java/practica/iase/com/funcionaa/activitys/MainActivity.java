package practica.iase.com.funcionaa.activitys;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import base.Concierto;
import base.Usuario;
import practica.iase.com.funcionaa.R;

public class MainActivity extends Activity implements View.OnClickListener{


    private final String URL_SERVIDOR = "http://192.168.42.221:8082";
    TextView etUsuario;
    TextView etContrasena;
        public static Usuario usuario = new Usuario();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvLinkRegistro = findViewById(R.id.linkRegistro);
        tvLinkRegistro.setOnClickListener(this);
        Button btIniciar = findViewById(R.id.btIniciarSesion);
        btIniciar.setOnClickListener(this);
        etUsuario = findViewById(R.id.etUsuario);
        etContrasena = findViewById(R.id.etContrasena);
        //TareaAsincrona tareaAsincrona = new TareaAsincrona(this);


    }



private void createNotificationChanel(Context context){

       if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "canal";
            String descripcion = "descripcon";
            int importancia = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel chanel = new NotificationChannel("chanel_1", name,importancia);
            chanel.setDescription(descripcion);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(chanel);
        }
}



public void terminado(Usuario usuario){
    if(usuario!= null) {
        /*Toast toast1 = Toast.makeText(getApplicationContext(), usuario.getNombre(),
                Toast.LENGTH_SHORT);
        toast1.show();*/
        MainActivity.usuario = usuario;
        if (etContrasena.getText().toString().equals(usuario.getContrasena())){
            Intent intent2 = new Intent(this, ListaConciertosActivity.class);
            startActivity(intent2);
        }else{
            Toast toast1 = Toast.makeText(getApplicationContext(), "error, contraseña incorrecta",
                    Toast.LENGTH_SHORT);
            toast1.show();
        }

    }else{
        Toast toast1 = Toast.makeText(getApplicationContext(), "error, usuario incorrecto",
                Toast.LENGTH_SHORT);
        toast1.show();
    }

}




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linkRegistro:

                Intent intent = new Intent(this, RegistroActivity.class);
                startActivity(intent);
                break;
            case R.id.btIniciarSesion:
                TareaAsincrona tareaAsincrona = new TareaAsincrona(this);
                tareaAsincrona.execute();

                /*Toast toast1 = Toast.makeText(getApplicationContext(), usuario.getNombre(),
                        Toast.LENGTH_SHORT);
                toast1.show();*/


                NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"")
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("notificacion")
                        .setContentText("texto")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);





/*
                Intent intent2 = new Intent(this, ListaConciertosActivity.class);
                startActivity(intent2);*/
                break;
        }


    }
    public class TareaAsincrona extends AsyncTask<Void,Void,Void>{
        Usuario usuario;
        private MainActivity actividad = new MainActivity();

        public TareaAsincrona(MainActivity mainActivity){
            this.actividad = mainActivity;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            usuario =restTemplate.getForObject("http://192.168.42.221:8082" + "/usuario_nombre?nombre="+ etUsuario.getText().toString()
                    , Usuario.class);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            //MainActivity.usuario = this.usuario;

            terminado(usuario);
        }
    }


}
