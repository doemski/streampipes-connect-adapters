/*
 * Copyright 2018 FZI Forschungszentrum Informatik
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.streampipes.connect.protocol.set;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.streampipes.connect.SendToPipeline;
import org.streampipes.connect.adapter.exception.ParseException;
import org.streampipes.connect.adapter.guess.SchemaGuesser;
import org.streampipes.connect.adapter.model.generic.Format;
import org.streampipes.connect.adapter.model.generic.Parser;
import org.streampipes.connect.adapter.model.generic.Protocol;
import org.streampipes.connect.adapter.model.pipeline.AdapterPipeline;
import org.streampipes.connect.adapter.sdk.ParameterExtractor;
import org.streampipes.model.AdapterType;
import org.streampipes.model.connect.grounding.ProtocolDescription;
import org.streampipes.model.connect.guess.GuessSchema;
import org.streampipes.model.schema.EventSchema;
import org.streampipes.model.staticproperty.FileStaticProperty;
import org.streampipes.sdk.builder.adapter.ProtocolDescriptionBuilder;
import org.streampipes.sdk.helpers.AdapterSourceType;
import org.streampipes.sdk.helpers.Labels;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileProtocol extends Protocol {

    private static Logger logger = LoggerFactory.getLogger(FileProtocol.class);

    public static final String ID = "https://streampipes.org/vocabulary/v1/protocol/set/file";

    private String fileUri;

    public FileProtocol() {
    }

    public FileProtocol(Parser parser, Format format, String fileUri) {
        super(parser, format);
        this.fileUri = fileUri;
    }

    @Override
    public ProtocolDescription declareModel() {
        return ProtocolDescriptionBuilder.create(ID, "File Set", "Reads the content from a local file.")
                .sourceType(AdapterSourceType.SET)
                .category(AdapterType.Generic)
                .iconUrl("file.png")
                .requiredFile(Labels.from("filePath", "File", "File Path"))
                .build();
    }

    @Override
    public Protocol getInstance(ProtocolDescription protocolDescription, Parser parser, Format format) {
        ParameterExtractor extractor = new ParameterExtractor(protocolDescription.getConfig());

//        String fileUri = extractor.singleValue("fileUri");

        FileStaticProperty fileStaticProperty = (FileStaticProperty) extractor.getStaticPropertyByName("filePath");

        String fileUri = fileStaticProperty.getLocationPath();
        return new FileProtocol(parser, format, fileUri);
    }

    @Override
    public void run(AdapterPipeline adapterPipeline) {
        FileReader fr = null;

        // TODO fix this. Currently needed because it must be wait till the whole pipeline is up and running
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SendToPipeline stk = new SendToPipeline(format, adapterPipeline);
        try {
            fr = new FileReader(fileUri);
            BufferedReader br = new BufferedReader(fr);

            InputStream inn = new FileInputStream(fileUri);
            parser.parse(inn, stk);

            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }  catch (ParseException e) {
            logger.error("Error while parsing: " + e.getMessage());
        }
    }

    @Override
    public void stop() {

    }


    @Override
    public GuessSchema getGuessSchema() throws ParseException {


        InputStream dataInputStream = getDataFromEndpoint();

        List<byte[]> dataByte = parser.parseNEvents(dataInputStream, 20);

        EventSchema eventSchema= parser.getEventSchema(dataByte);

        GuessSchema result = SchemaGuesser.guessSchma(eventSchema, getNElements(20));

        return result;

//        EventSchema result = null;
//
//        FileReader fr = null;
//
//        try {
//            fr = new FileReader(fileUri);
//            BufferedReader br = new BufferedReader(fr);
//
//            InputStream inn = new FileInputStream(fileUri);
//            result = parser.getEventSchema(parser.parseNEvents(inn, 1).get(0));
//
//            fr.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        return result;
    }


    public InputStream getDataFromEndpoint() throws ParseException {
        FileReader fr = null;
        InputStream inn = null;

        try {
            fr = new FileReader(fileUri);
            BufferedReader br = new BufferedReader(fr);

            inn = new FileInputStream(fileUri);

        } catch (FileNotFoundException e) {
            throw new ParseException("File not found: " + fileUri);
        }
        if (inn == null)
            throw new ParseException("Could not receive Data from file: " + fileUri);

        return inn;
    }
    @Override
    public List<Map<String, Object>> getNElements(int n) throws ParseException {
        List<Map<String, Object>> result = new ArrayList<>();

        InputStream dataInputStream = getDataFromEndpoint();

        List<byte[]> dataByteArray = parser.parseNEvents(dataInputStream, n);

        // Check that result size is n. Currently just an error is logged. Maybe change to an exception
        if (dataByteArray.size() < n) {
            logger.error("Error in File Protocol! User required: " + n + " elements but the resource just had: " +
                    dataByteArray.size());
        }

        for (byte[] b : dataByteArray) {
            result.add(format.parse(b));
        }

        return result;
    }


    @Override
    public String getId() {
        return ID;
    }

}
