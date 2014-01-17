package com.mycompany.vertx.multicast.test.integration.java;

import com.mycompany.vertx.multicast.test.ReceiverVerticle;
import com.mycompany.vertx.multicast.test.SenderVerticle;
import org.junit.Test;
import org.vertx.testtools.TestVerticle;

public class BasicIntegrationTest extends TestVerticle {

    @Test
    public void testDeployArbitraryVerticle() {
        container.deployVerticle(SenderVerticle.class.getName());
        container.deployVerticle(ReceiverVerticle.class.getName());
    }

}
