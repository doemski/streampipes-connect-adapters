/*
 * Copyright 2018 FZI Forschungszentrum Informatik
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * you may obtain a copy of the License at
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

package org.streampipes.connect.adapters.sensemap.model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Sensor {

    @SerializedName("icon")
    private String mIcon;
    @SerializedName("lastMeasurement")
    private LastMeasurement mLastMeasurement;
    @SerializedName("sensorType")
    private String mSensorType;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("unit")
    private String mUnit;
    @SerializedName("_id")
    private String m_id;

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public LastMeasurement getLastMeasurement() {
        return mLastMeasurement;
    }

    public void setLastMeasurement(LastMeasurement lastMeasurement) {
        mLastMeasurement = lastMeasurement;
    }

    public String getSensorType() {
        return mSensorType;
    }

    public void setSensorType(String sensorType) {
        mSensorType = sensorType;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getUnit() {
        return mUnit;
    }

    public void setUnit(String unit) {
        mUnit = unit;
    }

    public String get_id() {
        return m_id;
    }

    public void set_id(String _id) {
        m_id = _id;
    }

}
