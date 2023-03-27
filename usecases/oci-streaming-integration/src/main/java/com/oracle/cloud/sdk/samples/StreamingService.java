package com.oracle.cloud.sdk.samples;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.streaming.StreamAdminClient;
import com.oracle.bmc.streaming.StreamClient;
import com.oracle.bmc.streaming.model.CreateGroupCursorDetails;
import com.oracle.bmc.streaming.model.PutMessagesDetails;
import com.oracle.bmc.streaming.model.PutMessagesDetailsEntry;
import com.oracle.bmc.streaming.model.PutMessagesResultEntry;
import com.oracle.bmc.streaming.model.Stream;
import com.oracle.bmc.streaming.requests.CreateGroupCursorRequest;
import com.oracle.bmc.streaming.requests.GetMessagesRequest;
import com.oracle.bmc.streaming.requests.GetStreamRequest;
import com.oracle.bmc.streaming.requests.PutMessagesRequest;
import com.oracle.bmc.streaming.responses.CreateGroupCursorResponse;
import com.oracle.bmc.streaming.responses.GetMessagesResponse;
import com.oracle.bmc.streaming.responses.GetStreamResponse;
import com.oracle.bmc.streaming.responses.PutMessagesResponse;
import com.oracle.bmc.util.internal.StringUtils;

@Service
public class StreamingService {
	
	@Value("${oci.streaming.streamId}")
	private String streamId;
	
	@Value("${oci.streaming.cursorGroupName}")
	private String cursorGroupName;
	
	@Value("${oci.streaming.cursorInstanceName}")
	private String cursorInstanceName;

	private StreamClient client = null;
	private String cursor = null;
	
	@Autowired
	private OCIConfig config;

	public StreamClient getClient() throws Exception {
		if (client != null)
			return client;

		AuthenticationDetailsProvider adp = config.getAuthenticationDetailsProvider();
		final StreamAdminClient adminClient = StreamAdminClient.builder().region(config.getRegion()).build(adp);
		Stream stream = getStream(adminClient, streamId);
		StreamClient client = StreamClient.builder().stream(stream).build(adp);
		
		return client;
	}

	private static Stream getStream(StreamAdminClient adminClient, String streamId) {
		GetStreamResponse getResponse = adminClient.getStream(GetStreamRequest.builder().streamId(streamId).build());
		return getResponse.getStream();
	}

	public String publishMessage(Message message) throws Exception {
		StreamClient streamClient = getClient();

		// build up a putRequest and publish some messages to the stream
		List<PutMessagesDetailsEntry> messages = new ArrayList<>();
		messages.add(PutMessagesDetailsEntry.builder().key(message.key.getBytes(UTF_8))
				.value(message.value.getBytes(UTF_8)).build());

		System.out.println(String.format("Publishing %s messages to stream %s.", messages.size(), streamId));
		PutMessagesDetails messagesDetails = PutMessagesDetails.builder().messages(messages).build();

		PutMessagesRequest putRequest = PutMessagesRequest.builder().streamId(streamId)
				.putMessagesDetails(messagesDetails).build();

		PutMessagesResponse putResponse = streamClient.putMessages(putRequest);

		// the putResponse can contain some useful metadata for handling failures
		for (PutMessagesResultEntry entry : putResponse.getPutMessagesResult().getEntries()) {
			if (StringUtils.isNotBlank(entry.getError())) {
				return String.format("Error(%s): %s", entry.getError(), entry.getErrorMessage());
			} else {
				return String.format("Published message to partition %s, offset %s.", entry.getPartition(),
						entry.getOffset());
			}
		}

		return "None";
	}

	public List<Message> readMessages() throws Exception {
		GetMessagesRequest getRequest = GetMessagesRequest.builder().streamId(streamId).cursor(getCursorByGroup())
				.limit(10).build();

		GetMessagesResponse getResponse = getClient().getMessages(getRequest);

		// process the messages
		System.out.println(String.format("Read %s messages.", getResponse.getItems().size()));
		List<Message> messages = new ArrayList<Message>();
		for (com.oracle.bmc.streaming.model.Message message : getResponse.getItems()) {
			messages.add(new Message(message));
		}
		cursor = getResponse.getOpcNextCursor();

		return messages;
	}

	private String getCursorByGroup() throws Exception {
		if (cursor != null)
			return cursor;

		System.out.println(
				String.format("Creating a cursor for group %s, instance %s.", cursorGroupName, cursorInstanceName));

		CreateGroupCursorDetails cursorDetails = CreateGroupCursorDetails.builder().groupName(cursorGroupName)
				.instanceName(cursorInstanceName).type(CreateGroupCursorDetails.Type.TrimHorizon).commitOnGet(true)
				.build();

		CreateGroupCursorRequest createCursorRequest = CreateGroupCursorRequest.builder().streamId(streamId)
				.createGroupCursorDetails(cursorDetails).build();

		CreateGroupCursorResponse groupCursorResponse = getClient().createGroupCursor(createCursorRequest);
		return groupCursorResponse.getCursor().getValue();
	}

	public static class Message {
		public String key;
		public String value;
		public Long offset;
		public String partition;
		public Date timestamp;

		public Message(com.oracle.bmc.streaming.model.Message message) {
			copyFrom(message);
		}

		public Message(String key, String value) {
			this.key = key;
			this.value = value;
		}

		private void copyFrom(com.oracle.bmc.streaming.model.Message message) {
			key = message.getKey() == null ? "" : new String(message.getKey(), UTF_8);
			value = message.getValue() == null ? "" : new String(message.getValue(), UTF_8);
			offset = message.getOffset();
			partition = message.getPartition();
			timestamp = message.getTimestamp();
		}
	}
}
