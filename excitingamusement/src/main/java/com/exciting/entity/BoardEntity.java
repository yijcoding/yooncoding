package com.exciting.entity;

import java.time.LocalDateTime;

import javax.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name="board")
public class BoardEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int board_id;
    
    @Column(length = 45)
    private String b_title;

    @Column(columnDefinition = "TEXT")
    private String b_content;
    
    private LocalDateTime postdate;
    
    @Column(nullable = false)
    private int visitcount;

    @Column(length = 45, nullable = false)
    private String b_type;
    
    private int level;
    
    @Column(length = 45) 
    private String member_id;

    @Column(nullable = false)
    private int favorite;

    @Column(nullable = false)
    private int hate;
}
