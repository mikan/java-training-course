/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch17.ex17_05;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.Map;

public class ResourceManager {

    final ReferenceQueue<Object> queue;
    final Map<Reference<?>, Resource> refs;
    boolean shutdown = false;

    public ResourceManager() {
        queue = new ReferenceQueue<Object>();
        refs = new HashMap<Reference<?>, Resource>();
        // Initialize resource
    }

    public synchronized void shutdown() {
        if (!shutdown) {
            shutdown = true;
            System.gc();
        }
    }

    public synchronized Resource getResource(Object key) {
        if (shutdown)
            throw new IllegalStateException();
        Resource res = new ResourceImpl(key);
        Reference<?> ref = new PhantomReference<Object>(key, queue);
        refs.put(ref, res);
        return res;
    }
}
