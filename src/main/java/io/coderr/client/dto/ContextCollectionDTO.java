package io.coderr.client.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains a set of telemetry data.
 */
public class ContextCollectionDTO {

    /**
     * @param name       Name of this telemetry collection.
     * @param properties Data attached to the collection.
     */
    public ContextCollectionDTO(String name, Map<String, String> properties) {
        Name = name;
        Properties = new HashMap<>();
        Properties.putAll(properties);
    }

    public String Name;
    public Map<String, String> Properties;
}
