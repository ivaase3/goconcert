package practica.iase.com.funcionaa.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import base.Usuario;
import practica.iase.com.funcionaa.R;

public class RegistroActivity extends Activity implements View.OnClickListener {
    TextView etUsuario;
    TextView etContrasena;
    TextView etCContrasena;
    public static Usuario usuario = new Usuario();
    Button btRegistrar;
    //TextView nombre = findViewById(R.id.etUsuario);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        btRegistrar= findViewById(R.id.btCrear);
        btRegistrar.setOnClickListener(this);

        etUsuario= findViewById(R.id.etUsuario);
        etContrasena= findViewById(R.id.etContrasena);
        etCContrasena = findViewById(R.id.etCContrasena);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btCrear) {

            if(!(etContrasena.getText().equals("")) && etContrasena.getText().toString().equals(etCContrasena.getText().toString())) {
                TareaAsincrona6 tareaAsincrona = new TareaAsincrona6(this);
                tareaAsincrona.execute();
            }
            else if(etContrasena.getText().equals("")){
                Toast toast1 = Toast.makeText(getApplicationContext(), "Introduzca una contraseña",
                        Toast.LENGTH_SHORT);
                toast1.show();
            }else{
                Toast toast1 = Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden",
                        Toast.LENGTH_SHORT);
            }
        }
    }

    public void terminado(Usuario usuario){
        RegistroActivity.usuario = usuario;
        Toast toast1 = Toast.makeText(getApplicationContext(), "Cuenta creada" + usuario.getNombre(),
                Toast.LENGTH_SHORT);
        toast1.show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        }

    public class TareaAsincrona6 extends AsyncTask<Void,Void,Void> {
        Usuario usuario;
        private RegistroActivity actividad = new RegistroActivity();

        public TareaAsincrona6(RegistroActivity mainActivity){
            this.actividad = mainActivity;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.getForObject("http://192.168.42.221:8082/addusuario?nombre=" +
                    etUsuario.getText().toString() + "&contrasena="
                    + etContrasena.getText().toString(), Void.class);
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
}
