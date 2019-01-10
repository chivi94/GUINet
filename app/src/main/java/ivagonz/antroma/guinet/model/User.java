package ivagonz.antroma.guinet.model;

/**
 * Clase que identifica a un usuario dentro del sistema.
 */
public class User {

    private int id;
    private String cargo;
    private String alias;
    private String nombre;
    private String apellido;
    private String dni;
    private String tlf;
    private String email;
    private String carrera;

    public User(int id, String nombre, String apellido, String alias) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.alias = alias;
    }

    public User(int id, String cargo, String alias, String nombre, String apellido, String dni, String tlf, String email, String carrera) {
        this.id = id;
        this.cargo = cargo;
        this.alias = alias;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.tlf = tlf;
        this.email = email;
        this.carrera = carrera;
    }

    public int getId() {
        return id;
    }

    public String getCargo() {
        return cargo;
    }

    public String getAlias() {
        return alias;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public String getTlf() {
        return tlf;
    }

    public String getEmail() {
        return email;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
}
