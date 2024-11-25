import org.apache.catalina.startup.Tomcat;
import org.website.servlets.AuthServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.Servlet;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();

        // Установка порта и каталога для развертывания приложения
        tomcat.setPort(12345);
        tomcat.setBaseDir(".");

        // Добавление контекста приложения
        tomcat.addWebapp("", new File("src/main/webapp").getAbsolutePath());

        tomcat.addServlet("/login", "AuthServlet", new AuthServlet());
        tomcat.addServletMappingDecoded("/hello", "HelloServlet");

        // Запуск Tomcat
        tomcat.start();
        tomcat.getServer().await();
    }
}