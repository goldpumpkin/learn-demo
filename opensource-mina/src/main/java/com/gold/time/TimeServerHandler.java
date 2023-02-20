package com.gold.time;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import java.util.Date;

@Slf4j
public class TimeServerHandler extends IoHandlerAdapter {

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) {
        log.info("IDLE session status:{}", session.getIdleCount(status));
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) {
        cause.printStackTrace();
    }

    @Override
    public void messageReceived(IoSession session, Object message) {
        final String str = message.toString();
        if (StringUtils.equals(str.trim(), "quit")) {
            session.closeNow();
            return;
        }

        Date now = new Date();
        session.write(now.toString());
        log.info("message written");
    }
}
