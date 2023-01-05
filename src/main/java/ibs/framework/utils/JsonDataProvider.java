package ibs.framework.utils;

import ibs.framework.models.ConfigData;

public class JsonDataProvider {
    private static final String CONFIG_PATH = "./src/main/resources/frameworkConfig.json";

    public static final ConfigData configData = JsonUtil.deserializeFile(CONFIG_PATH,ConfigData.class);
}
