package com.udacity.jwdnd.c1.cloudstorage.orm;

import com.udacity.jwdnd.c1.cloudstorage.models.Credential;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface CredentialMapper {
    @Insert("INSERT INTO credentials (credentialid, url, username, `key`, password, userid) VALUES (#{credentialId}, #{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insert(Credential credential);
}
