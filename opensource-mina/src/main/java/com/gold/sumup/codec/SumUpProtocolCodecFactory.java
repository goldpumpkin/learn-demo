package com.gold.sumup.codec;

import com.gold.sumup.codec.decoder.AddMessageDecoder;
import com.gold.sumup.codec.decoder.ResultMessageDecoder;
import com.gold.sumup.codec.encoder.AddMessageEncoder;
import com.gold.sumup.codec.encoder.ResultMessageEncoder;
import com.gold.sumup.message.AddMessage;
import com.gold.sumup.message.ResultMessage;
import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

public class SumUpProtocolCodecFactory extends DemuxingProtocolCodecFactory {

    public SumUpProtocolCodecFactory(boolean server) {

        // Server
        if (server) {
            super.addMessageDecoder(AddMessageDecoder.class);
            super.addMessageEncoder(ResultMessage.class,
                    ResultMessageEncoder.class);
            return;
        }

        // Client
        super.addMessageEncoder(AddMessage.class, AddMessageEncoder.class);
        super.addMessageDecoder(ResultMessageDecoder.class);
    }
}
