package com.example;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

@Configuration
public class GsonConfigure {
    public static final String STANDARD_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    
    public static final String STANDARD_DATE_PATTERN = "yyyy-MM-dd";

    @Bean
    public static GsonBuilder gsonBuilder() {

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class, new DateSerializer());
        builder.registerTypeAdapter(Date.class, new DateDeserializer());
        return builder;
    }
    
    
    public static Gson  gson( ){
    	return new Gson();
    }

    final static class DateSerializer implements JsonSerializer<Date> {

        public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(new SimpleDateFormat(STANDARD_DATETIME_PATTERN).format(src));
        }
    }

    final static class DateDeserializer implements JsonDeserializer<Date> {
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                return new SimpleDateFormat(STANDARD_DATETIME_PATTERN).parse(json.getAsJsonPrimitive().getAsString());
            } catch (ParseException e) {
                throw new JsonParseException(e);
            }
        }
    }
}
