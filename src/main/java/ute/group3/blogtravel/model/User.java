package ute.group3.blogtravel.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "User")
public class User {
    @Id
    private Long id;
    @Field(value = "FullName")
    private String fullName;

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }

    public String getFullName(){
        return fullName;
    }
    public void setFullName(String fullName){
        this.fullName=fullName;
    }
    @Override
    public String toString(){
        return "id="+id+" Fullname="+fullName;
    }

}
