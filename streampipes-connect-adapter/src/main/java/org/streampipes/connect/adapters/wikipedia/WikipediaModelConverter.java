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

import org.streampipes.connect.adapters.wikipedia.model.WikipediaModel;

import java.util.HashMap;
import java.util.Map;

import static org.streampipes.connect.adapters.wikipedia.WikipediaAdapter.*;

public class WikipediaModelConverter {

  private WikipediaModel wikipediaModel;

  public WikipediaModelConverter(WikipediaModel wikipediaModel) {
    this.wikipediaModel = wikipediaModel;
  }

  public Map<String, Object> makeMap() {
    Map<String, Object> event = new HashMap<>();
    event.put(TIMESTAMP, wikipediaModel.getTimestamp());
    event.put(TYPE, wikipediaModel.getType());
    event.put(EVENT_ID, wikipediaModel.getId());
    event.put(NAMESPACE, wikipediaModel.getNamespace());
    event.put(TITLE, wikipediaModel.getTitle());
    event.put(USER, wikipediaModel.getUser());
    event.put(BOT, wikipediaModel.getBot());
    event.put(MINOR, wikipediaModel.getMinor());
    event.put(OLDLENGTH, wikipediaModel.getLength().getOld());
    event.put(NEWLENGTH, wikipediaModel.getLength().getNew());
    event.put(OLDREVISION, wikipediaModel.getRevision().getOld());
    event.put(NEWREVISION, wikipediaModel.getRevision().getNew());
    event.put(SERVERURL, wikipediaModel.getServerUrl());
    event.put(SERVERNAME, wikipediaModel.getServerName());
    event.put(WIKI, wikipediaModel.getWiki());
    event.put(URI, wikipediaModel.getMeta().getUri());

    return event;
  }
}
