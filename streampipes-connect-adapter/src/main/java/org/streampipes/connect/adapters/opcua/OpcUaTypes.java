/*
 * Copyright 2019 FZI Forschungszentrum Informatik
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

package org.streampipes.connect.adapters.opcua;


import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.streampipes.sdk.utils.Datatypes;

public class OpcUaTypes {

    public static Datatypes getType(UInteger o) {
        if (UInteger.valueOf(4).equals(o) | UInteger.valueOf(6).equals(o) | UInteger.valueOf(8).equals(o) | UInteger.valueOf(27).equals(o)) {
            return Datatypes.Integer;
        } else if (UInteger.valueOf(11).equals(o)) {
            return Datatypes.Double;
        } else if (UInteger.valueOf(10).equals(o) | UInteger.valueOf(26).equals(o)) {
            return Datatypes.Float;
        } else if (UInteger.valueOf(1).equals(o)) {
            return Datatypes.Boolean;
        } else if (UInteger.valueOf(12).equals(o)) {
            return Datatypes.String;
        }

        return Datatypes.String;
    }

}

