package android.marcelo_ramirez.sides.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by gonzalopro on 8/1/16.
 */

@Table(name = "grupo")
public class GroupDB extends Model {

    @Column(name = "id_grupo")
    public String id_grupo;

    @Column(name = "grupo")
    public String grupo;

    @Column(name = "puntos")
    public String puntos;

    @Column(name = "id_falta")
    public String id_falta;

    @Column(name = "nombre")
    public String nombre;

    public GroupDB() {
        super();
    }

    public static List<GroupDB> getAllFoul() {
        return new Select()
                .from(GroupDB.class)
                .execute();
    }

}
