package base;

import android.graphics.Bitmap;
import android.icu.util.LocaleData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;


//local date fechas
//local time horas
//local date time fecha y hora
public class Concierto {

    private long id;
    private String nombre;
    private Date fecha;
    private String hora_inicio;
    private int personas;
    private int precio;
    private String nombreSala;
    private String lugar;
    private String calle;
    private String ciudad;
    private Festival festival;
    private List<Usuario> usuarios;
    private transient Bitmap imagen;


    public Concierto(){}

    //con DATE

    public Concierto(String nombre, Date fecha, String hora_inicio,
                     int personas, int precio, String nombreSala, String lugar, String calle, String ciudad)
    {
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora_inicio = hora_inicio;
        this.personas = personas;
        this.precio = precio;
        this.nombreSala = nombreSala;
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

    public String getNombreSala() { return nombreSala; }

    public void setNombreSala(String nombreSala) { this.nombreSala = nombreSala; }

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

    public Date getFecha() { return fecha; }

    public void setFecha(Date fecha) { this.fecha = fecha; }

    public String getHora_inicio() { return hora_inicio; }

    public void setHora_inicio(String hora_inicio) { this.hora_inicio = hora_inicio; }

    public Festival getFestival() {
        return festival;
    }

    public void setFestival(Festival festival) {
        this.festival = festival;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String getSFecha(Date fecha){
        String pattern = "dd/MM/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        String todayAsString = df.format(fecha);
        return todayAsString;
    }
//https://stackoverflow.com/questions/5683728/convert-java-util-date-to-string
    public String getDiaSemana(Date fecha){

        String[] array = (fecha.toString()).split(" ");
        String cad = array[0];

        return traduce(cad);
    }

    public String traduce(String dia) {
        String cad;
        switch (dia) {
            case "Mon":
                cad = "lunes";
                break;
            case "Tue":
                cad = "martes";
                break;
            case "Wed":
                cad = "miercoles";
                break;
            case "Thu":
                cad = "jueves";
                break;
            case "Fri":
                cad = "viernes";
                break;
            case "Sat":
                cad = "sabado";
                break;
            case "Sun":
                cad = "domingo";
                break;
            default:
                cad = "error";
                break;


        }
        return cad;
    }
}
