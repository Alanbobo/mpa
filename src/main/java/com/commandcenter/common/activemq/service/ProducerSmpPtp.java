package com.commandcenter.common.activemq.service;


import javax.jms.Message;

public interface ProducerSmpPtp {



  /**
   * 向指定队列发送消息
   * @param queue
   * @param msg
   */
  void sendMessage(final String queue, final String msg);

  /**
   * 使用临时队列queue发送消息同时接收回复的消息(接收回复消息使用定制化业务逻辑,可按分页接收回复消息)
   * @param queue
   * @param msg
   */
  void pubAndMultiRece(final String queue, final String msg);
  void pubAndMultiReceAdd(final String queue, final String msg);
  Message pubAndMultiRece1(final String queue, final String msg);
  /**
   * 使用临时队列queue发送消息同时接收回复的消息(同步接收一次返回)
   * @param queue
   * @param msg
   */
  void pubAndOnceRece(final String queue, final String msg);

  /**
   * 使用临时队列queue发送消息同时接收回复的消息(同步接收一次返回)
   * @param queue 临时队列名称
   * @param msg 传入的临时队列消息
   * @return true代表成功 false代表失败
   */
  Boolean pubAndOnceReceReturn(String queue,String msg);

}
