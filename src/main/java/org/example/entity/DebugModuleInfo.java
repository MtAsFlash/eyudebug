package org.example.entity;

import lombok.Data;

import java.util.List;

/**
 * @author mtasflash Created on 2022-09-27 23:18
 */
@Data
public class DebugModuleInfo {
    private String apiModuleName;
    private String apiModuleDescribe;
    private int apiModuleRank;
    private List<DebugMethodInfo> apiList;
}
