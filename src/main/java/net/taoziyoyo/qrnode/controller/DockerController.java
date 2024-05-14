package net.taoziyoyo.qrnode.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DockerController {

    @GetMapping("/read-config")
    // public String readDockerConfig(@RequestParam String instanceName) {
    public String readDockerConfig(@RequestParam("instanceName") String instanceName) {
    
        try {
            instanceName="vless_reality_0422";
            List<String> command = new ArrayList<>();
            command.add("docker");
            command.add("exec");
            // command.add("-it");
            command.add(instanceName);
            command.add("cat");
            command.add("config_info.txt");
            // command.add("ls -al ~/");
            System.out.println("Executing command: " + command);
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();
        
            // 处理标准输出
            Thread outputThread = new Thread(() -> {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println("STDOUT: " + line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        
            // 处理标准错误
            Thread errorThread = new Thread(() -> {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println("STDERR: " + line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        
            // 启动两个线程来处理输出和错误
            outputThread.start();
            errorThread.start();
        
            // 等待命令执行完成
            int exitCode = process.waitFor();
            outputThread.join();  // 确保输出线程完成
            errorThread.join();   // 确保错误线程完成
        
            if (exitCode == 0) {
                return "Command executed successfully";
            } else {
                return "Command execution failed with exit code: " + exitCode;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error executing command";
        }
        
    }
}
