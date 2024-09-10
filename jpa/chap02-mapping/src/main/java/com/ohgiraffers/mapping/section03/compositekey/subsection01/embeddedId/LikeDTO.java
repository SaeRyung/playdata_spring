package com.ohgiraffers.mapping.section03.compositekey.subsection01.embeddedId;

public class LikeDTO {
    private int likeMemberNo;
    private int likedBookNo;

    public LikeDTO(int likeMemberNo, int likedBookNo) {
        this.likeMemberNo = likeMemberNo;
        this.likedBookNo = likedBookNo;
    }

    public int getLikeMemberNo() {
        return likeMemberNo;
    }

    public void setLikeMemberNo(int likeMemberNo) {
        this.likeMemberNo = likeMemberNo;
    }

    public int getLikedBookNo() {
        return likedBookNo;
    }

    public void setLikedBookNo(int likedBookNo) {
        this.likedBookNo = likedBookNo;
    }

    @Override
    public String toString() {
        return "LikeDTO{" +
                "likeMemberNo=" + likeMemberNo +
                ", likedBookNo=" + likedBookNo +
                '}';
    }
}
