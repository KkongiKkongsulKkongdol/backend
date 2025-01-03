package com.teach.me.back.data.dto.gpt.request;

import lombok.*;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatCompletionDto {

    // 사용할 모델
    private String model;

    private List<ChatRequestMsgDto> messages;


    @Builder
    public ChatCompletionDto(String model, List<ChatRequestMsgDto> messages) {
        this.model = model;
        this.messages = messages;
    }
}
