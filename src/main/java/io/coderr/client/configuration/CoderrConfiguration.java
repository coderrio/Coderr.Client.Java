package io.coderr.client.configuration;

import io.coderr.client.Err;
import io.coderr.client.ErrorDispatcher;
import io.coderr.client.InternalErrorListener;
import io.coderr.client.providers.ContextCollectionProvider;
import io.coderr.client.uploaders.CoderrUploader;
import io.coderr.client.uploaders.ReportUploader;
import sun.awt.util.ThreadGroupUtils;

import java.net.URL;
import java.util.ArrayList;

/**
 *
 */
public class CoderrConfiguration implements Thread.UncaughtExceptionHandler {
    private final ArrayList<ContextCollectionProvider> providers = new ArrayList<>();
    private final ArrayList<ReportUploader> uploaders = new ArrayList<>();
    private Thread.UncaughtExceptionHandler innerExceptionHandler;

    public void credentials(URL coderrServerUrl, String applicationKey, String sharedSecret){
        uploaders.add(new CoderrUploader(coderrServerUrl, applicationKey, sharedSecret));
    }

    public void addProvider(ContextCollectionProvider provider){
        if (provider == null)
            throw new IllegalArgumentException("Must specify 'provider'.");

        this.providers.add(provider);
    }

    public void addErrorListener(InternalErrorListener listener){
        ErrorDispatcher.Instance.addErrorListener(listener);
    }


    public void catchUnhandledExceptions(){
        innerExceptionHandler=  Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Err.report(e);
        if (this.innerExceptionHandler != null){
            this.innerExceptionHandler.uncaughtException(t, e);
        }
    }
}

