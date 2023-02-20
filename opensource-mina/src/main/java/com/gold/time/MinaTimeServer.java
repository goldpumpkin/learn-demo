package com.gold.time;

import cn.hutool.core.util.CharsetUtil;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSessionConfig;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;

public class MinaTimeServer {

    private static final int PORT = 9132;

    public static void main(String[] args) throws IOException {
        final IoAcceptor acceptor = new NioSocketAcceptor();

        // filter chain
        final DefaultIoFilterChainBuilder filterChain = acceptor.getFilterChain();
        filterChain.addLast("logger", new LoggingFilter());
        filterChain.addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(CharsetUtil.CHARSET_UTF_8)));

        // time handler
        acceptor.setHandler(new TimeServerHandler());

        // session config
        final IoSessionConfig sessionConfig = acceptor.getSessionConfig();
        sessionConfig.setReadBufferSize(2048);
        sessionConfig.setIdleTime(IdleStatus.BOTH_IDLE, 10);

        // bind port
        acceptor.bind(new InetSocketAddress(PORT));
    }

}
