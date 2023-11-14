package tech.between.retotecnicomaxleon.infrastructure.adapter.Utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;
import tech.between.retotecnicomaxleon.infrastructure.adapter.exception.ValidateException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
public class Util<T> {
    public static final String DATE_FORMAT = "yyyy-MM-dd-HH.mm.ss";
    public static LocalDateTime fromStringToLocalDateTime(String stringDate) throws ValidateException {
        DateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setLenient(false);
        LocalDateTime dateParsed;

        try {
            dateParsed = LocalDateTime.parse(stringDate, DateTimeFormatter.ofPattern(DATE_FORMAT));
        } catch (DateTimeParseException e) {
            throw new ValidateException("Invalid date format: " + stringDate);
        }

        return dateParsed;
    }
    public static String fromLocalDateTimeToString(LocalDateTime localDateTime) throws ValidateException {
        try {
            return localDateTime.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        } catch (DateTimeException e) {
            throw new ValidateException("Error formatting LocalDateTime to String");
        }
    }
    public static <T> T convertBytesToResponse(byte[] bytes, Class<T> responseType) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = new String(bytes);
            return objectMapper.readValue(json, responseType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T convertFileToObject(File file, Class<T> valueType) throws IOException {
        return new Gson().fromJson(FileUtils.readFileToString(file, StandardCharsets.UTF_8), valueType);
    }

    public static <T> T readFile(String file, String folder, Class<T> className) throws IOException {
        return convertFileToObject(ResourceUtils.getFile("src/test/resources/" + folder +
                "/" + file + ".json"), className);
    }
}
