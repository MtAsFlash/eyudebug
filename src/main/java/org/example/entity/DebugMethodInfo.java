package org.example.entity;

import lombok.Data;

import java.util.List;

/**
 * @author mtasflash Created on 2022-09-27 23:21
 */
@Data
public class DebugMethodInfo {
    private String apiName;
    private String apiDescribe;
    private int apiMethodRank;
    private boolean gmNeedDiy;
    private List<DebugParamInfo> apiParams;
}
