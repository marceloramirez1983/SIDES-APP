package android.marcelo_ramirez.sides.util;

/**
 * Created by gonzalopro on 6/28/16.
 */
public class Constant {

    public static final String IP = "192.168.169.65";
    public static final String PORT = "8888";
    public static final String ROOT = "Sistema-disciplina";
    public static final String _URL_GET_ALL_FOUL = "http://"+IP+":"+PORT+"/"+ROOT+"/controladores/getAllFoulByGroup.php";
    public static final String _URL_GET_ALL_USER = "http://"+IP+":"+PORT+"/"+ROOT+"/controladores/getAllUsers.php";
    public static final String _URL_GET_ALL_PERSON = "http://"+IP+":"+PORT+"/"+ROOT+"/controladores/getAllPerson.php";
    public static final String _URL_PUT_SANCTION = "http://"+IP+":"+PORT+"/"+ROOT+"/controladores/insertarSancionApp.php";

    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
}
