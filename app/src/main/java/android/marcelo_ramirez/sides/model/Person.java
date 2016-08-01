package android.marcelo_ramirez.sides.model;

/**
 * Created by gonzalopro on 8/1/16.
 */
public class Person {

    String id_ci, codigo_secreto;

    public Person(){}

    public void setId_ci(String id_ci) {
        this.id_ci = id_ci;
    }

    public void setCodigo_secreto(String codigo_secreto) {
        this.codigo_secreto = codigo_secreto;
    }

    public String getId_ci() {
        return id_ci;
    }

    public String getCodigo_secreto() {
        return codigo_secreto;
    }
}
