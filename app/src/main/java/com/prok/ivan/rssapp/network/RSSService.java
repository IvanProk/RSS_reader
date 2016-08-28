package com.prok.ivan.rssapp.network;

import android.app.Application;
import android.app.Notification;

import com.octo.android.robospice.SpiceService;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;
import com.octo.android.robospice.persistence.string.InFileStringObjectPersister;

public class RSSService extends SpiceService {
    @Override
    public CacheManager createCacheManager(Application application) throws CacheCreationException {
        CacheManager cacheManager = new CacheManager();
        InFileStringObjectPersister rssPersister = new InFileStringObjectPersister(application);
        cacheManager.addPersister(rssPersister);
        return cacheManager;
    }

    @Override
    public int getThreadCount() {
        return 3;
    }

    @Override
    public Notification createDefaultNotification() {
        return null;
    }
}
