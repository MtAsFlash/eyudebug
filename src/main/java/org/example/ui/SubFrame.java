package org.example.ui;

import com.alibaba.fastjson.JSONObject;
import javafx.util.Pair;
import lombok.Getter;
import org.example.GlobalConfig;
import org.example.entity.DebugParamInfo;
import org.example.util.OkHttpUtil;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author mtasflash Created on 2022-09-27 23:34
 */
public class SubFrame extends JFrame {
    /**
     * 确定按钮
     */
    @Getter
    private final JButton submitButton;

    private final List<Pair<String, JTextField>> inputParams = new ArrayList<>();

    public SubFrame(String title, String debugModuleName, String debugMethodName, List<DebugParamInfo> paramInfoList) {
        super(title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        setMinimumSize(new Dimension(300, 600));
        setVisible(true);

        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(new GridLayout(0, 2));
        for (DebugParamInfo debugParamInfo : paramInfoList) {
            JLabel jLabel = new JLabel(debugParamInfo.getParamDescribe());
            jLabel.setFont(new Font("宋体", Font.PLAIN, 24));
            JTextField jTextField = new JTextField();
            panel.add(jLabel);
            panel.add(jTextField);
            inputParams.add(new Pair<>(debugParamInfo.getParamName(), jTextField));
        }
        submitButton = new JButton("Submit");
        this.add(submitButton);
        submitButton.addActionListener(e -> {
            try {
                Map<String, String> params = new HashMap<>();
                params.put("_api", debugModuleName + "." + debugMethodName);
                for (Pair<String, JTextField> inputParam : inputParams) {
                    params.put(inputParam.getKey(), inputParam.getValue().getText());
                }
                OkHttpUtil.getByRest(GlobalConfig.getInstance().getBaseUrl(), params);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
