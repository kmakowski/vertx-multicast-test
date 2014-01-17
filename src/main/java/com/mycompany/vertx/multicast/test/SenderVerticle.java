package com.mycompany.vertx.multicast.test;


import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.Handler;
import org.vertx.java.core.datagram.DatagramSocket;
import org.vertx.java.core.datagram.InternetProtocolFamily;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.platform.Verticle;

public class SenderVerticle extends Verticle {

    @Override
    public void start() {

        final Logger log = container.logger();
        final DatagramSocket socket = vertx.createDatagramSocket(InternetProtocolFamily.IPv4);
        socket.setMulticastNetworkInterface("eth0");

        vertx.setPeriodic(1000, new Handler<Long>() {

            @Override
            public void handle(Long event) {

                socket.send("Hello, its orchestrator here", "230.0.0.1", 6000, new Handler<AsyncResult<DatagramSocket>>() {

                    @Override
                    public void handle(AsyncResult<DatagramSocket> result) {

                        log.info("Multicast message sent: " + result.succeeded());
                    }
                });
            }
        });

        log.info("OrchestratorVerticle started local address: " + socket.localAddress());

    }
}
