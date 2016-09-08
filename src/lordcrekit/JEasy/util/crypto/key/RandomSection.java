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
package lordcrekit.util.crypto.key;

import java.util.Random;

/**
 *
 * @author William A. Norman (LordCrekit@gmail.com, normanwi@msu.edu)
 */
public class RandomSection implements KeySection {

    /*
     * ================================================ STATIC VARIABLES =================================================
     */
    public final static int DEFAULT_LENGTH = 16;
    public final static String DEFAULT_ALPHABET = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890";
    public final static String LOWERCASE_ALPHABET = "qwertyuiopasdfghjklzxcvbnm1234567890";

    /*
     * ================================================ MEMBER VARIABLES ================================================
     */
    private final Random mRNG;
    private final int mLength;
    private final String mAlphabet;

    /*
     * ================================================== CONSTRUCTORS ==================================================
     */
    /**
     * Constructs a new RandomSection.
     *
     * @param length
     * @param alphabet
     */
    public RandomSection( int length, String alphabet ) {
        mRNG = new Random();
        mLength = length;
        mAlphabet = alphabet;
    }

    /*
     * ================================================ PRIMARY FUNCTIONS ===============================================
     */
    @Override
    public StringBuilder generate(StringBuilder strb) {
        for ( int i = 0; i < mLength; i++ )
            strb.append(mAlphabet.charAt(mRNG.nextInt(mAlphabet.length())));
        return strb;
    }

    /*
     * =============================================== GETTERS AND SETTERS ==============================================
     */
    /*
     * ================================================ VISUAL FUNCTIONS ================================================
     */
    /*
     * ================================================ PRIVATE FUNCTIONS ===============================================
     */
}
