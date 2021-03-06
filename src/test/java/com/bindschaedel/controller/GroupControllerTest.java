package com.bindschaedel.controller;

import com.bindschaedel.entity.ClubGroup;
import com.bindschaedel.service.GroupService;
import com.google.common.collect.Iterables;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GroupControllerTest {

    @InjectMocks
    GroupController groupController;

    @Mock GroupService groupService;

    @Test
    public void testGetSingleGroup() {
        ClubGroup group = new ClubGroup();
        group.setName("Test Group");
        group.setAge("5-8");
        when(groupService.findById(any(Long.class))).thenReturn(group);
        ResponseEntity<ClubGroup> responseEntity = groupController.getGroupById("1");

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody().getName()).isEqualTo(group.getName());
    }

    @Test
    public void testGetAllGroups() {
        ClubGroup group = new ClubGroup();
        group.setName("Test Group");
        group.setAge("5-8");
        ClubGroup group2 = new ClubGroup();
        group2.setName("Second Test Group");
        group2.setAge("5-8");
        Iterable<ClubGroup> groups = Arrays.asList(group, group2);
        when(groupService.getAll()).thenReturn(groups);
        ResponseEntity<Iterable<ClubGroup>> responseEntity = groupController.getAllGroups();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(Iterables.size(responseEntity.getBody())).isEqualTo(2);
    }
}
