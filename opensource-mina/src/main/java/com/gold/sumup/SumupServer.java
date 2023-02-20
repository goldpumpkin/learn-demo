package com.gold.sumup;

import com.gold.sumup.codec.SumUpProtocolCodecFactory;
import com.gold.sumup.handler.SumServerSessionHandler;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;

public class SumupServer {

    private static final int PORT = 9132;
    private static final boolean USE_CUSTOM_CODEC = true;

    public static void main(String[] args) throws IOException {
        final IoAcceptor acceptor = new NioSocketAcceptor();

        // filter chain
        final DefaultIoFilterChainBuilder filterChain = acceptor.getFilterChain();
        if (USE_CUSTOM_CODEC) {
            filterChain.addLast("codec", new ProtocolCodecFilter(new SumUpProtocolCodecFactory(true)));
        } else {
            filterChain.addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
        }
        filterChain.addLast("logger", new LoggingFilter());

        // sum up handler
        acceptor.setHandler(new SumServerSessionHandler());

        // bind port
        acceptor.bind(new InetSocketAddress(PORT));
    }

}
