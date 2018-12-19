package ivagonz.antroma.elespinar.cons_and_preferences;

import android.net.Uri;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Ivan on 20/02/2017.
 */

public class Constants {

    private static final String FB_PACKAGE = "com.facebook.katana";
    private static final String FB_APP_URI = "fb://profile/206937332679858";
    private static final String FB_WEB_URI = "https://www.facebook.com/OpenElEspinar";
    private static final String TW_PACKAGE = "com.twitter.android";
    private static final String TW_APP_URI = "twitter://user?user_id=145945805";
    private static final String TW_WEB_URI = "https://twitter.com/Tenis_Espinar";
    private static final String IG_PACKAGE = "com.instagram.android";
    private static final String IG_APP_URI = "http://instagram.com/_u/teniselespinar";
    private static final String IG_WEB_URI = "http://instagram.com/teniselespinar";
    private static final String YT_PACKAGE = "com.google.android.youtube";
    private static final String YT_APP_URI = "vnd.youtube:" + Uri.parse("https://www.youtube.com/watch?v=B5BPLWFeEJ8&t=5s").getQueryParameter("v");
    private static final String YT_WEB_URI = "https://www.youtube.com/channel/UC9pv4MQNV7QKOrJ_zW9Ds7A";
    private static final String TOURNAMENT_INFO = "http://www.elespinar.es/informacion-turistica/como-llegar.html";
    private static final String TOURNAMENT_BUS = "http://www.lasepulvedana.es/rutas-horarios.php";
    private static final String TOURNAMENT_TRAIN = "http://www.renfe.com/viajeros/index.html";

    private static final String ATP_SINGLE_URL = "http://tosoportfolio.esy.es/Atp/individual.pdf";
    private static final String ATP_DOUBLE_URL = "http://tosoportfolio.esy.es/Atp/dobles.pdf";
    private static final String ATP_PREVIOUS_URL = "http://tosoportfolio.esy.es/Atp/previa.pdf";
    private static final String ATP_GAME_ORDER_URL = "http://tosoportfolio.esy.es/Atp/orden.pdf";
    private static final String ITF_SINGLE_URL = "http://tosoportfolio.esy.es/Itf/individual.pdf";
    private static final String ITF_DOUBLE_URL = "http://tosoportfolio.esy.es/Itf/dobles.pdf";
    private static final String ITF_PREVIOUS_URL = "http://tosoportfolio.esy.es/Itf/previa.pdf";
    private static final String ITF_GAME_ORDER_URL = "http://tosoportfolio.esy.es/Itf/orden.pdf";
    private static ArrayList<File> files = new ArrayList<>();

    private static final String NEWS_TOURNAMENT_URL = "http://teniselespinar.com/category/torneo/";
    private static final String NEWS_SPORT_URL = "http://teniselespinar.com/category/deporte/";
    private static final String NEWS_COMMUNICATION_URL = "http://teniselespinar.com/category/comunicacion/";
    private static final String NEWS_DID_YOU_KNOW_URL = "http://teniselespinar.com/category/sabias-que/";

    public static String getFbPackage() {
        return FB_PACKAGE;
    }

    public static String getFbAppUri() {
        return FB_APP_URI;
    }

    public static String getFbWebUri() {
        return FB_WEB_URI;
    }

    public static String getTwPackage() {
        return TW_PACKAGE;
    }

    public static String getTwAppUri() {
        return TW_APP_URI;
    }

    public static String getTwWebUri() {
        return TW_WEB_URI;
    }

    public static String getIgPackage() {
        return IG_PACKAGE;
    }

    public static String getIgAppUri() {
        return IG_APP_URI;
    }

    public static String getIgWebUri() {
        return IG_WEB_URI;
    }

    public static String getYtPackage() {
        return YT_PACKAGE;
    }

    public static String getYtAppUri() {
        return YT_APP_URI;
    }

    public static String getYtWebUri() {
        return YT_WEB_URI;
    }

    public static String getTournamentInfo() {
        return TOURNAMENT_INFO;
    }

    public static String getTournamentBus() {
        return TOURNAMENT_BUS;
    }

    public static String getTournamentTrain() {
        return TOURNAMENT_TRAIN;
    }

    public static String getAtpSingleUrl() {
        return ATP_SINGLE_URL;
    }

    public static String getAtpDoubleUrl() {
        return ATP_DOUBLE_URL;
    }

    public static String getAtpPreviousUrl() {
        return ATP_PREVIOUS_URL;
    }

    public static String getAtpGameOrderUrl() {
        return ATP_GAME_ORDER_URL;
    }

    public static String getItfSingleUrl() {
        return ITF_SINGLE_URL;
    }

    public static String getItfDoubleUrl() {
        return ITF_DOUBLE_URL;
    }

    public static String getItfPreviousUrl() {
        return ITF_PREVIOUS_URL;
    }

    public static String getItfGameOrderUrl() {
        return ITF_GAME_ORDER_URL;
    }

    public static ArrayList<File> getFiles() {
        return files;
    }

    public static String getNewsTournamentUrl() {
        return NEWS_TOURNAMENT_URL;
    }

    public static String getNewsSportUrl() {
        return NEWS_SPORT_URL;
    }

    public static String getNewsCommunicationUrl() {
        return NEWS_COMMUNICATION_URL;
    }

    public static String getNewsDidYouKnowUrl() {
        return NEWS_DID_YOU_KNOW_URL;
    }
}
