package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import base.Concierto;
import practica.iase.com.funcionaa.R;

public class ConciertoAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private int idLayout;
    private List<Concierto> conciertos;

    public ConciertoAdapter(Context contexto, int idLayout,
                         List<Concierto> conciertos) {

        inflater = LayoutInflater.from(contexto);
        this.idLayout = idLayout;
        this.conciertos = conciertos;
    }


static class ViewHolder {
    TextView tvNombre;
    TextView tvSemana;
    TextView tvFecha;
    TextView tvSala;
    TextView tvLugar;
    TextView tvCiudad;
    TextView tvPrecio;
    TextView tvPersonas;
    ImageView ivImagen;
}

    @Override
    public View getView(int posicion, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {
            convertView = inflater.inflate(idLayout, null);

            holder = new ViewHolder();

            holder.tvNombre = convertView.findViewById(R.id.lbNombre);
            holder.tvSemana = convertView.findViewById(R.id.lbsemana);
            holder.tvFecha = convertView.findViewById(R.id.lbDia);
            holder.tvSala = convertView.findViewById(R.id.lbNombreSala);
            holder.tvLugar = convertView.findViewById(R.id.lbLugar);
            holder.tvCiudad = convertView.findViewById(R.id.lbCiudad);
            holder.tvPrecio = convertView.findViewById(R.id.lbPrecio);
            holder.tvPersonas = convertView.findViewById(R.id.lbPersonas);
            holder.ivImagen = convertView.findViewById(R.id.ivImagen);

            convertView.setTag(holder);
        }
        else {

            holder = (ViewHolder) convertView.getTag();
        }

        Concierto concierto = conciertos.get(posicion);

        Date fecha = concierto.getFecha();
        String semana = traduce(fecha.getDay());
        int dia = fecha.getDate();
        int mes = fecha.getMonth()+1;
        int anio = fecha.getYear()+1900;

        holder.tvNombre.setText("grupo: "+concierto.getNombre());
        holder.tvFecha.setText("dia: "+anio+"-"+mes+"-"+dia);
        holder.tvSemana.setText(semana);
        holder.tvSala.setText("Sala: "+concierto.getNombreSala());
        holder.tvLugar.setText("Lugar: "+concierto.getLugar());
        holder.tvCiudad.setText("ciudad: "+concierto.getCiudad());
        holder.tvPrecio.setText("Precio "+Integer.toString(concierto.getPrecio())+"€");
        holder.tvPersonas.setText("Personas: "+Integer.toString(concierto.getPersonas()));
        //holder.ivImagen.setImageBitmap();

        return convertView;
    }

    public String traduce(int dia) {
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
    @Override
    public int getCount() {
        return conciertos.size();
    }

    @Override
    public Object getItem(int posicion) {
        return conciertos.get(posicion);
    }

    @Override
    public long getItemId(int posicion) {
        return conciertos.get(posicion).getId();
    }


}
