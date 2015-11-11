package com.fortmocks.mock.rest.model.project.message;

import com.fortmocks.core.model.Input;
import com.sun.istack.internal.NotNull;

/**
 * @author Karl Dahlgren
 * @since 1.0
 */
public class DeleteRestProjectInput implements Input{

    @NotNull
    private Long restProjectId;

    public Long getRestProjectId() {
        return restProjectId;
    }

    public void setRestProjectId(Long restProjectId) {
        this.restProjectId = restProjectId;
    }
}