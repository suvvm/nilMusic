package top.suvvm.nilmusic.http;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public abstract class HttpClient {
    public static OkHttpClient client = new OkHttpClient();
    public static final MediaType HTTPJSON = MediaType.get("application/json; charset=utf-8");

    public static final String REGISTER_URL = "http://www.suvvm.work:6789/nilmusic/user/register";
    public static final String LOGIN_URL = "http://www.suvvm.work:6789/nilmusic/user/login";

    public static final String ALL_ALBUM_URL = "http://www.suvvm.work:6789/nilmusic/album/all?uid=%d";
    public static final String CREATE_ALBUM_URL = "http://www.suvvm.work:6789/nilmusic/album/create";
    public static final String DELETE_ALBUM_URL = "http://www.suvvm.work:6789/nilmusic/album/delete";

    public static final String ALL_MUSIC_URL = "http://www.suvvm.work:6789/nilmusic/album/music?aid=%s";
    public static final String ALBUM_ADD_MUSIC_URL = "http://www.suvvm.work:6789/nilmusic/album/music/add";
    public static final String MDF_MUSIC_URL = "http://www.suvvm.work:6789/nilmusic/album/music/mdf";
    public static final String DELETE_MUSIC_URL = "http://www.suvvm.work:6789/nilmusic/album/music/delete";

    public static final Integer HandlerSuccess	= 300000;
    public static final Integer HandlerReadBodyErr	= 300010;
    public static final Integer HandlerReadPathErr	= 300011;
    public static final Integer HandlerDBInsertErr	= 300022;
    public static final Integer HandlerDBSelectErr	= 300023;
    public static final Integer HandlerDBUpdateErr	= 300024;
    public static final Integer HandlerDBDeleteErr	= 300025;
    public static final Integer HandlerPasswordErr	= 300036;
}
