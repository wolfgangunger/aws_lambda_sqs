package com.unw.demo;

import software.amazon.awssdk.services.sqs.SQSClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;

public class SQSReader {

	private static String QUEUE_NAME = "ppQueue1"; 
	
	public static void main(String[] args) {
		
		SQSClient sqsClient = SQSClient.builder().build();

		GetQueueUrlRequest getQueueRequest = GetQueueUrlRequest.builder().queueName(QUEUE_NAME).build();
		String queueUrl = sqsClient.getQueueUrl(getQueueRequest).queueUrl();
		ReceiveMessageResponse message = sqsClient.receiveMessage(ReceiveMessageRequest.builder().queueUrl(queueUrl).build());
		System.out.println(message);
		//AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
		//List<Message> messages = sqs.receiveMessage(queueUrl).getMessages();
		//for(Message m : messages) {
		//	System.out.println(m);
		//	sqs.deleteMessage(queueUrl,m.getReceiptHandle());
		//}
	}

}
