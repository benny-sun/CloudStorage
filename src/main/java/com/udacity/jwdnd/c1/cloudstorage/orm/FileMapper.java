package com.udacity.jwdnd.c1.cloudstorage.orm;

import com.udacity.jwdnd.c1.cloudstorage.models.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM files WHERE userid = #{userId}")
    List<File> getFiles(Integer userId);

    @Select("SELECT * FROM files WHERE userid = #{userId} AND fileId = #{fileId}")
    File getFile(Integer fileId, Integer userId);

    @Select("SELECT EXISTS(SELECT 1 FROM files WHERE filename = #{filename} AND userid = #{userId})")
    Boolean isExistedBy(String filename, Integer userId);

    @Insert("INSERT INTO files (filename, contenttype, filesize, userid, filedata) VALUES (#{filename}, #{contentType}, #{filesize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(File file);

    @Delete("DELETE FROM files WHERE userid = #{userId} AND fileId = #{fileId}")
    int delete(Integer fileId, Integer userId);
}
