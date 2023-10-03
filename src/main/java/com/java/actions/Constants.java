package com.java.actions;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static final Map<String, Object> DEFAULT_VALUES = new HashMap<>();
    //TODO: seguir agregando tipos default
    static {
        DEFAULT_VALUES.put("STRING",  "\"\"");
        DEFAULT_VALUES.put("LONG",    0L);
        DEFAULT_VALUES.put("INT",     0);
        DEFAULT_VALUES.put("INTEGER", 0);
        DEFAULT_VALUES.put("DOUBLE",  0D);
        DEFAULT_VALUES.put("FLOAT",   "0.0F");
        DEFAULT_VALUES.put("MAP",     "Map.of()");
        DEFAULT_VALUES.put("SET",     "Set.of()");
        DEFAULT_VALUES.put("LIST",    "List.of()");
        DEFAULT_VALUES.put("BIGDECIMAL", "BigDecimal.valueOf(1000000000000D)");
        DEFAULT_VALUES.put("BIGINTEGER", "BigInteger.valueOf(20000000)");
    }

    public static final Map<String, String> DEFAULT_IMPORTS = new HashMap<>();
    static {
        DEFAULT_IMPORTS.put("BigDecimal","java.math.BigDecimal");
        DEFAULT_IMPORTS.put("BigInteger","java.math.BigInteger");
        DEFAULT_IMPORTS.put("Map","java.util.Map");
        DEFAULT_IMPORTS.put("Set","java.util.Set");
        DEFAULT_IMPORTS.put("List","java.util.List");
    }
    private Constants() {}
}
