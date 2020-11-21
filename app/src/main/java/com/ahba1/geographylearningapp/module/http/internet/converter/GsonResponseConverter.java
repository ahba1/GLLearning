package com.ahba1.geographylearningapp.module.http.internet.converter;

import android.util.Log;

import com.ahba1.geographylearningapp.module.http.internet.api.ApiResponse;
import com.ahba1.geographylearningapp.module.http.internet.exception.ErrorCode;
import com.ahba1.geographylearningapp.module.http.internet.exception.InputException;
import com.ahba1.geographylearningapp.module.http.internet.exception.TranslateObjectException;
import com.ahba1.geographylearningapp.module.http.internet.exception.UserNotExistException;
import com.ahba1.geographylearningapp.module.http.internet.utils.GsonUtil;
import com.google.gson.TypeAdapter;

import okhttp3.ResponseBody;
import retrofit2.Converter;



import java.io.IOException;


final class GsonResponseConverter<T> implements Converter<ResponseBody, Object> {

    private final TypeAdapter<T> adapter;

    GsonResponseConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override
    public Object convert(ResponseBody value) throws IOException {
        return solve(value, adapter);
    }

    /**
     * Static method to solve Error
     *
     * @param value   value from netWork
     * @return result translate by Gson
     */
    private static Object solve(ResponseBody value, TypeAdapter adapter) {
        ApiResponse result;

        try {
            String s = "";
            try {
                s = value.string();
                Log.v("GsonResponse",s);

                result = (ApiResponse) adapter.fromJson(s);

            } catch (Throwable throwable) {
                Log.e("GsonResponse", throwable.getMessage());
                throw new TranslateObjectException();
            }
            switch (result.code) {
                case ErrorCode.SUCCESS:
                    Log.v("GsonResponse", result.data.toString());
                    return result.data;
                case ErrorCode.INPUT_ILLEGAL:
                    throw new InputException(result.msg);
                case ErrorCode.USER_NOT_EXIST:
                    throw new UserNotExistException(result.msg);

            }
        } finally {
            value.close();
        }
        return null;
    }
}
