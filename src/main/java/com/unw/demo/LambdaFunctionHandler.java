package com.unw.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

import software.amazon.awssdk.services.sqs.SQSClient;

/**
 * lambda to write a message (json) in a sqs queue 
 * can be triggerd by api-gateway for example
 * 
 * @author UNGERW
 *
 */
public class LambdaFunctionHandler implements RequestHandler<Object, String> {
	
	private static String QUEUE_NAME = "queue1";

    public LambdaFunctionHandler() {}

    @Override
    public String handleRequest(Object input, Context context) {
        context.getLogger().log("Received input: " + input);
		SQSClient sqsClient = SQSClient.builder().build();
		String message = input.toString();

		GetQueueUrlRequest getQueueRequest = GetQueueUrlRequest.builder().queueName(QUEUE_NAME).build();
		String queueUrl = sqsClient.getQueueUrl(getQueueRequest).queueUrl();
		sqsClient.sendMessage(
				SendMessageRequest.builder().queueUrl(queueUrl).messageBody(message).delaySeconds(10).build());
		return message;
        
    }
}