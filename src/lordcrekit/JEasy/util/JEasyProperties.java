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
package lordcrekit.JEasy.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * JEasyProperties is a replacement for the {@link Properties } class. Thread safe. It can actually be used to quickly load, add
 * to, and save files.
 *
 * @author William A Norman (Lordcrekit@gmail.com, normanwi@msu.edu)
 */
public class JEasyProperties {

    /*
     * ================================================ MEMBER VARIABLES ================================================
     */
    private Map<String, String> properties;

    /*
     * ================================================== CONSTRUCTORS ==================================================
     */
    /**
     * Default constructor for CreaProperties.
     */
    public JEasyProperties() {
        this.properties = Collections.synchronizedMap(new HashMap<>());
    }

    /**
     * Copy constructor for CreaProperties.
     *
     * @param orig The object to copy.
     */
    public JEasyProperties( JEasyProperties orig ) {
        this.properties = new HashMap<>();
        for ( String key : orig.getProperties().keySet() )
            this.properties.put(key, orig.getProperties().get(key));
    }

    /**
     * Constructs a CreaProperties from a Properties object.
     *
     * @param props The Properties object to copy.
     */
    public JEasyProperties( Properties props ) {
        this.properties = new HashMap<>();
        for ( Object key : props.keySet() )
            this.properties.put(key.toString(), props.get(key).toString());
    }

    /*
     * =============================================== GETTERS AND SETTERS ==============================================
     */
    /**
     * Gets a Key-Value Map of the properties.
     *
     * @return A key-value paired map.
     */
    public Map<String, String> getProperties() {
        return this.properties;
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
     * @param key   The key that is being set.
     * @param value The value being saved to the key.
     * @return Pointer back to this object.
     */
    public JEasyProperties setProperty( String key, String value ) {
        this.properties.put(key, value);
        return this;
    }

    /*
     * ================================================ UTILITY FUNCTIONS ===============================================
     */
    /**
     * Saves the properties to the given OutputStream. The file will also contain a timestamp of when it was saved.
     *
     * @param outstream The OutputStream write to.
     * @param comments  Comments to save with the file.
     * @return Pointer back to this object.
     * @throws IOException If something goes wrong while attempting to save.
     */
    public JEasyProperties save( OutputStream outstream, String comments ) throws IOException {
        save(new BufferedWriter(new OutputStreamWriter(outstream)), comments);
        return this;
    }

    /**
     * Saves the properties using the given Writer. The file will also contain a timestamp of when it was saved.
     *
     * @param writer   The Writer use.
     * @param comments Comments to save with the file.
     * @return Pointer back to this object.
     * @throws IOException If something goes wrong while attempting to save.
     */
    public JEasyProperties save( Writer writer, String comments ) throws IOException {
        save(new BufferedWriter(writer), comments);
        return this;
    }

    /**
     * Saves the properties to the given File. The file will also contain a timestamp of when it was saved.
     *
     * @param file     The file to save to.
     * @param comments Comments to save with the file.
     * @return Pointer back to this object.
     * @throws IOException If something goes wrong while attempting to save.
     */
    @Deprecated
    public JEasyProperties save( File file, String comments ) throws IOException {
        try ( BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file))) ) {
            save(writer, comments);
        }
        return this;
    }

    /**
     * Saves the properties to the given Path. File created will contain timestamp.
     *
     * @param path     Path to the file to save to.
     * @param comments Comments that will be saved with the file.
     * @return Pointer back to this object.
     * @throws IOException If something goes wrong while attempting to save.
     */
    public JEasyProperties save( Path path, String comments ) throws IOException {
        try ( BufferedWriter writer = Files.newBufferedWriter(path) ) {
            save(writer, comments);
        }
        return this;
    }

    /**
     * Loads the properties from a given InputStream.
     *
     * @param instream The InputStream to use.
     * @return Pointer back to this object.
     * @throws IOException If something goes wrong while attempting to load.
     */
    public JEasyProperties load( InputStream instream ) throws IOException {
        load(new BufferedReader(new InputStreamReader(instream)));
        return this;
    }

    /**
     * Loads the properties from a given Reader.
     *
     * @param reader The Reader to use.
     * @return Pointer back to this object.
     * @throws IOException If something goes wrong while attempting to load.
     */
    public JEasyProperties load( Reader reader ) throws IOException {
        load(new BufferedReader(reader));
        return this;
    }

    /**
     * Loads the properties from the given File.
     *
     * @param file The file to read from.
     * @return Pointer back to this object.
     * @throws IOException If something goes wrong while attempting to load.
     */
    @Deprecated
    public JEasyProperties load( File file ) throws IOException {
        try ( BufferedReader reader = new BufferedReader(new FileReader(file)) ) {
            load(reader);
        }
        return this;
    }

    /**
     * Loads the properties from the given filepath.
     *
     * @param path Path to the file to read from.
     * @return Pointer back to this object.
     * @throws IOException If something goes wrong while attempting to read the file.
     */
    public JEasyProperties load( Path path ) throws IOException {
        try ( BufferedReader reader = Files.newBufferedReader(path) ) {
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
     * @return Representation of this object.
     */
    @Override
    public String toString() {
        return this.properties.toString();
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
        for ( String key : this.properties.keySet() )
            writer.write(key + "=" + this.properties.get(key) + "\n");
        writer.flush();
    }

    /**
     * Loads...
     *
     * @param reader
     * @throws IOException
     */
    private void load( BufferedReader reader ) throws IOException {
        this.properties = Collections.synchronizedMap(new HashMap<>());
        while ( reader.ready() ) {
            String no_comments = reader.readLine().split("#")[0];
            int eqind = no_comments.indexOf('=');
            if ( eqind != -1 )
                this.properties.put(no_comments.substring(0, eqind), no_comments.length() > eqind
                        ? no_comments.substring(eqind + 1, no_comments.length())
                        : null);
        }
    }
}
