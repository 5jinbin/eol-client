package com.jinbin.eolclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinbin.eolclient.pojo.Word;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WordMapper extends BaseMapper<Word> {

    Page<Word> selectPage(@Param("page") Page<Word> page);

    @Select("SELECT w.* " +
            "FROM eol_word w " +
            "WHERE 1 = 1 " +
            "AND ((#{difficultyLevel} = -1) OR (#{difficultyLevel} IS NULL) OR (w.difficulty = 0 AND #{difficultyLevel} = 0) OR (w.difficulty = 1 AND #{difficultyLevel} = 1)) " +
            "AND ( " +
            "  CASE " +
            "    WHEN #{filterCondition} = 0 THEN NOT EXISTS (SELECT r1.word_id FROM eol_word_study_record r1 WHERE r1.user_id = #{userId} AND r1.word_id = w.id) " +
            "    WHEN #{filterCondition} = 1 THEN EXISTS (SELECT r1.word_id FROM eol_word_study_record r1 WHERE r1.user_id = #{userId} AND r1.word_id = w.id) " +
            "    ELSE 1=1 " +
            "  END " +
            ")")
    List<Word> selectWordsByCondition(
            @Param("userId") Integer userId,
            @Param("difficultyLevel") Integer difficultyLevel,
            @Param("filterCondition") Integer filterCondition
    );

    @Select("SELECT W.* " +
            "FROM eol_word AS W, eol_user_word_config AS UWC " +
            "WHERE UWC.user_id = #{userId} " +
            "AND W.difficulty = UWC.default_difficulty " +
            "AND NOT W.id IN ( " +
            "   SELECT word_id FROM eol_word_study_record WHERE user_id = UWC.user_id " +
            ") " +
            "ORDER BY W.id " +
            "LIMIT #{taskCount} ")
    List<Word> selectTask(
            @Param("userId") Integer userId,
            @Param("taskCount") Integer taskCount
    );


}

