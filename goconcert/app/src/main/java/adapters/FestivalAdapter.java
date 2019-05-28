package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import base.Festival;
import practica.iase.com.funcionaa.R;

public class FestivalAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private int idLayout;
    private List<Festival> festivales;

    public FestivalAdapter(Context contexto, int idLayout,
                            List<Festival> festivales) {

        inflater = LayoutInflater.from(contexto);
        this.idLayout = idLayout;
        this.festivales = festivales;
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

        ConciertoAdapter.ViewHolder holder = null;

        if (convertView == null) {
            convertView = inflater.inflate(idLayout, null);

            holder = new ConciertoAdapter.ViewHolder();

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

            holder = (ConciertoAdapter.ViewHolder) convertView.getTag();
        }

        Festival festival = festivales.get(posicion);

        holder.tvNombre.setText(festival.getNombre());
        //TODO holder.tvDescripcion.setText(concierto.get());
        //TODO holder.tvNombre.setText(concierto.getFecha());
        holder.tvSemana.setText("semana");
        holder.tvFecha.setText("fecha");
       holder.tvSala.setText(festival.getNombreSala());
        holder.tvLugar.setText(festival.getLugar());
        holder.tvCiudad.setText(festival.getCiudad());
        holder.tvPrecio.setText(Integer.toString(festival.getPrecio()));
        holder.ivImagen.setImageBitmap(festival.getImagen());

        return convertView;
    }


    @Override
    public int getCount() {
        return festivales.size();
    }

    @Override
    public Object getItem(int posicion) {
        return festivales.get(posicion);
    }

    @Override
    public long getItemId(int posicion) {
        return festivales.get(posicion).getId();
    }

}
