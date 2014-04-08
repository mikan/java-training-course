/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch17.ex17_03;

import java.lang.ref.WeakReference;

public class ResourceImpl implements Resource {
    private WeakReference<Object> keyRef;
    boolean needsRelease = false;

    ResourceImpl(Object key) {
        keyRef = new WeakReference<Object>(key);
        // Set resource
        needsRelease = true;
    }

    @Override
    public void use(Object key, Object... args) {
        if (!keyRef.get().equals(key))
            throw new IllegalArgumentException("wrong key");
        // Use resource
    }

    @Override
    public synchronized void release() {
        if (needsRelease) {
            needsRelease = false;
            // Release resource
        }
    }
}
