package com.whxm.harbor.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.Range;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @desc 分页查询对象
 */
@ApiModel("分页查询对象")
public class PageQO<T> {

    @ApiModelProperty(value = "当前页号")
    @Range(min = 1, max = Integer.MAX_VALUE)
    private int pageNum = 1;

    @ApiModelProperty(value = "一页数量")
    @Range(min = 1, max = Integer.MAX_VALUE)
    private int pageSize = 10;

//    @ApiModelProperty(value = "排序", notes = "例：create_time desc,update_time desc")
//    private String orderBy;

    public PageQO(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public PageQO() {
    }

    public int getOffset() {
        return (this.pageNum - 1) * this.pageSize;
    }


    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @ApiModelProperty(value = "查询条件",hidden = true)
    private T condition;

    public T getCondition() {
        return condition;
    }

    public void setCondition(T condition) {
        this.condition = condition;
    }
}