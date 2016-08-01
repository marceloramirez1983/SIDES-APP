package android.marcelo_ramirez.sides.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

/**
 * Created by gonzalopro on 8/1/16.
 */
@Table(name = "persona")
public class PersonDB extends Model {

    @Column(name = "ci_persona")
    public String ci_persona;

    @Column(name = "code_persona")
    public String code_persona;

    public PersonDB() {
        super();
    }

    public static PersonDB verifyCredential(String ci, String code) {
        return new Select()
                .from(PersonDB.class)
                .where("ci_persona = ?", ci)
                .and("code_persona = ?", code)
                .executeSingle();
    }

}
