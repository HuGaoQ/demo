package com.ncamc.serializer;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Slf4j
public class KryoSerializer implements Serializer {

    // 通过ThreadLocal确保线程安全
    private final ThreadLocal<Kryo> kryoThreadLocal = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        kryo.register(HttpResponse.class);
        kryo.register(HttpRequest.class);
        return kryo;
    });

    /**
     * 序列化
     *
     * @param obj 对象
     * @return 字节
     */
    @Override
    public byte[] serialize(Object obj) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Output output = new Output(outputStream);
            Kryo kryo = kryoThreadLocal.get();
            kryo.writeObject(output, obj);
            kryoThreadLocal.remove();
            return output.toBytes();
        } catch (Exception e) {
            log.error("Serialization failed");
        }
        return null;
    }

    /**
     * 反序列化
     *
     * @param bytes 序列化后的字节
     * @param clazz 要转化的类
     * @return 任意对象
     */
    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes)) {
            Input input = new Input(inputStream);
            Kryo kryo = kryoThreadLocal.get();
            Object obj = kryo.readObject(input, clazz);
            kryoThreadLocal.remove();
            return clazz.cast(obj);
        } catch (IOException e) {
            log.error("Serialization failed");
        }
        return null;
    }
}
