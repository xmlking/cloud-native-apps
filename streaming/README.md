Streaming ETL
=============

Streaming ETL: Streaming ETL with `Kafka Streams`.

* Sample 1: Transformer
* Sample 2 : WordCounter

![WordCounter](kafka_streams.png)

### Build

```bash
gradle streaming:installShadow
```

### Run

```bash
gradle streaming:run
```


### Testing

To easily validate the above application as a proof of concept, one can use the [kafkacat](https://github.com/edenhill/kafkacat) command, as below.

#### Producer
```
kafkacat -b localhost -t words-topic -P
<write some text>
```
#### Consumer
```
kafkacat -b localhost -t counts-topic -C
```