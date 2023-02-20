package com.gold.sumup.handler;

import com.gold.sumup.message.AddMessage;
import com.gold.sumup.message.ResultMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

@Slf4j
public class SumServerSessionHandler extends IoHandlerAdapter {

    private static final String SUM_KEY = "sum";

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        // set idle time to 60 seconds
        session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 60);

        // initial sum is zero
        session.setAttribute(SUM_KEY, Integer.valueOf(0));

        log.info("server session opened");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) {
        // disconnect an idle client
        log.info("disconnect an idle client...");
        session.closeNow();
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) {
        // close the connection on exceptional situation
        log.error("[server] exceptionCaught close the connection on exceptional situation:", cause);
        session.closeNow();
    }

    @Override
    public void messageReceived(IoSession session, Object message) {
        // client only sends AddMessage. otherwise, we will have to identify
        // its type using instanceof operator.
        final AddMessage am = (AddMessage) message;
        int value = am.getValue();

        // add the value to the current sum.
        int sum = (Integer) session.getAttribute(SUM_KEY);
        long expectedSum = (long) sum + value;

        if (expectedSum > Integer.MAX_VALUE || expectedSum < Integer.MIN_VALUE) {
            // if the sum overflows or under flows, return error message
            ResultMessage rm = new ResultMessage();
            // copy sequence
            rm.setSequence(am.getSequence());
            rm.setOk(false);
            session.write(rm);
        } else {
            // sum up
            sum = (int) expectedSum;
            session.setAttribute(SUM_KEY, sum);

            // return the result message
            ResultMessage rm = new ResultMessage();
            // copy sequence
            rm.setSequence(am.getSequence());
            rm.setOk(true);
            rm.setValue(sum);
            session.write(rm);
        }
    }
}
