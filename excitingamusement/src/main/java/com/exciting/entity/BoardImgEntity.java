package com.exciting.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name="boardimg")
public class BoardImgEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardImg_num;
    
    private int board_id;

    @Column(length = 1000)
    private String boardImg;

    private int announcement_num;
    private int inquiry_num;
}
