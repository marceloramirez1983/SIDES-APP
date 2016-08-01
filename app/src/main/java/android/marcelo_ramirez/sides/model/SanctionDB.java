package android.marcelo_ramirez.sides.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by gonzalopro on 8/1/16.
 */
@Table(name = "sancion")
public class SanctionDB  extends Model {

    @Column(name = "ci_instructor")
    public String ci_instructor;

    @Column(name = "ci_alumno")
    public String ci_alumno;

    @Column(name = "id_falta")
    public String id_falta;

    @Column(name = "id_grupo")
    public String id_grupo;

    @Column(name = "puntos")
    public String puntos;

    @Column(name = "fecha")
    public String fecha;

    @Column(name = "status")
    public boolean status;

    public SanctionDB() {
        super();
    }

    public static List<SanctionDB> getAllSanctionByUser() {
        return new Select()
                .from(SanctionDB.class)
                .execute();
    }
}
