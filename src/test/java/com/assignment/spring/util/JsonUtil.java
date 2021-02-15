package com.assignment.spring.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.AutoCloseInputStream;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class JsonUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @SneakyThrows
    public static <T> T readFile(final String url, Class<T> clazz, final Object... args) {
        final InputStream inputStream = JsonUtil.class.getResourceAsStream(url);
        final String jsonContent = IOUtils.toString(new AutoCloseInputStream(inputStream), Charset.defaultCharset());
        final String formattedJson = String.format(jsonContent, Arrays.stream(args).map(JsonUtil::toStringJson).toArray());
        return OBJECT_MAPPER.readValue(formattedJson, clazz);
    }

    @SneakyThrows
    private static String toStringJson(final Object obj) {
        return OBJECT_MAPPER.writeValueAsString(obj);
    }

}
