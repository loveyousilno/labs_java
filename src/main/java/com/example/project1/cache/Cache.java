package com.example.project1.cache;

import com.example.project1.models.PostCodes;
import com.example.project1.models.State;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class Cache {
    private final Map<Long, State> stateCache = new ConcurrentHashMap<>();

    public void putState(Long id, State state) {
        stateCache.put(id, state);
    }

    public State getState(Long id) {
        return stateCache.get(id);
    }

    public boolean containsState(Long id) {
        return stateCache.containsKey(id);
    }

    public void removeState(Long id) {
        stateCache.remove(id);
    }

    private final Map<Long, PostCodes> postCodesCache = new ConcurrentHashMap<>();

    public void putPostCodes(Long id, PostCodes postCodes) {
        postCodesCache.put(id, postCodes);
    }

    public PostCodes getPostCodes(Long id) {
        return postCodesCache.get(id);
    }

    public boolean containsPostCodes(Long id) {
        return postCodesCache.containsKey(id);
    }

    public void removePostCodes(Long id) {
        postCodesCache.remove(id);
    }
}

