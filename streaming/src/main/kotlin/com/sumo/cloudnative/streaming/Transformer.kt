package com.sumo.cloudnative.streaming

import com.sumo.cloudnative.streaming.utils.GenericAvroSerde
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig
import org.apache.avro.generic.GenericRecord
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.KeyValue
import org.apache.kafka.streams.StreamsConfig
import org.apache.kafka.streams.kstream.KStreamBuilder
import org.apache.kafka.streams.kstream.Predicate
import org.apache.kafka.streams.processor.WallclockTimestampExtractor
import org.slf4j.LoggerFactory


class Transformer {

    private lateinit var kafkaStreams: KafkaStreams

    fun run() {

        val streamsConfig = StreamsConfig(properties)

        val kStreamBuilder = KStreamBuilder()


        val sentiToKey = { k: GenericRecord, v: GenericRecord ->
             "neu"
        }

        val tweetToKeyValue = { lang: String, tweet: GenericRecord -> KeyValue(tweet.get("user"), tweet) }

        val isPositive = Predicate({ k: String, v: GenericRecord -> k.equals("pos") })
        val isNegative = Predicate({ k: String, v: GenericRecord -> k.equals("neg") })
        val isNeutral = Predicate({ k: String, v: GenericRecord -> k.equals("neu") })

        val tweetStream = kStreamBuilder.stream<GenericRecord, GenericRecord>("english")

        val filteredStreams = tweetStream.selectKey(sentiToKey).branch(isPositive, isNegative, isNeutral)

        filteredStreams[0].map(tweetToKeyValue).to("positive")
        filteredStreams[1].map(tweetToKeyValue).to("negative")
        filteredStreams[2].map(tweetToKeyValue).to("neutral")

        kafkaStreams = KafkaStreams(kStreamBuilder, streamsConfig)
        log.debug("Transformer started")
        kafkaStreams.start()

    }

    fun stop() {
        kafkaStreams.close()
        log.debug("Transformer stopped")
    }


    companion object {
        private val log = LoggerFactory.getLogger(Transformer::class.java)

        @JvmStatic fun main(args: Array<String>) {
            val transformer = Transformer()

            Runtime.getRuntime().addShutdownHook(Thread {
                transformer.stop()
            })

            transformer.run()
        }

        val properties = mapOf(
                StreamsConfig.APPLICATION_ID_CONFIG to "ETL-Transformer",
                StreamsConfig.BOOTSTRAP_SERVERS_CONFIG to "localhost:9092",
                StreamsConfig.ZOOKEEPER_CONNECT_CONFIG to "localhost:2181",
                StreamsConfig.KEY_SERDE_CLASS_CONFIG to GenericAvroSerde::class.java,
                StreamsConfig.VALUE_SERDE_CLASS_CONFIG to GenericAvroSerde::class.java,
                AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG to "http://localhost:8081",
                StreamsConfig.TIMESTAMP_EXTRACTOR_CLASS_CONFIG to WallclockTimestampExtractor::class.java
        )
    }

}