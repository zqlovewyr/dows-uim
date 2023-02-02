package org.dows.account.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 * @date 2023/2/2 9:49
 */
@Data
public class NormalDataVo implements Serializable {
    private static final long serialVersionUID = -3972286994024019236L;
    private String id;          //标题id
    private String name;        //标题名
    private List<Object> dataList; //数据
    private Long count;    //数量
    private String per;       //百分比

    public NormalDataVo(String name, List<Object> dataList) {
        this.name = name;
        this.dataList = dataList;
    }

    public NormalDataVo(String name, Long count, String per) {
        this.name = name;
        this.count = count;
        this.per = per;
    }

    public NormalDataVo(String name, Long count) {
        this.name = name;
        this.count = count;
    }
}
