package com.cornflower.kotlin.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

/**
 * Created by xiejingbao on 2018/10/29.
 */
public class IOUtils {
    public static void close(InputStream in) {
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {

            }
        }
    }

    public static void close(OutputStream out){
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {

            }
        }
    }

    public static void close(Reader r) {
        if (r != null) {
            try {
                r.close();
            } catch (IOException e) {

            }
        }
    }

    public static void close(Writer w){
        if (w != null) {
            try {
                w.close();
            } catch (IOException e) {

            }
        }
    }

}
