package cn.bipher.hexagrams.jackson.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.io.Serializable;

/**
 * jackson NULL值序列化为 ""
 *
 * @author Bipher
 * @version 1
 * @date 2023/7/26 11:24
 */
public class NullStringJsonSerializer extends JsonSerializer<Object> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
		jsonGenerator.writeString("");
	}

}
