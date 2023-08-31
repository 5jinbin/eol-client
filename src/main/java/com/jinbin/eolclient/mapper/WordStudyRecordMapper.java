package com.jinbin.eolclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinbin.eolclient.pojo.Word;
import com.jinbin.eolclient.pojo.WordStudyRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WordStudyRecordMapper extends BaseMapper<WordStudyRecord> {

    /**
     * 某个难度的单词量统计
     * @param difficulty
     * @return
     */
    @Select("SELECT COUNT(*) FROM eol_word WHERE difficulty = #{difficulty}")
    Integer selectTotal(@Param("difficulty") Integer difficulty);

    /**
     * 某userId下已经完成的总数
     * @param userId
     * @param difficulty
     * @return
     */
    @Select("SELECT COUNT(*) FROM eol_word_study_record WHERE user_id = #{userId} AND word_id IN (SELECT id FROM eol_word WHERE difficulty = #{difficulty})")
    Integer selectCompCount(@Param("userId") Integer userId, @Param("difficulty") Integer difficulty);


    /**
     * 某userId下今日任务完成数
     * @param userId
     * @return
     */
    @Select("SELECT COUNT(*) \n" +
            "FROM eol_word_study_record AS WSR,eol_word AS W\n" +
            "WHERE WSR.user_id= #{userId}\n" +
            "AND WSR.study_date = DATE(NOW())\n" +
            "AND WSR.word_id = W.id\n" +
            "AND W.difficulty = (" +
            "   SELECT default_difficulty FROM eol_user_word_config WHERE user_id = WSR.user_id" +
            ")")
    Integer selectCompDailyCount(@Param("userId") Integer userId);


}
