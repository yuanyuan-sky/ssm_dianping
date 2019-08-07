package study_ssm_dianping.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Create By yuanyuan on 2019/7/20 16:43
 */
public class FileUtil {

    public static String saveImg(MultipartFile multipartFile,String imgSavePath) throws IOException {
        String fileName = System.currentTimeMillis()
                + "_"
                + multipartFile.getOriginalFilename();
        File file = new File(imgSavePath
                + fileName);
        File fileFolder = new File(imgSavePath);
        if (!fileFolder.exists()) {
            fileFolder.mkdirs();
        }
        multipartFile.transferTo(file);

        return fileName;
    }

}
