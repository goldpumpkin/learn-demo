package com.gold.sumup;

import com.gold.sumup.codec.SumUpProtocolCodecFactory;
import com.gold.sumup.handler.SumClientSessionHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;

@Slf4j
public class SumupClient {

    private static final String HOSTNAME = "localhost";
    private static final int SERVER_PORT = 9132;

    private static final int CONNECT_TIMEOUT = 20000;
    private static final boolean USE_CUSTOM_CODEC = true;

    public static void main(String[] args) throws InterruptedException {
        final NioSocketConnector connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(CONNECT_TIMEOUT);

        // filter
        final DefaultIoFilterChainBuilder filterChain = connector.getFilterChain();
        if (USE_CUSTOM_CODEC) {
            filterChain.addLast("codec", new ProtocolCodecFilter(new SumUpProtocolCodecFactory(false)));
        } else {
            filterChain.addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
        }

        filterChain.addLast("logger", new LoggingFilter());

        // handler
        connector.setHandler(new SumClientSessionHandler(new int[]{1, 2, 3}));

        IoSession session;

        while (true) {
            try {
                ConnectFuture future = connector.connect(new InetSocketAddress(HOSTNAME, SERVER_PORT));
                future.awaitUninterruptibly();
                session = future.getSession();
                break;
            } catch (RuntimeIoException e) {
                log.error("Failed to connect.");
                e.printStackTrace();
                Thread.sleep(5000L);
            }
        }

        session.getCloseFuture().awaitUninterruptibly();
        connector.dispose();
    }

}
