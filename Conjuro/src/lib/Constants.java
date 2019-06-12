package lib;

public interface Constants {

     //---------------INTS---------------------//
    public static int PORT_NUMBER = 10000;
    public static int STATE_MATRIZ_LEN = 4;
    public static int LIMIT_RANDOM = 10000;
    public static int THREAD_SLEEP_TIME = 50;
    public static int CANT_SAVE = 100;
    public static int CANT_DATA_PLAY = 3;
    public static int MAX_DATA_PLAY = 99;
    public static int MIN_DATA_PLAY = 0;
    public static int DATA_CERO = 0;
    public static int DATA_ONE = 1;
    public static int DATA_TWO = 2;
    public static int TOTAL_CARDS = 7;
    public static int JUGADA_NUMBER = 3;
    public static int LENGTH_KEY = 64;
    public static int RSA_LENGTH_KEY = 1024;
    public static int MAX_KEYS = 100;
    
    //---------------STRINGS---------------------//
    public static String MESSAGE_SEPARATOR = ",";
    public static String MESSAGE_VALUES_SEPARATOR = "=";
    public static String LIMIT_ENCRYPTATION_AES = "X--X";
    public static String LIMIT_ENCRYPTATION_SHA256 = "X-Y-X";
    public static String LIMIT_FILE = "X-X-X";
    public static String LIMIT_DESCRIPTION = "-FinText-";
    public static String LIMIT_NAME = "-FinName-";
    public static String HOST = "172.18.210.67";
    public static String PATH_KEY = "\\C++\\Save.txt";
    public static String PATH_PLAY_DATA = "\\C++\\DataPlay.txt";
}