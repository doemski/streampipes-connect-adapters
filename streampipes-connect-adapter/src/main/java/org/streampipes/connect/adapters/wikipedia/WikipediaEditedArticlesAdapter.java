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
package org.streampipes.connect.adapters.wikipedia;

import org.streampipes.connect.adapter.Adapter;
import org.streampipes.model.AdapterType;
import org.streampipes.model.connect.adapter.SpecificAdapterStreamDescription;
import org.streampipes.sdk.builder.adapter.SpecificDataStreamAdapterBuilder;

public class WikipediaEditedArticlesAdapter extends WikipediaAdapter {

  public static final String ID = "http://streampipes.org/adapter/specific/wikipedia/edit";

  private static final String Type = "edit";

  public WikipediaEditedArticlesAdapter(SpecificAdapterStreamDescription adapterStreamDescription) {
    super(adapterStreamDescription, Type);
  }

  public WikipediaEditedArticlesAdapter() {
    super();
  }

  @Override
  public SpecificAdapterStreamDescription declareModel() {
    return SpecificDataStreamAdapterBuilder.create(ID, "Wikipedia Edits", "Continuously publishes" +
            " recent Wikipedia edits")
            .category(AdapterType.SocialMedia, AdapterType.OpenData)
            .iconUrl("wikipedia.png")
            .build();
  }

  @Override
  public Adapter getInstance(SpecificAdapterStreamDescription adapterDescription) {
    return new WikipediaEditedArticlesAdapter(adapterDescription);
  }

  @Override
  public String getId() {
    return ID;
  }
}
