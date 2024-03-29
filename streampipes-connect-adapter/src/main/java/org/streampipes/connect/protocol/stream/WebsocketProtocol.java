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
package org.streampipes.connect.protocol.stream;

import static org.streampipes.sdk.helpers.EpProperties.stringEp;
import static org.streampipes.sdk.helpers.EpProperties.timestampProperty;

import org.streampipes.connect.adapter.Adapter;
import org.streampipes.connect.adapter.exception.AdapterException;
import org.streampipes.connect.adapter.exception.ParseException;
import org.streampipes.connect.adapter.model.specific.SpecificDataStreamAdapter;
import org.streampipes.model.AdapterType;
import org.streampipes.model.connect.adapter.SpecificAdapterStreamDescription;
import org.streampipes.model.connect.guess.GuessSchema;
import org.streampipes.sdk.builder.adapter.GuessSchemaBuilder;
import org.streampipes.sdk.builder.adapter.SpecificDataStreamAdapterBuilder;
import org.streampipes.sdk.helpers.Labels;
import org.streampipes.vocabulary.SO;

import java.net.URI;
import java.net.URISyntaxException;

public class WebsocketProtocol extends SpecificDataStreamAdapter {

  public static final String ID = "http://streampipes.org/adapter/specific/turtlebot";

  private WebsocketClient websocketClient;

  public WebsocketProtocol() {
  }

  public WebsocketProtocol(SpecificAdapterStreamDescription adapterDescription) {
    super(adapterDescription);

    //getConfigurations(adapterDescription);

    //this.jsonObjectParser = new JsonObjectParser();
  }

  @Override
  public SpecificAdapterStreamDescription declareModel() {
    SpecificAdapterStreamDescription description = SpecificDataStreamAdapterBuilder.create(ID,
            "Turtlebot Map", "")
            .iconUrl("ros.png")
            .category(AdapterType.Manufacturing).build();
    description.setAppId(ID);


    return description;
  }

  @Override
  public void startAdapter() throws AdapterException {
    try {
      this.websocketClient = new WebsocketClient(adapterPipeline, new URI("ws://192.168.178" +
              ".40:9090"));
      this.websocketClient.connect();
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void stopAdapter() throws AdapterException {
    this.websocketClient.close();
  }

  @Override
  public Adapter getInstance(SpecificAdapterStreamDescription adapterDescription) {
    return new WebsocketProtocol(adapterDescription);
  }

  @Override
  public GuessSchema getSchema(SpecificAdapterStreamDescription adapterDescription) throws AdapterException, ParseException {
    return GuessSchemaBuilder.create()
            .property(timestampProperty("timestamp"))
            .property(stringEp(Labels.from("data", "image", ""), "data", SO.Image))
            .build();
  }

  @Override
  public String getId() {
    return ID;
  }
}

