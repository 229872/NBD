package repositories.codec;

import model.UniqueId;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;

import java.util.UUID;

public class UniqueIdCodec implements Codec<UniqueId> {

    private Codec<UUID> uuidCodec;
    public UniqueIdCodec(CodecRegistry codecRegistry) {
        uuidCodec = codecRegistry.get(UUID.class);
    }

    @Override
    public UniqueId decode(BsonReader bsonReader, DecoderContext decoderContext) {
        UUID uuid = uuidCodec.decode(bsonReader, decoderContext);
        return new UniqueId(uuid);
    }

    @Override
    public void encode(BsonWriter bsonWriter, UniqueId uniqueId, EncoderContext encoderContext) {
        uuidCodec.encode(bsonWriter, uniqueId.getUuid(), encoderContext);
    }

    @Override
    public Class<UniqueId> getEncoderClass() {
        return UniqueId.class;
    }
}
