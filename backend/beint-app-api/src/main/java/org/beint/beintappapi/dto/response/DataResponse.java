package org.beint.beintappapi.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import org.beint.beintappapi.exceptions.ErrorMessage;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataResponse<T> extends BaseResponse {
    private T data;

    public DataResponse() {
        super();
    }

    public DataResponse(T data) {
        this();
        this.data = data;
    }

    public DataResponse(ErrorMessage appErrorMessage) {
        this.setCode(appErrorMessage.getCode());
        this.setMess(appErrorMessage.getMessage());
    }

}
