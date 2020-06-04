package org.catplayer.sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * sort algorithms enum
 *
 * @author catplayer
 * @since 1.0
 */
public enum SorterAlgorithms {

    SELECTION("selection"),

    INSERTION("insertion"),

    SHELL("shell"),

    MERGE("merge"),

    QUICK("quick"),

    HEAP("heap");

    private final String name;

    private static final Map<String, SorterAlgorithms> NAME_PAIR = new HashMap<>();

    static {
        //load all sort algorithms
        Arrays.stream(values()).forEach(sortAlgorithms -> NAME_PAIR.put(sortAlgorithms.name, sortAlgorithms));
    }

    SorterAlgorithms(String name) {
        this.name = name;
    }

    public static SorterAlgorithms get(String name) {
        return NAME_PAIR.get(name);
    }
}
