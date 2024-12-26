package com.teach.me.back.data.dto.group;

public record GroupCreateRequest(String name, String explain , boolean isOpen,String password,Long token) {
}
