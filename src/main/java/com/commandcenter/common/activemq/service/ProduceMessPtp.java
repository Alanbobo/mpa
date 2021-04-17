package com.commandcenter.common.activemq.service;

public interface ProduceMessPtp {
    /**
     * 向指定的topic发布消息（字节）
     */
    public void publishTopic(final String topic, final String msg);

    /**
     * 向指定的topic发布消息 (字符串)
     */
    public void publishStrTopic(final String topic, final String msg);

    /**
     * 向指定的topic发布消息 (字符串) 带上属性clientId
     *
     * @param topic
     * @param msg
     * @param clientId
     */
    public void publishStrTopic(final String topic, final String msg, String clientId);


    /**
     * 向指定的Queue发布消息（字节）
     */
    public void publishQueue(final String queue, final String msg);

    /**
     * 向指定的Queue发布消息 (字符串)
     */
    public void publishStrQueue(final String queue, final String msg);
}
