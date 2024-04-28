package net.taoziyoyo.qrnode.controller;

import net.taoziyoyo.qrnode.service.QrNodeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1")

public class QrNodeController {

    private final QrNodeService qrNodeService;
    public QrNodeController(QrNodeService qrNodeService) {
        this.qrNodeService = qrNodeService;
    }
    @GetMapping("/qrInfo")
    public String qrInfo(){
        return qrNodeService.qrNode();
    }
    @GetMapping("/readVlessContent")
    public Map<String,String> readVlessContent(){
        return qrNodeService.readVlessContent();
    }

    @GetMapping("/files")
    public List<String> fileList(){
        return qrNodeService.files();
    }

}