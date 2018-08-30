package com.example.mapper;

import java.util.List;

import com.example.model.Messages;

public interface MessageMapper {
int insertMessage(Messages message);
List<Messages> findAllMessage();
List<Messages> findByMessagename(String messagename);
void deleteIdMessage(int id);
}
