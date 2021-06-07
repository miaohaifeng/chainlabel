package com.happy.video.pojo;

import lombok.Data;

import java.util.Set;

@Data
public class GroupRelationship {
    private String parent;
    private Set<String> child;
}
