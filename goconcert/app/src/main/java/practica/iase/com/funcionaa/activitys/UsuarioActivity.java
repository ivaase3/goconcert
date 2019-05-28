package practica.iase.com.funcionaa.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import base.Usuario;
import practica.iase.com.funcionaa.R;

public class UsuarioActivity extends Activity implements View.OnClickListener{

    TextView etUsuario;
    TextView etContrasena;
    TextView etCiudad;
    TextView etPais;
    TextView etCorreo;
    TextView etTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        Button btConciertos = findViewById(R.id.ibConciertos);
        btConciertos.setOnClickListener(this);
        Button btFestibales = findViewById(R.id.ibFestivales);
        btFestibales.setOnClickListener(this);
        Button btBuscar = findViewById(R.id.ibBuscar);
        btBuscar.setOnClickListener(this);
        Button btCuenta = findViewById(R.id.ibCuenta);
        btCuenta.setClickable(false);
        Button btEditar= findViewById(R.id.btEditar);
        btEditar.setOnClickListener(this);

         etUsuario = findViewById(R.id.tvNombre);
         etContrasena= findViewById(R.id.tvContrasena);
         etCiudad = findViewById(R.id.tvCiudad);
         etPais= findViewById(R.id.tvPais);
         etCorreo= findViewById(R.id.tvCorreo);
         etTelefono= findViewById(R.id.tvTelefono);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibConciertos:
                Intent intent2 = new Intent(this, ListaConciertosActivity.class);
                startActivity(intent2);
                break;
            case R.id.ibFestivales:
                Intent intent = new Intent(this, ListaFestivalesActivity.class);
                startActivity(intent);
                break;
            case R.id.ibBuscar:
                Intent intent3 = new Intent(this, BuscarActivity.class);
                startActivity(intent3);
                break;
            case R.id.btEditar:
                TareaAsincrona9 tareaAsincrona = new TareaAsincrona9(this);
                tareaAsincrona.execute();
                break;

        }
    }

    public class TareaAsincrona9 extends AsyncTask<Void,Void,Void> {
        Usuario usuario;
        private UsuarioActivity actividad = new UsuarioActivity();

        public TareaAsincrona9(UsuarioActivity mainActivity){
            this.actividad = mainActivity;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.getForObject("http://192.168.42.140:8082/addusuarioo?nombre=" +
                    etUsuario.getText().toString() + "&contrasena="
                    + etContrasena.getText().toString() + "&ciudad="
                    +  etCiudad.getText().toString()+"&pais"
                    + etPais.getText().toString() + "&correo"
                    + etCorreo.getText().toString()+"&telefono", Void.class);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            //MainActivity.usuario = this.usuario;
            usuario= new Usuario();
            usuario.setNombre(etUsuario.getText().toString());
            terminado(usuario);
        }
    }

    public void terminado(Usuario usuario){
        RegistroActivity.usuario = usuario;
        Toast toast1 = Toast.makeText(getApplicationContext(), "Cuenta guardada" + usuario.getNombre(),
                Toast.LENGTH_SHORT);
        toast1.show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
