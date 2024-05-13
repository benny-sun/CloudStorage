package com.udacity.jwdnd.c1.cloudstorage.orm;

import com.udacity.jwdnd.c1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM notes WHERE userid = #{userId}")
    List<Note> getNotes(Integer userId);

    @Insert("INSERT INTO notes (notetitle, notedescription, userid) VALUES (#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insert(Note note);

    @Update("UPDATE notes SET notetitle = #{noteTitle}, notedescription = #{noteDescription} WHERE noteId = #{noteId} AND userId = #{userId}")
    int update(Note note);

    @Delete("DELETE FROM notes WHERE noteid = #{noteId} AND userid = #{userId}")
    int delete(Integer noteId, Integer userId);
}
