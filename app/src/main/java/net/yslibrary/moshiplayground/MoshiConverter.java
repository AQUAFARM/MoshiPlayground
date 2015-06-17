package net.yslibrary.moshiplayground;

import com.squareup.moshi.JsonReader;
import com.squareup.moshi.Moshi;
import com.squareup.okhttp.MediaType;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okio.Buffer;
import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

/**
 * Created by shimizu_yasuhiro on 15/06/17.
 */
public class MoshiConverter implements Converter {

    private final Moshi moshi;

    private final Charset charset;

    private final MediaType mediaType;

    public MoshiConverter() {
        this(new Moshi.Builder().build());
    }

    public MoshiConverter(Moshi moshi) {
        this(moshi, Charset.forName("UTF-8"));
    }

    public MoshiConverter(Moshi moshi, Charset charset) {
        if (moshi == null) {
            throw new NullPointerException("moshi == null");
        }
        if (charset == null) {
            throw new NullPointerException("charset == null");
        }

        this.moshi = moshi;
        this.charset = charset;
        this.mediaType = MediaType.parse("application/json; charset=" + charset.name());
    }

    @Override
    public Object fromBody(TypedInput body, Type type) throws ConversionException {

        InputStream is = null;
        Buffer buf = null;

        try {
            is = body.in();
            buf = new Buffer();
            buf = buf.readFrom(is);

            return moshi.adapter(type).fromJson(new JsonReader(buf));
        } catch (IOException e) {
            throw new ConversionException(e);

        } finally {
            try {
                if (buf != null) {
                    buf.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException ignored) {
                // ignore
            }
        }
    }

    @Override
    public TypedOutput toBody(Object object) {
        return null;
    }
}
