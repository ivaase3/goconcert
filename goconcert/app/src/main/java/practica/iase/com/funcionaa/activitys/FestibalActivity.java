package practica.iase.com.funcionaa.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import base.Concierto;
import base.Festival;
import practica.iase.com.funcionaa.R;

public class FestibalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festibal);
    }

    private long idFestival;

    Festival festival = (Festival) getIntent().getSerializableExtra("concierto");

    private void rellenarDatos(Festival festival) {
        TextView lbNombre = findViewById(R.id.lbNombre);
        TextView lbSemana= findViewById(R.id.lbSemana);
        TextView lbDia = findViewById(R.id.lbDia);
        TextView lbHora = findViewById(R.id.lbHora);
        TextView lbPersonas = findViewById(R.id.lbPersonas);
        TextView lbPrecio = findViewById(R.id.lbPrecio);
//        ImageButton ibImagen = findViewById(R.id.ibImagen);

        lbNombre.setText(festival.getNombre());
//        lbSemana.setText(concierto.getDiaSemana());
//        lbDia.setText(concierto.getFecha());
//        lbHora.setText(festival.getHora_inicio());
        lbPersonas.setText(festival.getPersonas());
        lbPrecio.setText(festival.getPrecio());
//        ibImagen.setImageBitmap(imagenEvento);

        idFestival = festival.getId();
    }
}
