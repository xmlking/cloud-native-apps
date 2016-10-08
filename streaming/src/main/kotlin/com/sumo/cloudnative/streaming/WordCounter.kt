package com.sumo.cloudnative.streaming

import org.apache.kafka.common.serialization.Serde
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.KeyValue
import org.apache.kafka.streams.StreamsConfig
import org.apache.kafka.streams.kstream.KStreamBuilder
import org.apache.kafka.streams.processor.WallclockTimestampExtractor
import java.util.Properties
import java.util.Arrays.asList

object WordCounter {
    @JvmStatic fun main(args: Array<String>) {
        val streamsConfig = StreamsConfig(properties)
        val stringSerde = Serdes.String()

        val kStreamBuilder = KStreamBuilder()

        kStreamBuilder.stream(stringSerde, stringSerde, "words-topic")
                .flatMapValues({ text -> asList(text.split(" ")) })
                .map({ key, word -> KeyValue(word, word) })
//                .countByKey(stringSerde, "Counts").toStream()
                .countByKey("Counts").toStream()
                .map({ word, count -> KeyValue(word, word + ":" + count) })
//                .to(stringSerde, stringSerde, "counts-topic")
                .to("counts-topic")

        val kafkaStreams = KafkaStreams(kStreamBuilder, streamsConfig)
        kafkaStreams.start()

        Runtime.getRuntime().addShutdownHook(Thread { kafkaStreams.close() } )
    }

    private val properties: Properties
        get() {
            val props = Properties()
            props.put(StreamsConfig.APPLICATION_ID_CONFIG, "Example-Counter")
            props.put("group.id", "test-group")
            props.put(StreamsConfig.APPLICATION_ID_CONFIG, "stream-counter")
            props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
            props.put(StreamsConfig.TIMESTAMP_EXTRACTOR_CLASS_CONFIG, WallclockTimestampExtractor::class.java)
            return props
        }
}