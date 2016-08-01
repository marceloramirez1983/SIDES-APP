package android.marcelo_ramirez.sides.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by gonzalopro on 8/1/16.
 */
@Table(name = "asignar_usuario")
public class UserDB extends Model {

    @Column(name = "id_rol")
    public String id_rol;

    @Column(name = "id_ci")
    public String id_ci;

    @Column(name = "usuario_nombre")
    public String usuario_nombre;

    @Column(name = "usuario_password")
    public String usuario_password;

    public UserDB(){
        super();
    }

    public static List<UserDB> getAllUserDB() {
        return new Select()
                .from(UserDB.class)
                .execute();
    }

    public static UserDB logInUser(String username, String password) {
        return new Select()
                .from(UserDB.class)
                .where("usuario_nombre = ?", username)
                .and("usuario_password = ?", password)
                .executeSingle();
    }

}
