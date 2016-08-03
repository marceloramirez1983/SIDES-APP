package android.marcelo_ramirez.sides.util;

/**
 * Created by gonzalopro on 6/28/16.
 */
public class Constant {

    public static final String IP = "192.168.169.30";
    public static final String PORT = "8888";
    public static final String _URL_GET_ALL_FOUL = "http://"+IP+":"+PORT+"/Sistema-disciplina/controladores/getAllFoulByGroup.php";
    public static final String _URL_GET_ALL_USER = "http://"+IP+":"+PORT+"/Sistema-disciplina/controladores/getAllUsers.php";
    public static final String _URL_GET_ALL_PERSON = "http://"+IP+":"+PORT+"/Sistema-disciplina/controladores/getAllPerson.php";
    public static final String _URL_PUT_SANCTION = "http://"+IP+":"+PORT+"/Sistema-disciplina/controladores/insertarSancionApp.php";

    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
}
