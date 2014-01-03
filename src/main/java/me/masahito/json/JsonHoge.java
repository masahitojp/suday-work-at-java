package me.masahito.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import me.masahito.json.pojo.MyValue;

import java.io.IOException;


public class JsonHoge {

    public static void main(String[] args) throws IOException {
        final JsonHoge json = new JsonHoge();

        // JSON(オブジェクト)
        String jsondata = "{\"name\":\"あいうえお\",\"age\":2}";
        MyValue result = json.toPOJO(jsondata, MyValue.class);
        System.out.println("result1.toString() : " + new String(json.toJSONByteArray(result)));


    }

    final ObjectMapper mapper;
    public JsonHoge() {
        // Jacksonのマッパーを生成。
        this.mapper = new ObjectMapper();
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }
    public <T> T toPOJO(final String jsondata, final Class<T> typeParameterClass) throws IOException {
        return this.mapper.readValue(jsondata, typeParameterClass);
    }

    public String toJSONString(Object obj) throws JsonProcessingException {
        return this.mapper.writeValueAsString(obj);
    }

    public byte[] toJSONByteArray(Object obj) throws JsonProcessingException {
        return this.mapper.writeValueAsBytes(obj);
    }
}
