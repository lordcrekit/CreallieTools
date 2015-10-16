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
package Creallie.Tools.util;

/**
 * Because {@link Tuple } just wasn't good enough.
 *
 * @author William A. Norman (LordCrekit@gmail.com, normanwi@msu.edu)
 * @param <T>
 * @param <G>
 * @param <U>
 */
public final class Triple<T, G, U> {

    /*
     * ================================================ MEMBER VARIABLES ================================================
     */
    private T mFirst;
    private G mSecond;
    private U mThird;

    /*
     * ================================================== CONSTRUCTORS ==================================================
     */
    /**
     * Constructs a new, empty Triple.
     */
    public Triple() {
        this.mFirst = null;
        this.mSecond = null;
        this.mThird = null;
    }

    /**
     * Constructs a new Triple.
     *
     * @param first
     * @param second
     * @param third
     */
    public Triple( T first, G second, U third ) {
        this.mFirst = first;
        this.mSecond = second;
        this.mThird = third;
    }

    /*
     * =============================================== GETTERS AND SETTERS ==============================================
     */
    /**
     *
     * @return
     */
    public T getFirst() {
        return this.mFirst;
    }

    /**
     *
     * @param first
     * @return
     */
    public Triple<T, G, U> setFirst( T first ) {
        this.mFirst = first;
        return this;
    }

    /**
     *
     * @return
     */
    public G getSecond() {
        return this.mSecond;
    }

    /**
     *
     * @param second
     * @return
     */
    public Triple<T, G, U> setSecond( G second ) {
        this.mSecond = second;
        return this;
    }

    /**
     *
     * @return
     */
    public U getThird() {
        return this.mThird;
    }

    /**
     *
     * @param third
     * @return
     */
    public Triple<T, G, U> setThird( U third ) {
        this.mThird = third;
        return this;
    }

    /*
     * ================================================ VISUAL FUNCTIONS ================================================
     */
    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
