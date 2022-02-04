/*
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.util;

import java.io.Closeable;
import java.util.function.Consumer;

/**
 * Base class for an autocloseable that produce a result once it has
 * been closed.
 *
 * @param <T> Type of the result
 */
public abstract class BaseResultProducingCloseable<T> implements AutoCloseable {

    private volatile boolean closed;

    protected final void ensureOpen() {
        if (closed) {
            throw new IllegalStateException("Listener closed");
        }
    }

    /**
     * Returns the result.
     *
     * @throws IllegalStateException If this instance has not been closed yet
     */
    public final T getResult() {
        if (!closed) {
            throw new IllegalStateException("Cannot get result before listener is closed");
        }

        return getResultImpl();
    }

    /** Produce the final result. */
    protected abstract T getResultImpl();

    /**
     * Close this object. Idempotent.
     *
     * @implNote Override {@link #closeImpl()} instead.
     */
    @Override
    public final void close() {
        if (!closed) {
            closed = true;
            closeImpl();
        }
    }

    /**
     * Close this closeable as per the contract of {@link Closeable#close()}.
     * Called exactly once. By default does nothing.
     */
    protected void closeImpl() {
        // override
    }

    public static <U, C extends BaseResultProducingCloseable<U>> U using(C closeable, Consumer<? super C> it) {
        try {
            it.accept(closeable);
        } finally {
            closeable.close();
        }
        return closeable.getResult();
    }
}
