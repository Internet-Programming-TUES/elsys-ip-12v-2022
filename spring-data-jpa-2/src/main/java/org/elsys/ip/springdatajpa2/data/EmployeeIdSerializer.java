package org.elsys.ip.springdatajpa2.data;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

public class EmployeeIdSerializer extends JsonSerializer<List<Employee>> {
    @Override
    public void serialize(List<Employee> value, JsonGenerator jgen,
                          SerializerProvider provider) throws IOException {
        jgen.writeStartArray();
        for (Employee model : value) {
            jgen.writeNumber(model.getId());
        }
        jgen.writeEndArray();
    }

}
