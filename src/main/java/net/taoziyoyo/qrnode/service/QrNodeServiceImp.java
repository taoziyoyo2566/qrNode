package net.taoziyoyo.qrnode.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QrNodeServiceImp implements QrNodeService {

    @Value("${custom.file.path}")
    private String filePath;
    @Override
    public Map<String, String> readVlessContent() {

        String vlessContent = findVlessContent(filePath);

        Map<String, String> result = new HashMap<>();
        if (vlessContent != null) {
            result.put("status", "success");
            result.put("vlessContent", vlessContent);
        } else {
            result.put("status", "error");
            result.put("message", "No line containing 'vless:' found");
        }

        return result;
    }

    public List<String> files() {
        return fileNameList(filePath);
    }

    private ArrayList<String> fileNameList(String filePath) {
        ArrayList<String> list = new ArrayList<>();
        File file = new File(filePath);
        File[] tsFile = file.listFiles();
        File[] fsFile = file.listFiles();
        if (tsFile != null && fsFile != null) {
            for (int i = 0; i < fsFile.length; i++) {
                if (tsFile[0].isFile()) {
                    list.add(tsFile[i].toString());
                }
            }
        }
        return list;
    }

    private String findVlessContent(String filePath) {
        String fileName = filePath.concat("config_info.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("vless:")) {
                    return line.substring(line.indexOf("vless:")).trim();
                }
            }
        } catch (IOException ioException) {
            System.out.printf(ioException.toString());
        }
        return null;
    }

    @Override
    public String qrNode() {
        return findVlessContent(filePath);

    }


}
