package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pojo.CreateBookingPOJO;
import pojo.CreateTokenPOJO;

import java.io.File;
import java.io.FileInputStream;

public class JSONPojoUtils {

    public static final String projectSrcDir = System.getProperty("user.dir");

    public static CreateTokenPOJO authToken(String fileName){
        ObjectMapper objectMapper = new ObjectMapper();
        String filePath = String.format("%s\\src\\test\\resources\\json\\%s".replace("\\", File.separator), projectSrcDir, fileName);
        File file = new File(filePath);
        CreateTokenPOJO ctp = new CreateTokenPOJO();
        try(FileInputStream fis = new FileInputStream(file)){
            ctp = objectMapper.readValue(fis, new TypeReference<CreateTokenPOJO>() {});
        }catch (Exception e){
        }
        return ctp;
    }


    public static CreateBookingPOJO createBokk(String fileName){
        ObjectMapper objectMapper = new ObjectMapper();
        String filePath = String.format("%s\\src\\test\\resources\\json\\%s".replace("\\", File.separator), projectSrcDir, fileName);
        File file = new File(filePath);
        CreateBookingPOJO ctp = new CreateBookingPOJO();
        try(FileInputStream fis = new FileInputStream(file)){
            ctp = objectMapper.readValue(fis, new TypeReference<CreateBookingPOJO>() {});
        }catch (Exception e){
        }
        return ctp;
    }
}
