package com.teach.me.back.data.repository;

import com.teach.me.back.data.dto.group.FindGroupResponse;
import com.teach.me.back.data.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    List<FindGroupResponse> findAllBy();

    List<FindGroupResponse> findAllByNameContaining(String name);

    List<FindGroupResponse> findAllByUserNameContaining(String userName);

    List<FindGroupResponse> findByUserId(Long id);

    FindGroupResponse findGroupById(Long id);

}


