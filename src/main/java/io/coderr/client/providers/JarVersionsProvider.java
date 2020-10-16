package io.coderr.client.providers;

import io.coderr.client.ErrorDispatcher;
import io.coderr.client.dto.ContextCollectionDTO;
import io.coderr.client.reporter.ErrorReporterContext;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class JarVersionsProvider implements ContextCollectionProvider{
    @Override
    public void collect(ErrorReporterContext context) {
        HashMap<String,String> properties = new HashMap<>();

        Enumeration resEnum;
        try {
            resEnum = Thread.currentThread().getContextClassLoader().getResources(JarFile.MANIFEST_NAME);
            while (resEnum.hasMoreElements()) {
                URL url = null;
                try {
                     url = (URL)resEnum.nextElement();
                    InputStream is = url.openStream();
                    if (is != null) {
                        Manifest manifest = new Manifest(is);
                        Attributes mainAttribs = manifest.getMainAttributes();
                        String version = mainAttribs.getValue("Implementation-Version");
                        String library = mainAttribs.getValue("Implementation-Title");
                        if(version != null && library != null) {
                            properties.put(library, version);
                        }
                    }
                }
                catch (Exception e) {
                    ErrorDispatcher.Instance.publish(this, e, "Failed to load " + url);
                }
            }
        } catch (IOException e1) {
            ErrorDispatcher.Instance.publish(this, e1, "Failed to retrieve manifests.");
        }

        ContextCollectionDTO collection = new ContextCollectionDTO("JarVersions", properties);
        context.addCollection(collection);
    }
}
