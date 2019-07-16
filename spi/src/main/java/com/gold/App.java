package com.gold;

import com.gold.spi.ObjectSerializer;
import com.gold.spi.SpiSerizlizer;

import java.util.Optional;
import java.util.ServiceLoader;
import java.util.stream.StreamSupport;

/**
 * SPI
 */
public class App {

    public static void main( String[] args ) {

        ServiceLoader<ObjectSerializer> serializers = ServiceLoader.load(ObjectSerializer.class);
        Optional<ObjectSerializer> serializer = StreamSupport.stream(serializers.spliterator(),false).findFirst();
        ObjectSerializer objectSerializer = serializer.orElse(new SpiSerizlizer());
        objectSerializer.getSchemeName();

    }
}
