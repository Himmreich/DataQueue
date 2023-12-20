package com.example.dataqueue.config;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.errorhandler.RedeliveryPolicy;
import org.springframework.stereotype.Component;

@Component
public class Jt400RouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        //from("jt400://user:pass@serv/DTQ").bean(Jt400DataQueueConsumer.class, "processMessage");
        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        redeliveryPolicy.setMaximumRedeliveries(3);
        redeliveryPolicy.setRedeliveryDelay(5000);
        redeliveryPolicy.setBackOffMultiplier(2);
        redeliveryPolicy.setMaximumRedeliveryDelay(20000);

        // Configuration du gestionnaire d'erreurs transactionnel
        errorHandler(
                defaultErrorHandler()
                        .maximumRedeliveries(3) // Nombre maximal de tentatives de redelivery
                        .redeliveryDelay(5)  // Délai entre les tentatives de redelivery en millisecondes
                        .retryAttemptedLogLevel(LoggingLevel.TRACE) // Niveau de journalisation pour chaque tentative de redelivery
        );

        from("jt400://user:pass@serv/DTQ")
                .transacted()
                .doTry()
                .bean(MyDataQueueProcessor.class, "processData")
                .doCatch(Exception.class).to("direct:dataQueueError")
                .log("Exception occurred. Rolling back transaction.")
                .rollback()
                .end();

        from("direct:dataQueueError")
                .log("Redelivering to DataQueue: ${body}").to("jt400://user:pass@serv/DTQ");
    }
}
