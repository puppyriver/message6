package message6.web.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;
import java.util.List;

/**
 * Author: Ronnie.Chen
 * Date: 2016/3/15
 * Time: 15:39
 * rongrong.chen@alcatel-sbell.com.cn
 */
public class QueryResult implements Serializable{

    private Integer pageSize = 100;
    private Integer currentPage = 1;
    private List data;
    private Integer total = 0;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }



    public QueryResult(Integer total,Integer pageSize, Integer currentPage, List data) {
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.data = data;
        this.total = total;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
