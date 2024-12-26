package com.teach.me.back.data.repository;

import com.teach.me.back.data.dto.group.FindGroupBoardResponse;
import com.teach.me.back.data.dto.group.FindUserGroup;
import com.teach.me.back.data.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

    List<FindUserGroup> queryByGroupId(Long id);

    List<FindGroupBoardResponse> findAllByGroupIdOrderByPointDesc(Long groupId);

}
