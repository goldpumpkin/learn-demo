package com.webull.gold.protobuf;

import com.gold.protobuf.bean.Quote;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;

import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ProtobufMsgTest {

    /**
     * kafka 地址
     */
    private static final String BROKER_LIST = "127.0.0.1:9092";

    /**
     * Topic
     */
    private static final String TOPIC = "topic_quote";

    @Test
    public void sendProtobufMsg() {
        final Quote.QuoteInfoVo quoteInfo = Quote.QuoteInfoVo.newBuilder()
                                                             .setSecurityId("9898998")
                                                             .setAsk("199.00")
                                                             .setBid("180.00")
                                                             .setExchange("NASDAQ")
                                                             .setSymbol("AAPL")
                                                             .build();
        final Quote.QuoteInfoVoList msg = Quote.QuoteInfoVoList.newBuilder()
                                                               .addItems(quoteInfo)
                                                               .build();


        final byte[] byteArray = msg.toByteArray();
        send(BROKER_LIST, TOPIC, byteArray);
    }

    /**
     * 构造一个生产者
     */
    private KafkaProducer<String, byte[]> createProducer(String envBrokerList) {
        final Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, envBrokerList);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class.getName());
        return new KafkaProducer<>(properties);
    }

    /**
     * 发布消息
     */
    public void send(String kafkaServer, String eventTopic, byte[] msgByte) {
        final KafkaProducer<String, byte[]> producer = createProducer(kafkaServer);

        try {
            //send message
            final ProducerRecord<String, byte[]> producerMsg = new ProducerRecord<>(eventTopic, UUID.randomUUID()
                                                                                                    .toString()
                                                                                                    .replace("-", ""),
                    msgByte);
            final Future<RecordMetadata> future = producer.send(producerMsg);

            //等待响应
            final RecordMetadata metadata = future.get();
            final String topic = metadata.topic();
            final int partition = metadata.partition();
            final long offset = metadata.offset();

            System.out.println("Send a message, value: ' [1] " + new String(
                    msgByte) + " ', topic: " + topic + ", partition:" + partition + ", offset " + offset);
        } catch (ExecutionException | InterruptedException e) {
            System.out.println("Error in sending record");
            e.printStackTrace();
        }

        producer.close();
    }


}

