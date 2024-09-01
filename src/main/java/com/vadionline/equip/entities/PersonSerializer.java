package com.vadionline.equip.entities;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import jakarta.inject.Singleton;

import java.io.IOException;

@Singleton
public class PersonSerializer extends StdSerializer<Person> {
    //add constructor
    public PersonSerializer() {
        this(null);
    }

    //add constructor
    public PersonSerializer(Class<Person> t) {
        super(t);
    }



    //add serialize method
    @Override
    public void serialize(Person person, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", person.name);
        jsonGenerator.writeStringField("email", person.email);
        jsonGenerator.writeStringField("phone", person.phone);
        jsonGenerator.writeStringField("address", person.address);
        jsonGenerator.writeEndObject();
    }

}
