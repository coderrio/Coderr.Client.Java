package io.coderr.client.providers;

import io.coderr.client.dto.ContextCollectionDTO;
import io.coderr.client.reporter.ErrorReporterContext;
import java.util.HashMap;

/**
 * Adds the java version to a collection called "Java" with the property "Version".
 */
public class JavaVersionProvider implements ContextCollectionProvider {


    @Override
    public void collect(ErrorReporterContext context) {
        String version = System.getProperty("java.version");
        if(version.startsWith("1.")) {
            version = version.substring(2, 3);
        }

        HashMap<String, String> properties = new HashMap<>();
        properties.put("Version", version);
        ContextCollectionDTO collection = new ContextCollectionDTO("Java", properties);
        context.addCollection(collection);
    }
}
