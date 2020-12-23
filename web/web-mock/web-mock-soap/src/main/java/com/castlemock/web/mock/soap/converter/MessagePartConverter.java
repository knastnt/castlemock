/*
 * Copyright 2020 Karl Dahlgren
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.castlemock.web.mock.soap.converter;

import com.castlemock.core.mock.soap.model.project.domain.SoapOperationIdentifier;
import com.castlemock.web.mock.soap.converter.types.MessagePart;
import com.castlemock.web.mock.soap.converter.types.Namespace;

import java.util.Set;

public final class MessagePartConverter {

    private MessagePartConverter(){

    }

    public static SoapOperationIdentifier toSoapOperationIdentifier(final MessagePart messagePart,
                                                                    final Set<Namespace> namespaces){
        return messagePart.getElement()
                .map(attribute -> AttributeConverter.toSoapOperationIdentifier(attribute, namespaces))
                .orElseGet(() -> SoapOperationIdentifier.builder()
                        .name(messagePart.getName())
                        .namespace(null)
                        .build());
    }

}