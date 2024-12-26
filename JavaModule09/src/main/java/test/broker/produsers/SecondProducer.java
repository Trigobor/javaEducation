package test.broker.produsers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import test.broker.config.RabbitMQConfig;

public class SecondProducer {
    public static void main(String[] args) {

        String queueName = RabbitMQConfig.QUEUE_NAME;

        try {
            // Получаем фабрику соединений из RabbitMQConfig
            ConnectionFactory factory = RabbitMQConfig.createConnectionFactory();

            // Устанавливаем соединение с RabbitMQ
            try (Connection connection = factory.newConnection();
                 Channel channel = connection.createChannel()) {

                // Объявляем очередь, если она ещё не создана
                channel.queueDeclare(queueName, false, false, false, null);

                // Сообщение, которое отправляем
                String message = "2) Hello from Producer two! I came here to do nothing";

                // Публикуем сообщение в очередь
                channel.basicPublish("", queueName, null, message.getBytes());
                System.out.println(" [x] Sent '" + message + "'");
            }
        } catch (Exception e) {
            // Обработка ошибок
            e.printStackTrace();
        }
    }
}
