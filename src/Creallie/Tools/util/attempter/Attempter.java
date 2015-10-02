/*
 * The MIT License
 *
 * Copyright 2015 William A. Norman (LordCrekit@gmail.com, normanwi@msu.edu).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package Creallie.Tools.util.attempter;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * The Attempter class is a convenience class to assist with running blocks of code with no guarantee of completion.
 *
 * @author William A. Norman (LordCrekit@gmail.com, normanwi@msu.edu)
 */
public class Attempter {

    /*
     * ================================================ PRIMARY FUNCTIONS ===============================================
     */
    /**
     * Attempts to run the given Callable with the set number of attempts before it gives up.
     *
     * @param <T> <code>T</code>: The return type of your Callable.
     * @param callable <code>{@link Callable }</code>: The Callable that will be run.
     * @param attempts <code>int</code>: The number of times to attempt.
     * @return <code>T</code>: Whatever your Callable returns.
     * @throws AllAttemptsFailedException If every attempt is failed.
     */
    public static <T> T attemptMultiple( Callable<T> callable, int attempts ) throws AllAttemptsFailedException {
        Exception lastEx = null;
        for ( int counter = 0; counter < attempts; counter++ )
            try {
                return callable.call();
            } catch ( RuntimeException ex ) {
                lastEx = ex;
            } catch ( Exception ex ) {
                lastEx = ex;
            }
        throw new AllAttemptsFailedException("All attempts failed.", lastEx);
    }

    /**
     * Attempts to run the given Callable within the set timeout time before it gives up. Always uses milliseconds.
     *
     * @param <T> <code>T</code>: The return type of your Callable.
     * @param callable <code>{@link Callable }</code>: The Callable that will be run.
     * @param timeout <code>long</code>: The number of milliseconds to wait for.
     * @param cancelThread <code>boolean</code>: If the thread should be shut down on failure.
     * @return
     * @throws TimeoutException
     * @throws ExecutionException
     */
    public static <T> T attemptTimeout( Callable<T> callable, long timeout, boolean cancelThread ) throws TimeoutException, ExecutionException {
        return attemptTimeout(callable, timeout, TimeUnit.MILLISECONDS, cancelThread);
    }

    /**
     * Attempts to run the given Callable within the set timeout time before it gives up. Allows you to specify the time unit being used.
     *
     * @param <T> <code>T</code>: The return type of your Callable.
     * @param callable <code>{@link Callable }</code>: The Callable that will be run.
     * @param timeout <code>long</code>: The number of specified TimeUnits to wait for.
     * @param timeUnit <code>{@link TimeUnit }</code>: The TimeUnit that is being used.
     * @param cancelThread <code>boolean</code>: If the thread should be shut down on failure.
     * @return
     * @throws TimeoutException
     * @throws ExecutionException
     */
    public static <T> T attemptTimeout( Callable<T> callable, long timeout, TimeUnit timeUnit, boolean cancelThread ) throws TimeoutException, ExecutionException {
        try {
            final ExecutorService executor = Executors.newSingleThreadExecutor();
            final Future<T> future = executor.submit(callable);
            try {
                return future.get(timeout, timeUnit);
            } catch ( TimeoutException e ) {
                future.cancel(cancelThread);
                throw e;
            } finally {
                executor.shutdown();
            }
        } catch ( InterruptedException ex ) {
            throw new ExecutionException("Interrupted.", ex);
        }
    }
}
