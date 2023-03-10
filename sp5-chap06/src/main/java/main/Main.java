package main;

import config.AppCtx;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import spring.Client;
import spring.Client2;

public class Main {

    public static void main(String[] args) {
        AbstractApplicationContext ctx
                = new AnnotationConfigApplicationContext(AppCtx.class);

        Client client = ctx.getBean(Client.class);
        client.send();
        Client2 client2 = ctx.getBean(Client2.class);
        client2.send();

        System.out.println("닫힌다");
        ctx.close();
        System.out.println("닫혔다");
    }
}
