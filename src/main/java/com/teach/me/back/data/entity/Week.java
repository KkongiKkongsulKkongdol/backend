package com.teach.me.back.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "week")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Week {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JoinColumn(name = "group_id")
    @ManyToOne
    private Group group;

    private String focusOne;
    private String focusTwo;
    private String focusThree;

}
