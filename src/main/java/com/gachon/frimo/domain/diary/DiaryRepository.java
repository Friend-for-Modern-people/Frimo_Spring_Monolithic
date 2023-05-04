package com.gachon.frimo.domain.diary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gachon.frimo.domain.user.User;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {

    public Diary findByDiaryPk(Long diaryPk);
    public List<Diary> findAllByMainSent(int mainSent);

    public List<Diary> findAllByAuthor(User author);


}
