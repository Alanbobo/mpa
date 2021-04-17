package com.commandcenter.common.activemq.service;



public interface ProducerIccPtp {


  /**
   * 向指定队列发送消息
   */
  void sendMessage(final String queue, final String msg);

  /**
   * 使用临时队列queue发送消息同时接收回复的消息(同步接收一次返回)
   * @param queue
   * @param msg
   */
  String pubAndOnceRece(final String queue, final String msg);


}
