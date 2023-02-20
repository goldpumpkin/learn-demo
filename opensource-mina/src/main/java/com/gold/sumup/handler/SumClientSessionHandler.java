package com.gold.sumup.handler;

import com.gold.sumup.message.AddMessage;
import com.gold.sumup.message.ResultMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

@Slf4j
public class SumClientSessionHandler extends IoHandlerAdapter {

    private final int[] values;

    private boolean finished;

    public SumClientSessionHandler(int[] values) {
        this.values = values;
    }

    public boolean isFinished() {
        return finished;
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        log.info("client session create.");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        // send summation requests
        for (int i = 0; i < values.length; i++) {
            final AddMessage m = new AddMessage();
            m.setSequence(i);
            m.setValue(values[i]);
            session.write(m);
        }
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        session.closeNow();
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        // server only sends ResultMessage. otherwise, we will have to identify
        // its type using instanceof operator.
        ResultMessage rm = (ResultMessage) message;

        if (rm.isOk()) {
            // server returned OK code.
            // if received the result message which has the last sequence
            // number,
            // it is time to disconnect.
            if (rm.getSequence() == values.length - 1) {
                // print the sum and disconnect.
                log.info("The sum: " + rm.getValue());
                session.closeNow();
                finished = true;
            }
        } else {
            // server returned error code because of overflow, etc.
            log.warn("Server error, disconnecting...");
            session.closeNow();
            finished = true;
        }
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        log.info("message info:{}", message);
    }
}
