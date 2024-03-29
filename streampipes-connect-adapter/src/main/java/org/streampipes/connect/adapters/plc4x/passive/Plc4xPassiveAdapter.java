/*
Copyright 2019 FZI Forschungszentrum Informatik

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package org.streampipes.connect.adapters.plc4x.passive;

import org.streampipes.connect.adapter.Adapter;
import org.streampipes.connect.adapter.exception.AdapterException;
import org.streampipes.connect.adapter.exception.ParseException;
import org.streampipes.connect.adapter.model.specific.SpecificDataStreamAdapter;
import org.streampipes.model.AdapterType;
import org.streampipes.model.connect.adapter.SpecificAdapterStreamDescription;
import org.streampipes.model.connect.guess.GuessSchema;
import org.streampipes.model.schema.EventProperty;
import org.streampipes.model.schema.EventSchema;
import org.streampipes.sdk.builder.PrimitivePropertyBuilder;
import org.streampipes.sdk.builder.adapter.SpecificDataStreamAdapterBuilder;
import org.streampipes.sdk.utils.Datatypes;

import java.util.ArrayList;
import java.util.List;

public class Plc4xPassiveAdapter extends SpecificDataStreamAdapter {

    /**
     * A unique id to identify the adapter
     */
    public static final String ID = "http://streampipes.org/adapter/specific/plc4xpassive";

    public Plc4xPassiveAdapter() {
    }

    public Plc4xPassiveAdapter(SpecificAdapterStreamDescription adapterDescription) {
        super(adapterDescription);
    }

    @Override
    public SpecificAdapterStreamDescription declareModel() {
        SpecificAdapterStreamDescription description = SpecificDataStreamAdapterBuilder.create(ID, "PLC4X Passive", "")
                .iconUrl("plc4x.png")
                .category(AdapterType.Manufacturing)
                .build();
        description.setAppId(ID);

        return description;
    }

    @Override
    public GuessSchema getSchema(SpecificAdapterStreamDescription adapterDescription) throws AdapterException, ParseException {
        GuessSchema guessSchema = new GuessSchema();

        EventSchema eventSchema = new EventSchema();
        List<EventProperty> allProperties = new ArrayList<>();

        allProperties.add(
                PrimitivePropertyBuilder
                        .create(Datatypes.String, "sourceId")
                        .label("Source Id")
                        .description("")
                        .build());

        allProperties.add(
                PrimitivePropertyBuilder
                        .create(Datatypes.String, "propertyId")
                        .label("Property Id")
                        .description("")
                        .build());

        // We need to define the type of the value, I choose a numerical value
        allProperties.add(
                PrimitivePropertyBuilder
                        .create(Datatypes.Float, "value")
                        .label("Value")
                        .description("")
                        .build());


        eventSchema.setEventProperties(allProperties);
        guessSchema.setEventSchema(eventSchema);
        guessSchema.setPropertyProbabilityList(new ArrayList<>());
        return guessSchema;
    }

    @Override
    public void startAdapter() throws AdapterException {
        // TODO
    }

    @Override
    public void stopAdapter() throws AdapterException {
        // TODO
    }

    @Override
    public Adapter getInstance(SpecificAdapterStreamDescription adapterDescription) {
        return new Plc4xPassiveAdapter(adapterDescription);
    }

    @Override
    public String getId() {
        return ID;
    }
}
