package org.example.ui;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import org.example.entity.DebugMethodInfo;
import org.example.entity.DebugModuleInfo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mtasflash Created on 2022-09-27 23:04
 */
public class MainFrame extends JFrame {

    @Getter
    private final List<DebugModuleInfo> debugModuleInfos = new ArrayList<>();

    public MainFrame(String title, String message) {
        super(title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        setMinimumSize(new Dimension(800, 800));
        setVisible(true);

        JSONObject result = (JSONObject) JSONObject.parseObject(message).get("result");
        String data = (String) result.get("data");
        debugModuleInfos.addAll(JSONObject.parseArray(data, DebugModuleInfo.class));
        initButton();
    }

    private void initButton() {
        for (DebugModuleInfo debugModuleInfo : debugModuleInfos) {
            this.add(new JLabel(debugModuleInfo.getApiModuleDescribe()));
            for (DebugMethodInfo debugMethodInfo : debugModuleInfo.getApiList()) {
                JButton button = new JButton(debugMethodInfo.getApiDescribe());
                this.add(button);
                button.addActionListener(e -> {
                    new SubFrame(debugMethodInfo.getApiDescribe(),
                            debugModuleInfo.getApiModuleName(),
                            debugMethodInfo.getApiName(),
                            debugMethodInfo.getApiParams());
                });
            }

        }
    }
}
