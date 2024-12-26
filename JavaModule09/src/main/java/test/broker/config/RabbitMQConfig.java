package test.broker.config;

import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQConfig {
    // Имя очереди
    public static final String QUEUE_NAME = "sharedQueue";

    // Хост RabbitMQ
    public static final String HOST = "localhost";

    // Порт RabbitMQ (опционально)
    public static final int PORT = 5670;

    // Логин и пароль (по умолчанию guest / guest)
    public static final String USERNAME = "guest";
    public static final String PASSWORD = "guest";

    // Метод для создания ConnectionFactory
    public static ConnectionFactory createConnectionFactory() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        return factory;
    }
}
