package android.marcelo_ramirez.sides.model;

/**
 * Created by gonzalopro on 8/1/16.
 */
public class User {

    String id_usuario, id_rol, id_ci, usuario_nombre, usuario_password;

    public User(){}

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setId_rol(String id_rol) {
        this.id_rol = id_rol;
    }

    public void setId_ci(String id_ci) {
        this.id_ci = id_ci;
    }

    public void setUsuario_nombre(String usuario_nombre) {
        this.usuario_nombre = usuario_nombre;
    }

    public void setUsuario_password(String usuario_password) {
        this.usuario_password = usuario_password;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public String getId_rol() {
        return id_rol;
    }

    public String getId_ci() {
        return id_ci;
    }

    public String getUsuario_nombre() {
        return usuario_nombre;
    }

    public String getUsuario_password() {
        return usuario_password;
    }
}
