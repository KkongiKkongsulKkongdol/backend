package com.teach.me.back.controller;

import com.teach.me.back.data.dto.group.*;
import com.teach.me.back.data.entity.Group;
import com.teach.me.back.data.entity.User;
import com.teach.me.back.data.entity.UserGroup;
import com.teach.me.back.data.entity.Week;
import com.teach.me.back.data.repository.GroupRepository;
import com.teach.me.back.data.repository.UserGroupRepository;
import com.teach.me.back.data.repository.UserRepository;
import com.teach.me.back.data.repository.WeekRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupRepository repository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final UserGroupRepository userGroupRepository;
    private final WeekRepository weekRepository;

    @PostMapping("/user")
    public void addUser(@RequestParam Long groupId, @RequestParam Long userId) {

        User user = userRepository.findById(userId).get();
        Group group = groupRepository.findById(groupId).get();

        UserGroup userGroup = new UserGroup(null, user, group, "브론즈", 0L);
        userGroupRepository.save(userGroup);
    }

    @GetMapping("/my")
    public ResponseEntity<List<FindGroupResponse>> findMy(@RequestParam Long token) {
        return new ResponseEntity<>(groupRepository.findByUserId(token), HttpStatus.OK);
    }

    @PostMapping
    public void createGroup(@RequestBody GroupCreateRequest request) {

        User user = userRepository.queryById(request.token());

        Group group;

        if (request.password().isEmpty()) {

            group = new Group(null, user, request.name(), request.isOpen(), null, request.explain());
        } else {
            group = new Group(null, user, request.name(), request.isOpen(), request.password(), request.explain());
        }

        Group savedGroup = repository.save(group);
        UserGroup userGroup = new UserGroup(null, user, savedGroup, "브론즈", 0L);
        userGroupRepository.save(userGroup);
    }

    @GetMapping
    public ResponseEntity<List<FindGroupResponse>> findAll() {
        return new ResponseEntity<>(groupRepository.findAllBy(), HttpStatus.OK);
    }

    @GetMapping("/week")
    public ResponseEntity<List<FindWeekResponse>> findWeek(@RequestParam Long groupId) {
        return new ResponseEntity<>(weekRepository.findAllByGroupId(groupId), HttpStatus.OK);
    }

    @PostMapping("/week")
    public void createWeek(@RequestBody WeekCreateRequest request) {

        Group group = groupRepository.findById(request.groupId()).get();

        Week week;

        if(Objects.isNull(request.focusTwo())){
            week = new Week(null, request.name(), group,request.focusOne(),null,null);
        }else if(Objects.isNull(request.focusThree())){
            week = new Week(null, request.name(), group,request.focusOne(),request.focusTwo(),null);
        }else{
            week = new Week(null, request.name(), group,request.focusOne(),request.focusTwo(),request.focusThree());
        }
        weekRepository.save(week);
    }

    @GetMapping("/week/spec")
    public ResponseEntity<FindWeekResponse> findWeekSpec(@RequestParam Long weekId) {
        return new ResponseEntity<>(weekRepository.queryById(weekId), HttpStatus.OK);
    }

    @GetMapping("/board")
    public ResponseEntity<List<FindGroupBoardResponse>> findGroupByBoard(@RequestParam Long groupId) {
        return new ResponseEntity<>(userGroupRepository.findAllByGroupIdOrderByPointDesc(groupId), HttpStatus.OK);
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<List<FindUserGroup>> find(@PathVariable Long groupId) {
        return new ResponseEntity<>(userGroupRepository.queryByGroupId(groupId), HttpStatus.OK);
    }

    @GetMapping("/search/user")
    public ResponseEntity<List<FindGroupResponse>> searchByUserName(@RequestParam String userName) {
        return new ResponseEntity<>(groupRepository.findAllByUserNameContaining(userName), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<FindGroupResponse>> search(@RequestParam String name) {
        return new ResponseEntity<>(groupRepository.findAllByNameContaining(name), HttpStatus.OK);
    }

}
