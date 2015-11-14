package com.fortmocks.mock.soap.model.project.service.message.input;

import com.fortmocks.core.model.Input;
import com.fortmocks.core.model.validation.NotNull;
import com.fortmocks.mock.soap.model.project.dto.SoapPortDto;

import java.util.List;

/**
 * @author Karl Dahlgren
 * @since 1.0
 */
public class CreateSoapPortsInput implements Input {

    @NotNull
    private Long soapProjectId;
    @NotNull
    private List<SoapPortDto> soapPorts;

    public CreateSoapPortsInput(Long soapProjectId, List<SoapPortDto> soapPorts) {
        this.soapProjectId = soapProjectId;
        this.soapPorts = soapPorts;
    }

    public Long getSoapProjectId() {
        return soapProjectId;
    }

    public void setSoapProjectId(Long soapProjectId) {
        this.soapProjectId = soapProjectId;
    }

    public List<SoapPortDto> getSoapPorts() {
        return soapPorts;
    }

    public void setSoapPorts(List<SoapPortDto> soapPorts) {
        this.soapPorts = soapPorts;
    }
}