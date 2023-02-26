package com.gold.image.client;

import com.gold.image.ImageRequest;
import com.gold.image.ImageResponse;
import com.gold.image.codec.ImageCodecFactory;
import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;


public class ImageClient extends IoHandlerAdapter {
    public static final int CONNECT_TIMEOUT = 3000;

    private String host;
    private int port;
    private SocketConnector connector;
    private IoSession session;
    private ImageListener imageListener;

    public ImageClient(String host, int port, ImageListener imageListener) {
        this.host = host;
        this.port = port;
        this.imageListener = imageListener;
        connector = new NioSocketConnector();
        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ImageCodecFactory(true)));
        connector.setHandler(this);
    }

    public boolean isConnected() {
        return (session != null && session.isConnected());
    }

    public void connect() {
        ConnectFuture connectFuture = connector.connect(new InetSocketAddress(host, port));
        connectFuture.awaitUninterruptibly(CONNECT_TIMEOUT);
        try {
            session = connectFuture.getSession();
        } catch (RuntimeIoException e) {
            imageListener.onException(e);
        }
    }

    public void disconnect() {
        if (session != null) {
            session.close(true).awaitUninterruptibly(CONNECT_TIMEOUT);
            session = null;
        }
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        imageListener.sessionOpened();
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        imageListener.sessionClosed();
    }

    public void sendRequest(ImageRequest imageRequest) {
        if (session == null) {
            //noinspection ThrowableInstanceNeverThrown
            imageListener.onException(new Throwable("not connected"));
        } else {
            session.write(imageRequest);
        }
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        ImageResponse response = (ImageResponse) message;
        imageListener.onImages(response.getImage1(), response.getImage2());
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        imageListener.onException(cause);
    }

}
