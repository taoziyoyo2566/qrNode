package net.taoziyoyo.qrnode.service;

import java.util.List;
import java.util.Map;

public interface QrNodeService {
    public String qrNode();
    public List<String> files();
    public Map<String, String> readVlessContent();
}
