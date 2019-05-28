package base;

import android.graphics.Bitmap;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class Festival {


    private long id;
    private String nombre;
    private Date fecha;
    private int nDias;
    private String hora_inicio;
    private int personas;
    private String nombreSala;
    private int precio;
    private String lugar;
    private String calle;
    private String ciudad;
    private List<Usuario> usuarios;
    private List<Concierto> conciertos;
    private transient Bitmap imagen;

    public Festival(){}



    public Festival(String nombre, Date fecha, String hora_inicio,
                    int personas, int precio, String lugar, String calle, String ciudad)
    {
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora_inicio = hora_inicio;
        this.personas = personas;
        this.precio = precio;
        this.lugar = lugar;
        this.calle = calle;
        this.ciudad = ciudad;

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getNombreSala() { return nombreSala; }

    public void setNombreSala(String nombreSala) { this.nombreSala = nombreSala; }

    public int getPersonas() {
        return personas;
    }

    public void setPersonas(int personas) {
        this.personas = personas;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public int getnDias() {
        return nDias;
    }

    public void setnDias(int nDias) {
        this.nDias = nDias;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Concierto> getConciertos() {
        return conciertos;
    }

    public void setConciertos(List<Concierto> conciertos) {
        this.conciertos = conciertos;
    }
}
