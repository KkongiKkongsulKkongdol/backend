package com.teach.me.back.data.repository;

import com.teach.me.back.data.dto.group.FindWeekResponse;
import com.teach.me.back.data.entity.Week;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeekRepository extends JpaRepository<Week, Long> {

    List<FindWeekResponse> findAllByGroupId(Long id);

    FindWeekResponse queryById(Long id);

}
