package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

public class CachingProxyRetriever implements Retriever {
    private LRUCache lruCache = new LRUCache<Long, Object>(50);
    private OriginalRetriever originalRetriever;

    public CachingProxyRetriever(Storage storage) {
        originalRetriever = new OriginalRetriever(storage);
    }

    @Override
    public Object retrieve(long id) {
        Object chacheFind = lruCache.find(id);
        if(chacheFind == null){
            chacheFind = originalRetriever.retrieve(id);
            lruCache.set(id, chacheFind);
        }
        return chacheFind;
    }
}
