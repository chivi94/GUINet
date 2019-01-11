package ivagonz.antroma.guinet.model;

/**
 * Clase que identifica a un usuario dentro del sistema.
 */
public class User {

    //"alias":"antroma","name":"Antonio","lastname":"Román","dni":"12346164v","email":"antonio.roman@alumnos.uva.es","phone":"686587434","grade":"Ingeniería Informatica"

    private int id;
    private String cargo;
    private String alias;
    private String name;
    private String lastname;
    private String dni;
    private String phone;
    private String email;
    private String grade;

    public User(int id, String name, String lastname, String alias,String dni, String email) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.alias = alias;
        this.dni = dni;
        this.email = email;
    }

    public User(int id, String cargo, String alias, String name, String lastname, String dni, String phone, String email, String grade) {
        this.id = id;
        this.cargo = cargo;
        this.alias = alias;
        this.name = name;
        this.lastname = lastname;
        this.dni = dni;
        this.phone = phone;
        this.email = email;
        this.grade = grade;
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

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getDni() {
        return dni;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getGrade() {
        return grade;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setTlf(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCarrera(String grade) {
        this.grade = grade;
    }
}
