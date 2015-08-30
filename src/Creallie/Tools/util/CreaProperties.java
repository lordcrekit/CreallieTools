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

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * CreaProperties is a replacement for the {@link Properties } class. Thread safe. It can actually be used to quickly load, add to, and save files.
 *
 * @author William A Norman (Lordcrekit@gmail.com, normanwi@msu.edu)
 */
public class CreaProperties {

    /*
     * ================================================ MEMBER VARIABLES ================================================
     */
    private Map<String, String> mProperties;

    /*
     * ================================================== CONSTRUCTORS ==================================================
     */
    /**
     * Default constructor for CreaProperties.
     */
    public CreaProperties() {
        mProperties = Collections.synchronizedMap(new HashMap<>());
    }

    /**
     * Copy constructor for CreaProperties.
     *
     * @param orig <code>{@link CreaProperties }</code>: The object to copy.
     */
    public CreaProperties( CreaProperties orig ) {
        mProperties = new HashMap<>();
        for ( String key : orig.getProperties().keySet() )
            mProperties.put(key, orig.getProperties().get(key));
    }

    /**
     * Constructs a CreaProperties from a Properties object.
     *
     * @param props <code>{@link Properties }</code>: The Properties object to copy.
     */
    public CreaProperties( Properties props ) {
        mProperties = new HashMap<>();
        for ( Object key : props.keySet() )
            mProperties.put(key.toString(), props.get(key).toString());
    }

    /*
     * =============================================== GETTERS AND SETTERS ==============================================
     */
    /**
     * Gets a Key-Value Map of the properties.
     *
     * @return <code>{@link Map }&lt;{@link String }, {@link String }&gt;</code>: A key-value paired map.
     */
    public Map<String, String> getProperties() {
        System.out.println(this.toString());
        return mProperties;
    }

    /**
     * Gets the value associated with the given key from the properties, or null if the key does not exist.
     *
     * @param key
     * @return
     */
    public String getProperty( String key ) {
        return getProperties().get(key);
    }

    /**
     * Gets the value associated with the given key from the properties, or the given default value if the key does not exist.
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public String getProperty( String key, String defaultValue ) {
        return getProperties().getOrDefault(key, defaultValue);
    }

    /**
     * Sets the given key to the given value.
     *
     * @param key <code>{@link String }</code>: The key that is being set.
     * @param value <code>{@link String }</code>: The value being saved to the key.
     * @return <code>{@link CreaProperties }</code>: Pointer back to this object.
     */
    public CreaProperties setProperty( String key, String value ) {
        mProperties.put(key, value);
        return this;
    }

    /*
     * ================================================ UTILITY FUNCTIONS ===============================================
     */
    /**
     * Saves the properties to the given OutputStream. The file will also contain a timestamp of when it was saved.
     *
     * @param outstream <code>{@link OutputStream }</code>: The OutputStream write to.
     * @param comments  <code>{@link String }</code>: Comments to save with the file.
     * @return <code>{@link CreaProperties }</code>: Pointer back to this object.
     * @throws IOException If something goes wrong while attempting to save.
     */
    public CreaProperties save( OutputStream outstream, String comments ) throws IOException {
        save(new BufferedWriter(new OutputStreamWriter(outstream)), comments);
        return this;
    }

    /**
     * Saves the properties using the given Writer. The file will also contain a timestamp of when it was saved.
     *
     * @param writer   <code>{@link Writer }</code>: The Writer use.
     * @param comments <code>{@link String }</code>: Comments to save with the file.
     * @return <code>{@link CreaProperties }</code>: Pointer back to this object.
     * @throws IOException If something goes wrong while attempting to save.
     */
    public CreaProperties save( Writer writer, String comments ) throws IOException {
        save(new BufferedWriter(writer), comments);
        return this;
    }

    /**
     * Saves the properties to the given File. The file will also contain a timestamp of when it was saved.
     *
     * @param file     <code>{@link File }</code>: The file to save to.
     * @param comments <code>{@link String }</code>: Comments to save with the file.
     * @return <code>{@link CreaProperties }</code>: Pointer back to this object.
     * @throws IOException If something goes wrong while attempting to save.
     */
    public CreaProperties save( File file, String comments ) throws IOException {
        try ( BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file))) ) {
            save(writer, comments);
        }
        return this;
    }

    /**
     * Loads the properties from a given InputStream.
     *
     * @param instream <code>{@link InputStream }</code>: The InputStream to use.
     * @return <code>{@link CreaProperties }</code>: Pointer back to this object.
     * @throws IOException If something goes wrong while attempting to load.
     */
    public CreaProperties load( InputStream instream ) throws IOException {
        load(new BufferedReader(new InputStreamReader(instream)));
        return this;
    }

    /**
     * Loads the properties from a given Reader.
     *
     * @param reader <code>{@link Reader }</code>: The Reader to use.
     * @return <code>{@link CreaProperties }</code>: Pointer back to this object.
     * @throws IOException If something goes wrong while attempting to load.
     */
    public CreaProperties load( Reader reader ) throws IOException {
        load(new BufferedReader(reader));
        return this;
    }

    /**
     * Loads the properties from the given File.
     *
     * @param file <code>{@link File }</code>: The file to read from.
     * @return <code>{@link CreaProperties }</code>: Pointer back to this object.
     * @throws IOException If something goes wrong while attempting to load.
     */
    public CreaProperties load( File file ) throws IOException {
        try ( BufferedReader reader = new BufferedReader(new FileReader(file)) ) {
            load(reader);
        }
        return this;
    }

    /*
     * ================================================ VISUAL FUNCTIONS ================================================
     */
    /**
     * Just a String representation of the internal Map of properties.
     *
     * @return <code>{@link String }</code>: Representation of this object.
     */
    @Override
    public String toString() {
        return mProperties.toString();
    }

    /*
     * ================================================ PRIVATE FUNCTIONS ===============================================
     */
    /**
     * Saves...
     *
     * @param writer
     * @param comments
     * @throws IOException
     */
    private void save( BufferedWriter writer, String comments ) throws IOException {
        if ( comments != null && !comments.isEmpty() )
            for ( String i : comments.split("\n") )
                writer.write('#' + i + "\n");
        writer.write(new SimpleDateFormat("'#Created on 'yyyy-MM-dd'T'HH.mm.ss'GMT'Z'\n'").format(new Date()));
        for ( String key : mProperties.keySet() )
            writer.write(key + "=" + mProperties.get(key) + "\n");
        writer.flush();
    }

    /**
     * Loads...
     *
     * @param reader
     * @throws IOException
     */
    private void load( BufferedReader reader ) throws IOException {
        mProperties = Collections.synchronizedMap(new HashMap<>());
        while ( reader.ready() ) {
            String no_comments = reader.readLine().split("#")[0];
            int eqind = no_comments.indexOf('=');
            if ( eqind != -1 )
                mProperties.put(no_comments.substring(0, eqind), no_comments.length() > eqind ? no_comments.substring(eqind + 1, no_comments.length()) : null);
        }
    }
}
