package com.user.domeuser.msg.modle;

import java.io.Serializable;
import java.util.Date;

/**
 * @author littlenew
 * @date 2020/5/22 5:31 PM
 **/
public class MessageInfo implements Serializable {
    private static final long serialVersionUID = 5085638769010287618L;
    /**
     * id
     */
    private Long id;
    /**
     * 消息
     */
    private String msg;
    /**
     * 发送时间
     */
    private Date sendTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return "KafkaMessage{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", sendTime=" + sendTime +
                '}';
    }
}
