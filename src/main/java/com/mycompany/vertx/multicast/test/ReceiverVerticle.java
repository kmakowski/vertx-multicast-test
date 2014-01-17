package com.mycompany.vertx.multicast.test;

import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.Handler;
import org.vertx.java.core.datagram.DatagramPacket;
import org.vertx.java.core.datagram.DatagramSocket;
import org.vertx.java.core.datagram.InternetProtocolFamily;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.platform.Verticle;

public class ReceiverVerticle extends Verticle {

    @Override
    public void start() {
        final Logger log = container.logger();

        final DatagramSocket socket = vertx.createDatagramSocket(InternetProtocolFamily.IPv4);

        socket.listen("0.0.0.0", 6000, new Handler<AsyncResult<DatagramSocket>>() {

            @Override
            public void handle(AsyncResult<DatagramSocket> result) {

                if (result.failed()) {
                    log.error("Failed: ", result.cause());
                    return;
                }

                socket.dataHandler(new Handler<DatagramPacket>() {

                    @Override
                    public void handle(DatagramPacket packet) {

                        log.info("Received packet from sender: " + packet.sender() + " " + packet.data());
                    }
                });

                socket.listenMulticastGroup("230.0.0.1", new Handler<AsyncResult<DatagramSocket>>() {

                    @Override
                    public void handle(AsyncResult<DatagramSocket> event) {

                        log.info("listenMulticastGroup succeeded: " + event.succeeded());
                    }
                });

            }
        });

        log.info("ReceiverVerticle started");

    }
}
