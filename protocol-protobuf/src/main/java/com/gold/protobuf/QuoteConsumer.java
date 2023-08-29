package com.gold.protobuf;

import com.gold.protobuf.bean.Quote;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class QuoteConsumer {

    @KafkaListener(topics = {"${topic_quote:topic_quote}"}, containerFactory = "msgContainerFactory")
    public void consume(List<ConsumerRecord<String, byte[]>> records, Acknowledgment ack) {

        for (ConsumerRecord<String, byte[]> msg : records) {
            try {
                final byte[] value = msg.value();
                final Quote.QuoteInfoVoList quoteInfoVoList = Quote.QuoteInfoVoList.parseFrom(value);
                final List<Quote.QuoteInfoVo> itemsList = quoteInfoVoList.getItemsList();
                for (Quote.QuoteInfoVo v : itemsList) {
                    final String securityId = v.getSecurityId();
                    final String symbol = v.getSymbol();
                    final String ask = v.getAsk();
                    final String bid = v.getBid();
                    log.info("securityId:{}, symbol:{}, ask:{}, bid:{}", securityId, symbol, ask, bid);
                }
            } catch (Exception e) {
                log.error("[QuoteConsumer#consume] consume kbus quote msg:{}, exception:", msg, e);
                throw new RuntimeException(e);
            }
        }


        ack.acknowledge();
    }


}
