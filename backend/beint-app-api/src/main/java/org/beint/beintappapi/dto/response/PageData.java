package org.beint.beintappapi.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.beint.beintappapi.exceptions.ErrorMessage;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class PageData<T> extends BaseResponse {
    private List<T> data;
    private Integer page;
    private Integer limit;
    private Long totalElements;

    public PageData() {
        super();
    }

    public PageData(ErrorMessage appErrorMessage) {
        this.setCode(appErrorMessage.getCode());
        this.setMess(appErrorMessage.getMessage());
    }

}
