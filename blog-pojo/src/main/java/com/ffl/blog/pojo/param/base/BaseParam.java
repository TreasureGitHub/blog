package com.ffl.blog.pojo.param.base;

import com.ffl.blog.pojo.enums.DeleteType;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lff
 * @datetime 2020/01/01 17:25
 */
@Data
public class BaseParam implements Serializable {

    private static final long serialVersionUID = 8476966655603123907L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 是否删除
     */
    private DeleteType isDeleted;
    private Boolean boolIsDeleted;

    public void supply(){

        if(isDeleted == null){
            isDeleted = DeleteType.EXISTS;
            boolIsDeleted = false;
        } else if(isDeleted == DeleteType.EXISTS){
            boolIsDeleted = false;
        } else {
            boolIsDeleted = true;
        }

    }

}
