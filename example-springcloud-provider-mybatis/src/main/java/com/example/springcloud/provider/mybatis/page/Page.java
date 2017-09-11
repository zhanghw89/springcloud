package com.example.springcloud.provider.mybatis.page;

/**自己看看，需要什么字段加什么字段吧*/
public class Page {  
      
    private Integer rows;  
      
    private Integer page = 1;  
      
    private Integer totalRecord;  
  
    public Integer getRows() {  
        return rows;  
    }  
  
    public void setRows(Integer rows) {  
        this.rows = rows;  
    }  
  
    public Integer getPage() {  
        return page;  
    }  
  
    public void setPage(Integer page) {  
        this.page = page;  
    }  
  
    public Integer getTotalRecord() {  
        return totalRecord;  
    }  
  
    public void setTotalRecord(Integer totalRecord) {  
        this.totalRecord = totalRecord;  
    }  
      
}  