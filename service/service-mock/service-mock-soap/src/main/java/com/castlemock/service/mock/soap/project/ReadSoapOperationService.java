/*
 * Copyright 2015 Karl Dahlgren
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

package com.castlemock.service.mock.soap.project;

import com.castlemock.model.core.Service;
import com.castlemock.model.core.ServiceResult;
import com.castlemock.model.core.ServiceTask;
import com.castlemock.model.mock.soap.domain.SoapMockResponse;
import com.castlemock.model.mock.soap.domain.SoapOperation;
import com.castlemock.service.mock.soap.project.input.ReadSoapOperationInput;
import com.castlemock.service.mock.soap.project.output.ReadSoapOperationOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Karl Dahlgren
 * @since 1.0
 */
@org.springframework.stereotype.Service
public class ReadSoapOperationService extends AbstractSoapProjectService implements Service<ReadSoapOperationInput, ReadSoapOperationOutput> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReadSoapOperationService.class);

    /**
     * The process message is responsible for processing an incoming serviceTask and generate
     * a response based on the incoming serviceTask input
     * @param serviceTask The serviceTask that will be processed by the service
     * @return A result based on the processed incoming serviceTask
     * @see ServiceTask
     * @see ServiceResult
     */
    @Override
    @SuppressWarnings("deprecation")
    public ServiceResult<ReadSoapOperationOutput> process(final ServiceTask<ReadSoapOperationInput> serviceTask) {
        final ReadSoapOperationInput input = serviceTask.getInput();
        final SoapOperation soapOperation = this.operationRepository.findOne(input.getOperationId());
        final List<SoapMockResponse> mockResponses = this.mockResponseRepository.findWithOperationId(input.getOperationId());
        soapOperation.setMockResponses(mockResponses);

        if(soapOperation.getDefaultMockResponseId() != null){
            // Iterate through all the mocked responses to identify
            // which has been set to be the default XPath mock response.
            boolean defaultXpathMockResponseFound = false;
            for(SoapMockResponse mockResponse : mockResponses){
                if(mockResponse.getId().equals(soapOperation.getDefaultMockResponseId())){
                    soapOperation.setDefaultResponseName(mockResponse.getName());
                    defaultXpathMockResponseFound = true;
                    break;
                }
            }

            if(!defaultXpathMockResponseFound){
                // Unable to find the default XPath mock response.
                // Log only an error message for now.
                LOGGER.error("Unable to find the default XPath mock response with the following id: " +
                        soapOperation.getDefaultXPathMockResponseId());
            }
        }



        return createServiceResult(ReadSoapOperationOutput.builder()
                .operation(soapOperation)
                .build());
    }
}
