package android.marcelo_ramirez.sides.util;

/**
 * Created by gonzalopro on 6/28/16.
 */
public class Constant {

    /*Marcelo Ramirez*/
    //public static final String _URL_GET_ALL_GROUP = "http://192.168.169.93:80/sides/controladores/retornarGruposApps.php";
    //public static final String _URL_GET_ALL_FAULT = "http://192.168.169.93:80/sides/controladores/retornarFaltasApps.php";

    /*Gonzalo Condori*/
    /*public static final String _URL_GET_ALL_GROUP = "http://192.168.0.107:8888/Sistema-disciplina/controladores/retornarGruposApps.php";
    public static final String _URL_GET_ALL_FAULT = "http://192.168.0.107:8888/Sistema-disciplina/controladores/retornarFaltasApps.php";
    public static final String _URL_SEND_SANCTION = "http://192.168.0.107:8888/Sistema-disciplina/controladores/insertarSancionApp.php";*/
    public static final String IP = "192.168.169.99";
    public static final String PORT = "8888";
    public static final String _URL_GET_ALL = "http://"+IP+":"+PORT+"/Sistema-disciplina/controladores/getAllFoulByGroup.php";

    /*192.168.1.36*/
    /*public static final String _URL_GET_ALL_GROUP = "http://192.168.1.36:8888/Sistema-disciplina/controladores/retornarGruposApps.php";
    public static final String _URL_GET_ALL_FAULT = "http://192.168.1.36:8888/Sistema-disciplina/controladores/retornarFaltasApps.php";
    public static final String _URL_POST_SANCTION = "http://192.168.1.36:8888/Sistema-disciplina/controladores/insertarSancionApp.php";*/

    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
}
