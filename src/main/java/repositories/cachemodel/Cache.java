package repositories.cachemodel;

import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Cache {

    private static TimerTask task;
    private static Timer timer;
    protected static JedisPooled pool;
    private static boolean healthy;
    protected static HostAndPort getHostAndPort() {
        String connectionString = MovieCache.getConnectionString();
        return HostAndPort.from(connectionString);
    }

    public Cache() {
        HostAndPort hostAndPort = getHostAndPort();
        JedisClientConfig config = DefaultJedisClientConfig.builder().build();

        if (pool == null) {
            pool = new JedisPooled(hostAndPort, config);
            task = new Healthcheck();
            timer = new Timer(true);
            healthy = MovieCache.checkHealthy();
            timer.scheduleAtFixedRate(task, 2, 2);
        }
    }

    public static String getConnectionString() {
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream stream = loader.getResourceAsStream("app.properties");
            Properties properties = new Properties();
            properties.load(stream);
            return properties.getProperty("connectionString");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Property not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isHealthy() {
        return healthy;
    }

    public static boolean checkHealthy() {
        HostAndPort hostAndPort = getHostAndPort();
        JedisClientConfig clientConfig = DefaultJedisClientConfig.builder().build();

        try (Jedis jedis = new Jedis(hostAndPort, clientConfig)) {
            return Objects.equals(jedis.ping(), "PONG");
        } catch (JedisConnectionException e) {
            return false;
        }
    }

     static class Healthcheck extends TimerTask {

        @Override
        public void run() {
            Cache.healthy = MovieCache.checkHealthy();
        }
    }
}
