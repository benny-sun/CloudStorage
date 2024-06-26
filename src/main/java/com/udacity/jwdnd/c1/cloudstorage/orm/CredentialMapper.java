package com.udacity.jwdnd.c1.cloudstorage.orm;

import com.udacity.jwdnd.c1.cloudstorage.models.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {
    @Select("SELECT * FROM credentials WHERE userid = #{userId}")
    List<Credential> getCredentials(Integer userId);

    @Insert("INSERT INTO credentials (url, username, `key`, password, userid) VALUES (#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insert(Credential credential);

    @Update("UPDATE credentials SET url = #{url}, username = #{username}, `key` = #{key}, password = #{password} WHERE credentialid = #{credentialId} AND userid = #{userId}")
    int update(Credential credential);

    @Delete("DELETE FROM credentials WHERE credentialid = #{credentialId} AND credentialid = #{credentialId} AND userid = #{userId}")
    int delete(Integer credentialId, Integer userId);
}
