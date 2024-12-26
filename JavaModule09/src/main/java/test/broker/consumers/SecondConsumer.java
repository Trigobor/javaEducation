package test.broker.consumers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import test.broker.config.RabbitMQConfig;

public class SecondConsumer {
    public static void main(String[] args) {
        // Имя очереди
        String queueName = RabbitMQConfig.QUEUE_NAME;

        try {
            // Получаем фабрику соединений из RabbitMQConfig
            ConnectionFactory factory = RabbitMQConfig.createConnectionFactory();

            // Устанавливаем соединение и канал с RabbitMQ
            try (Connection connection = factory.newConnection();
                 Channel channel = connection.createChannel()) {

                // Объявляем очередь, если она ещё не создана
                channel.queueDeclare(queueName, false, false, false, null);

                // Callback для обработки сообщений
                DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                    String message = new String(delivery.getBody(), "UTF-8");
                    try {
                        if (message.startsWith("2")) {
                            // Сообщение подходит, обрабатываем его
                            System.out.println(" [SecondConsumer] ЭТО МНЕ?! ЭТО МНЕ?! У! У! У! ЭТО У! ЭТО МНЕ!: '" + message + "'");
                            // Подтверждаем обработку сообщения
                            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                        } else {
                            // Сообщение не подходит, возвращаем его в очередь
                            System.out.println(" [SecondConsumer] Это мне? Блин, ну это не мне опять: '" + message + "'");
                            channel.basicNack(delivery.getEnvelope().getDeliveryTag(), false, true);
                        }
                    } catch (Exception e) {
                        // В случае ошибки возвращаем сообщение
                        channel.basicNack(delivery.getEnvelope().getDeliveryTag(), false, true);
                        e.printStackTrace();
                    }
                };
                
                channel.basicConsume(queueName, false, deliverCallback, consumerTag -> {});
            }
        } catch (Exception e) {
            // Обработка ошибок
            e.printStackTrace();
        }
    }
}
