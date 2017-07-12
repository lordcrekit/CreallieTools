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
package lordcrekit.JEasy.util.crypto.key;

/**
 *
 * @author William A. Norman (LordCrekit@gmail.com, normanwi@msu.edu)
 */
public class TimestampSection implements KeySection {

    /*
     * ================================================ MEMBER VARIABLES ================================================
     */
    private final int mLength;

    /*
     * ================================================== CONSTRUCTORS ==================================================
     */
    /**
     * Constructs a new TimestampSection.
     *
     * @param length
     */
    public TimestampSection( int length ) {
        mLength = length;
    }

    /*
     * ================================================ PRIMARY FUNCTIONS ===============================================
     */
    @Override
    public StringBuilder generate( StringBuilder strb ) {
        if ( Long.toHexString(mLength).length() > mLength )
            throw new RuntimeException("Length not enough!");
        else
            return strb.append(String.format("%1$" + mLength + "s", Long.toString(System.currentTimeMillis(), 36)).replace(' ', '0'));
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
