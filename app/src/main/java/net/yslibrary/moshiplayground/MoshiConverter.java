package net.yslibrary.moshiplayground;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;

import okio.Buffer;
import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;
import timber.log.Timber;

/**
 * Created by shimizu_yasuhiro on 15/06/17.
 */
public class MoshiConverter implements Converter {

    private final Moshi moshi;

    private final String charset;

    public MoshiConverter() {
        this(new Moshi.Builder().build());
    }

    public MoshiConverter(Moshi moshi) {
        this(moshi, "UTF-8");
    }

    public MoshiConverter(Moshi moshi, String charset) {
        if (moshi == null) {
            throw new NullPointerException("moshi == null");
        }
        if (charset == null) {
            throw new NullPointerException("charset == null");
        }

        this.moshi = moshi;
        this.charset = charset;
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
        Class clazz = object.getClass();

        JsonAdapter adapter = moshi.adapter(clazz);
        try {
            String result = adapter.toJson(object);
            Timber.d("result:%s", result);
            return new JsonTypedOutput(adapter.toJson(object).getBytes(charset), charset);
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    private static class JsonTypedOutput implements TypedOutput {

        private final byte[] jsonBytes;

        private final String mimeType;

        JsonTypedOutput(byte[] jsonBytes, String encode) {
            this.jsonBytes = jsonBytes;
            this.mimeType = "application/json; charset=" + encode;
        }

        @Override
        public String fileName() {
            return null;
        }

        @Override
        public String mimeType() {
            return mimeType;
        }

        @Override
        public long length() {
            return jsonBytes.length;
        }

        @Override
        public void writeTo(OutputStream out) throws IOException {
            out.write(jsonBytes);
        }
    }
}
