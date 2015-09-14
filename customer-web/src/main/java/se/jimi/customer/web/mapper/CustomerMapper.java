package se.jimi.customer.web.mapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.stream.JsonWriter;

import se.jimi.customer.model.Customer;
import se.jimi.customer.model.MemberStatus;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Provider
public class CustomerMapper implements MessageBodyWriter<Customer>, MessageBodyReader<Customer> {
	private Gson gson;

	public CustomerMapper() {
		gson = new GsonBuilder().registerTypeAdapter(Customer.class, new CustomerAdapter()).create();
	}

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return type.isAssignableFrom(Customer.class);
	}

	@Override
	public long getSize(Customer t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return 0;
	}

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return type.isAssignableFrom(Customer.class);
	}

	@Override
	public void writeTo(Customer customer, Class<?> type, Type genericType, Annotation[] annotations,
			MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
					throws IOException, WebApplicationException {
		try (JsonWriter writer = new JsonWriter(new OutputStreamWriter(entityStream))) {
			gson.toJson(customer, Customer.class, writer);
		}
	}

	@Override
	public Customer readFrom(Class<Customer> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
					throws IOException, WebApplicationException {
		Customer customer = gson.fromJson(new InputStreamReader(entityStream), Customer.class);

		return customer;
	}

	private static final class CustomerAdapter implements JsonSerializer<Customer>, JsonDeserializer<Customer>

	{
		@Override
		public JsonElement serialize(Customer customer, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject jsonCustomer = new JsonObject();
			jsonCustomer.add("email", new JsonPrimitive(customer.getEmail()));
			jsonCustomer.add("memberStatus", new JsonPrimitive(customer.getMemberStatus().toString()));

			return jsonCustomer;
		}

		@Override
		public Customer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject jsonCustomer = json.getAsJsonObject();

			String email = jsonCustomer.get("email").getAsString();
			String memberString = jsonCustomer.get("memberStatus").getAsString();
			MemberStatus memberStatus = MemberStatus.valueOf(memberString);

			return new Customer(email, memberStatus);
		}
	}
}
