package com.github.lh.support;

import org.nustaq.serialization.FSTConfiguration;

/**
 * @author <a href="mailto:393803588@qq.com">HanL(liuhan3)</a>
 * @date 17-12-29
 */
public class SerializeUtils {

    private static final FSTConfiguration conf = FSTConfiguration.createStructConfiguration();


    public static byte[] serialize(Object object) {
        return conf.asByteArray(object);
    }

    public static Object deserialize(byte[] bytes) {
        return conf.asObject(bytes);
    }

}
