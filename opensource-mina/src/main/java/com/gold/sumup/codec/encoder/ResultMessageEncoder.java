package com.gold.sumup.codec.encoder;

import com.gold.sumup.codec.Constants;
import com.gold.sumup.message.ResultMessage;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

public class ResultMessageEncoder<T extends ResultMessage> extends AbstractMessageEncoder<T> {

    public ResultMessageEncoder() {
        super(Constants.RESULT);
    }

    @Override
    protected void encodeBody(IoSession session, T message, IoBuffer out) {
        if (message.isOk()) {
            out.putShort((short) Constants.RESULT_OK);
            out.putInt(message.getValue());
            return;
        }

        out.putShort((short) Constants.RESULT_ERROR);
    }

    public void dispose() throws Exception {
    }
}
