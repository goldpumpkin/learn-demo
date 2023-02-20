package com.gold.sumup.codec.encoder;

import com.gold.sumup.codec.Constants;
import com.gold.sumup.message.AddMessage;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

public class AddMessageEncoder<T extends AddMessage> extends AbstractMessageEncoder<T> {

    public AddMessageEncoder() {
        super(Constants.ADD);
    }

    @Override
    protected void encodeBody(IoSession session, T message, IoBuffer out) {
        out.putInt(message.getValue());
    }

    public void dispose() throws Exception {
    }
}
